package top.lionxxw.house.entity;

import lombok.Data;

/**
 * 房屋装修清单类型

 * created on 2018/9/16 15:34
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Data
public class ItemType extends Entity<Long>{
    /**
     * 清单类型
     */
    private String type;
}
