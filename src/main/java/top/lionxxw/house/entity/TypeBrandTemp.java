package top.lionxxw.house.entity;

import lombok.Data;

/**
 * 采购类型和品牌中间件表数据
 * <p>
 *
 * @author lionxxw
 * created on 2018/9/19 14:17
 */
@Data
public class TypeBrandTemp extends Entity<Long> {
    /**
     * 类型ID
     */
    private Long typeId;

    /**
     * 品牌ID
     */
    private Long brandId;

    public TypeBrandTemp() {
    }

    public TypeBrandTemp(Long typeId, Long brandId) {
        this.typeId = typeId;
        this.brandId = brandId;
    }
}
