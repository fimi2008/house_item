package top.lionxxw.house.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.lionxxw.house.entity.ItemType;

import java.util.List;

/**
 * 清单类型

 * created on 2018/9/16 16:17
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Repository
@Mapper
public interface ItemTypeDao {

    /**
     * 新增
     *
     * @param itemType 对象
     * @return 新增成功条数
     */
    int insert(ItemType itemType);

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
     * @param itemType 对象
     * @return 更新成功条数
     */
    int update(ItemType itemType);

    /**
     * 根据主键ID查询
     *
     * @param id 主键ID
     * @return 对象
     */
    ItemType getById(Long id);

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    List<ItemType> selectAll();

    /**
     * 根据动态化参数查询
     *
     * @param param 参数
     * @return 配对数据
     */
    List<ItemType> selectParam(@Param(value = "param") ItemType param);
}
