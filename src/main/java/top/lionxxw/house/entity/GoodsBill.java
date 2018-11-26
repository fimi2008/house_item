package top.lionxxw.house.entity;

import lombok.Data;

/**
 * 采购物品名目

 * created on 2018/9/16 15:36
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Data
public class GoodsBill extends Entity<Long> {
    /**
     * 关联清单类型
     * @see ItemType id
     */
    private Long typeId;
    /**
     * 清单名称
     */
    private String name;
    /**
     * 清单数目
     */
    private Integer num;
}
