package top.lionxxw.house.service;

import top.lionxxw.house.entity.Brand;
import top.lionxxw.house.entity.GoodsBill;
import top.lionxxw.house.entity.ItemType;
import top.lionxxw.house.entity.UploadFile;
import top.lionxxw.house.web.type.dto.RelateBrandDTO;
import top.lionxxw.house.web.type.dto.UpdatePriceDTO;
import top.lionxxw.house.web.type.vo.TypeDetailVO;

import java.util.List;

/**
 * 采购清单

 * created on 2018/9/16 15:51
 *
 * @author lionxxw
 * @version 1.0.0
 */
public interface BillService {
    /**
     * 所有清单类型集合
     *
     * @return
     */
    List<ItemType> findTypes();

    /**
     * 保存清单类型
     *
     * @param itemType
     */
    void saveType(ItemType itemType);

    /**
     * 删除清单
     *
     * @param id
     */
    void delType(Long id);

    /**
     * 清单详情
     *
     * @param id
     * @return
     */
    TypeDetailVO typeDetail(Long id);

    /**
     * 保存清单明细
     *
     * @param bill
     */
    void saveBill(GoodsBill bill);

    /**
     * 查询品牌
     *
     * @return
     */
    List<Brand> findBrand();

    /**
     * 保存品牌
     *
     * @param brand
     */
    void saveBrand(Brand brand);

    /**
     * 删除品牌
     *
     * @param id
     */
    void delBrand(Long id);

    /**
     * 绑定采购品牌
     *
     * @param dto
     */
    void relateBrand(RelateBrandDTO dto);

    /**
     * 更新清单价格
     *
     * @param dto
     */
    void updatePrice(UpdatePriceDTO dto);

    /**
     * 保存上传文件
     *
     * @param uploadFile
     */
    void saveFile(UploadFile uploadFile);

    /**
     * 查询所有文件
     *
     * @return
     */
    List<UploadFile> findFiles();

    /**
     * 查询文件
     *
     * @param id
     * @return
     */
    UploadFile getFileById(Long id);

    /**
     * 更新明细文件信息
     *
     * @param fileId
     * @param billId
     * @param brandId
     */
    void updateFile(Long fileId, Long billId, Long brandId);

    /**
     * 删除图片
     *
     * @param dto
     */
    void delImg(UpdatePriceDTO dto);
}
