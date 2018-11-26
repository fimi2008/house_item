package top.lionxxw.house.web.type.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除清单图片
 * <p>
 *
 * @author lionxxw
 * created on 2018/9/20 19:43
 */
@Data
public class UpdateImgDTO implements Serializable {
    /**
     * 品牌ID
     */
    private Long brandId;
    /**
     * 清单ID
     */
    private Long billId;
}
