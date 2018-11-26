package top.lionxxw.house.entity;

import lombok.Data;

/**
 * 品牌目录

 * created on 2018/9/16 15:43
 *
 * @author lionxxw
 * @version 1.0.0
 */
@Data
public class BillBrandItem extends Entity<Long>{
    /**
     * 品牌
     * @see Brand id
     */
    private Long brandId;
    /**
     * 清单明细
     * @see GoodsBill id
     */
    private Long billId;
    /**
     * 价格 (单位分)
     */
    private Long price;
    /**
     * 图片ID
     */
    private Long fileId;

    public BillBrandItem() {
    }

    public BillBrandItem(Long brandId, Long billId) {
        this.brandId = brandId;
        this.billId = billId;
    }
}
