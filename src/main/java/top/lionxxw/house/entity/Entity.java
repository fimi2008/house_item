package top.lionxxw.house.entity;

import lombok.Data;

import java.util.Date;

/**
 * 实体基类

 * created on 2018/9/16 17:44
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Data
public class Entity<T> {

    /**
     * 主键id
     */
    private T id;

    /**
     * 数据状态
     * NOR,DEL
     */
    private String status;

    /**
     * 创建
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;

    private static String NOR = "NOR",DEL= "DEL";
}
