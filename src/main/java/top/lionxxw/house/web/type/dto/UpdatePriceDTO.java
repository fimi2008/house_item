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
public class UpdatePriceDTO implements Serializable {
    /**
     * 品牌ID
     */
    private Long brandId;
    /**
     * 清单ID
     */
    private Long billId;
    /**
     * 价格
     */
    private Double price;
}
