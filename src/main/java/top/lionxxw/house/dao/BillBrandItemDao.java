package top.lionxxw.house.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.lionxxw.house.entity.BillBrandItem;
import top.lionxxw.house.web.type.dto.UpdatePriceDTO;

import java.util.List;

/**
 * 品牌订单价格明细

 * created on 2018/9/16 16:17
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Repository
@Mapper
public interface BillBrandItemDao {

    /**
     * 新增
     *
     * @param item 对象
     * @return 新增成功条数
     */
    int insert(BillBrandItem item);

    /**
     * 删除
     *
     * @param id 主键ID
     * @return 删除成功条数
     */
    int deleteById(Long id);

    /**
     * 更新
     *
     * @param item 对象
     * @return 更新成功条数
     */
    int update(BillBrandItem item);

    /**
     * 根据主键ID查询
     *
     * @param id 主键ID
     * @return 对象
     */
    BillBrandItem getById(Long id);

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    List<BillBrandItem> selectAll();

    /**
     * 根据动态化参数查询
     *
     * @param param 参数
     * @return 配对数据
     */
    List<BillBrandItem> selectParam(@Param(value = "param") BillBrandItem param);

    /**
     * 根据品牌ID查询信息
     *
     * @param brandId
     * @return
     */
    List<BillBrandItem> selectByBrandId(Long brandId);

    /**
     * 删除图片关联
     *
     * @param dto
     * @return
     */
    int delImg(UpdatePriceDTO dto);
}
