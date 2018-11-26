package top.lionxxw.house.entity;

import lombok.Data;

/**
 * 品牌是否关联类型信息
 * <p>
 *
 * @author lionxxw
 * created on 2018/9/19 13:59
 */
@Data
public class BrandCheckInfo {
    private Long id;
    /**
     * 品牌名称
     */
    private String name;
    /**
     * 备注
     */
    private String note;
    /**
     * 是否已选
     */
    private Boolean checked;
}
