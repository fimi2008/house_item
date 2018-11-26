package top.lionxxw.house.web.type.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 采购类型关联品牌参数
 * <p>
 *
 * @author lionxxw
 * created on 2018/9/19 14:07
 */
@Data
public class RelateBrandDTO implements Serializable {
    /**
     * 类型ID
     */
    private Long typeId;
    /**
     * 品牌ID数组集合
     */
    private List<Long> brandIds;
}
