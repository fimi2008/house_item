package top.lionxxw.house.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.lionxxw.house.dao.*;
import top.lionxxw.house.entity.*;
import top.lionxxw.house.exception.ErrorCode;
import top.lionxxw.house.exception.ServerException;
import top.lionxxw.house.service.BillService;
import top.lionxxw.house.util.BeanUtil;
import top.lionxxw.house.web.type.dto.RelateBrandDTO;
import top.lionxxw.house.web.type.dto.UpdatePriceDTO;
import top.lionxxw.house.web.type.vo.TypeDetailVO;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * 采购清单接口实现

 * created on 2018/9/16 16:16
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Service
@Slf4j
public class BillServiceImpl implements BillService {
    @Autowired
    private ItemTypeDao itemTypeDao;
    @Autowired
    private GoodsBillDao goodsBillDao;
    @Autowired
    private BrandDao brandDao;
    @Autowired
    private BillBrandItemDao billBrandItemDao;
    @Autowired
    private UploadFileDao uploadFileDao;


    @Override
    public List<ItemType> findTypes() {
        return itemTypeDao.selectAll();
    }

    @Override
    public void saveType(ItemType itemType) {
        List<ItemType> itemTypes = itemTypeDao.selectParam(itemType);
        if (!CollectionUtils.isEmpty(itemTypes)) {
            log.info("{} 清单类型已存在", itemType.getType());
            throw new ServerException(ErrorCode.DUPLICATE_DATA, itemType.getType());
        }
        int insert = itemTypeDao.insert(itemType);
        log.info("新增成功：{}", insert);
    }

    @Override
    public void delType(Long id) {
        itemTypeDao.deleteById(id);
        log.info("id：{} 数据删除");
    }

    @Override
    public TypeDetailVO typeDetail(Long id) {
        ItemType itemType = itemTypeDao.getById(id);
        if (null != itemType) {
            TypeDetailVO typeDetail = BeanUtil.objConvert(itemType, TypeDetailVO.class);
            List<GoodsBill> bills = goodsBillDao.selectByTypeId(id);
            if (!CollectionUtils.isEmpty(bills)) {
                log.info("{} 下拥有 {} 条采购数据", itemType.getType(), bills.size());
                List<TypeDetailVO.GoodsBill> goodsBills = BeanUtil.convertList(bills, TypeDetailVO.GoodsBill.class);
                typeDetail.setBills(goodsBills);
            }
            List<BrandCheckInfo> brands = brandDao.selectByTypeId(id);
            if (!CollectionUtils.isEmpty(brands)) {
                List<TypeDetailVO.Brand> brandList = BeanUtil.convertList(brands, TypeDetailVO.Brand.class);
                typeDetail.setBrands(brandList);
                // 已有品牌
                List<TypeDetailVO.Brand> checkedBrands = brandList.stream().filter(TypeDetailVO.Brand::isChecked).collect(toList());
                if (!CollectionUtils.isEmpty(checkedBrands)) {
                    Map<Long, Map<Long, BillBrandItem>> brandMap = new LinkedHashMap<>();
                    checkedBrands.forEach(item -> {
                        // 根据品牌查询
                        List<BillBrandItem> items = billBrandItemDao.selectByBrandId(item.getId());
                        if (!CollectionUtils.isEmpty(items)) {
                            Map<Long, BillBrandItem> itemMap = new HashMap<>(items.size());
                            items.forEach(it -> itemMap.put(it.getBillId(), it));
                            brandMap.put(item.getId(), itemMap);
                        } else {
                            brandMap.put(item.getId(), null);
                        }
                    });
                    typeDetail.setCheckedBrands(checkedBrands);

                    typeDetail.getBills().forEach(bill -> {
                        List<TypeDetailVO.BrandPrice> brandPrices = new ArrayList<>(brandMap.size());
                        brandMap.forEach((brandId, itemMap) -> {
                            TypeDetailVO.BrandPrice brandPrice;
                            if (null != itemMap) {
                                BillBrandItem billBrandItem = itemMap.get(bill.getId());
                                if (null != billBrandItem) {
                                    brandPrice = BeanUtil.objConvert(billBrandItem, TypeDetailVO.BrandPrice.class);
                                } else {
                                    brandPrice = new TypeDetailVO.BrandPrice(bill.getId(), brandId);
                                }
                            } else {
                                brandPrice = new TypeDetailVO.BrandPrice(bill.getId(), brandId);
                            }
                            brandPrices.add(brandPrice);
                        });
                        bill.setBrandPriceList(brandPrices);
                    });
                }
            }
            return typeDetail;
        }
        return null;
    }

    @Override
    public void saveBill(GoodsBill bill) {
        if (bill.getTypeId() == null) {
            log.info("typeId 值为NULL");
            throw new ServerException(ErrorCode.MISS_PARAMETER, "清单类型");
        }
        List<GoodsBill> bills = goodsBillDao.selectParam(bill);
        if (!CollectionUtils.isEmpty(bills)) {
            log.info("typeId:{} name:{} 采购清单已存在", bill.getTypeId(), bill.getName());
            throw new ServerException(ErrorCode.DUPLICATE_DATA, bill.getName());
        }
        int insert = goodsBillDao.insert(bill);
        log.info("新增成功：{}", insert);
    }

    @Override
    public List<Brand> findBrand() {
        return brandDao.selectAll();
    }

    @Override
    public void saveBrand(Brand brand) {
        List<Brand> brands = brandDao.selectParam(brand);
        if (!CollectionUtils.isEmpty(brands)) {
            log.info("name:{} 品牌已存在", brand.getName());
            throw new ServerException(ErrorCode.DUPLICATE_DATA, brand.getName());
        }
        int insert = brandDao.insert(brand);
        log.info("新增成功：{}", insert);
    }

    @Override
    public void delBrand(Long id) {
        brandDao.deleteById(id);
        log.info("id：{} 数据删除");
    }

    @Override
    public void relateBrand(RelateBrandDTO dto) {
        if (dto.getTypeId() == null) {
            log.info("typeId 值为NULL");
            throw new ServerException(ErrorCode.MISS_PARAMETER, "清单类型");
        }
        int num = brandDao.deleteByTypeId(dto.getTypeId());
        log.info("typeId：{} 成功删除 {} 条数据", dto.getTypeId(), num);
        if (dto.getBrandIds() == null || dto.getBrandIds().size() == 0) {
            log.info("未选择绑定品牌");
            return;
        }
        List<TypeBrandTemp> list = new ArrayList<>(dto.getBrandIds().size());
        dto.getBrandIds().forEach(brandId -> list.add(new TypeBrandTemp(dto.getTypeId(), brandId)));
        brandDao.batchInsert(list);
        log.info("typeId：{} 批量新增 {} 条数据", dto.getTypeId(), list.size());
    }

    @Override
    public void updatePrice(UpdatePriceDTO dto) {
        BillBrandItem item = new BillBrandItem(dto.getBrandId(), dto.getBillId());
        List<BillBrandItem> items = billBrandItemDao.selectParam(item);
        if (CollectionUtils.isEmpty(items)) {
            item.setPrice((long) (dto.getPrice() * 100));
            int insert = billBrandItemDao.insert(item);
            log.info("新增成功：{}", insert);
        } else {
            if (items.size() > 1) {
                log.info("brandId：{}，billId：{}数据异常", dto.getBrandId(), dto.getBillId());
            } else {
                BillBrandItem update = items.get(0);
                update.setPrice((long) (dto.getPrice() * 100));
                int num = billBrandItemDao.update(update);
                log.info("更新成功：{}", num);
            }
        }
    }

    @Override
    public void saveFile(UploadFile uploadFile) {
        int insert = uploadFileDao.insert(uploadFile);
        log.info("新增成功：{}", insert);
    }

    @Override
    public List<UploadFile> findFiles() {
        return uploadFileDao.selectAll();
    }

    @Override
    public UploadFile getFileById(Long id) {
        return uploadFileDao.getById(id);
    }

    @Override
    public void updateFile(Long fileId, Long billId, Long brandId) {
        BillBrandItem item = new BillBrandItem(brandId, billId);
        List<BillBrandItem> items = billBrandItemDao.selectParam(item);
        if (CollectionUtils.isEmpty(items)) {
            item.setPrice(0L);
            item.setFileId(fileId);
            int insert = billBrandItemDao.insert(item);
            log.info("新增成功：{}", insert);
        } else {
            if (items.size() > 1) {
                log.info("brandId：{}，billId：{}数据异常", brandId, billId);
            } else {
                BillBrandItem update = items.get(0);
                update.setFileId(fileId);
                int num = billBrandItemDao.update(update);
                log.info("更新成功：{}", num);
            }
        }
    }

    @Override
    public void delImg(UpdatePriceDTO dto) {
        int num = billBrandItemDao.delImg(dto);
        log.info("更新成功：{}", num);
    }
}
