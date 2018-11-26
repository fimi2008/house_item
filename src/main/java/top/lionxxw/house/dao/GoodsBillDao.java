package top.lionxxw.house.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.lionxxw.house.entity.GoodsBill;
import top.lionxxw.house.entity.ItemType;

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
public interface GoodsBillDao {

    /**
     * 新增
     *
     * @param bill 对象
     * @return 新增成功条数
     */
    int insert(GoodsBill bill);

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
     * @param bill 对象
     * @return 更新成功条数
     */
    int update(GoodsBill bill);

    /**
     * 根据主键ID查询
     *
     * @param id 主键ID
     * @return 对象
     */
    GoodsBill getById(Long id);

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    List<GoodsBill> selectAll();

    /**
     * 根据动态化参数查询
     *
     * @param param 参数
     * @return 配对数据
     */
    List<GoodsBill> selectParam(@Param(value = "param") GoodsBill param);

    /**
     * 根据typeId查询数据
     * @param typeId t_item_type表主键ID
     * @return
     */
    List<GoodsBill> selectByTypeId(Long typeId);
}
