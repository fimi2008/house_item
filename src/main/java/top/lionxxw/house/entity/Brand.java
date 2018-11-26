package top.lionxxw.house.entity;

import lombok.Data;

/**
 * 品牌

 * created on 2018/9/16 15:41
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Data
public class Brand extends Entity<Long>{
    /**
     * 品牌名称
     */
    private String name;
    /**
     * 备注
     */
    private String note;
}
