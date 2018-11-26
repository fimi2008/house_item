package top.lionxxw.house.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.lionxxw.house.entity.BillBrandItem;
import top.lionxxw.house.entity.Brand;
import top.lionxxw.house.entity.BrandCheckInfo;
import top.lionxxw.house.entity.TypeBrandTemp;

import java.util.List;

/**
 * 名类型

 * created on 2018/9/16 16:17
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Repository
@Mapper
public interface BrandDao {

    /**
     * 新增
     *
     * @param brand 对象
     * @return 新增成功条数
     */
    int insert(Brand brand);

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
     * @param brand 对象
     * @return 更新成功条数
     */
    int update(Brand brand);

    /**
     * 根据主键ID查询
     *
     * @param id 主键ID
     * @return 对象
     */
    Brand getById(Long id);

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    List<Brand> selectAll();

    /**
     * 根据动态化参数查询
     *
     * @param param 参数
     * @return 配对数据
     */
    List<Brand> selectParam(@Param(value = "param") Brand param);

    /**
     * 根据类型ID查询所有品牌
     *
     * @param typeId
     * @return
     */
    List<BrandCheckInfo> selectByTypeId(Long typeId);

    /**
     * 根据类型ID删除绑定关系
     *
     * @param typeId
     * @return
     */
    int deleteByTypeId(Long typeId);

    /**
     * 批量新增类型绑定品牌数据
     *
     * @param list
     */
    void batchInsert(List<TypeBrandTemp> list);
}
