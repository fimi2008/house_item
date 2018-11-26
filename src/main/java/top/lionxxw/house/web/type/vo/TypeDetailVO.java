package top.lionxxw.house.web.type.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 采购类型详情数据封装
 * <p>
 *
 * @author lionxxw
 * created on 2018/9/18 14:30
 */
@Data
public class TypeDetailVO implements Serializable {
    private Long id;
    private String type;
    private Date createTime;

    private List<GoodsBill> bills;

    /**
     * 已选品牌集合
     */
    private List<Brand> checkedBrands;

    @Data
    public static class GoodsBill implements Serializable {
        private Long id;
        /**
         * 清单名称
         */
        private String name;
        /**
         * 清单数目
         */
        private Integer num;
        /**
         * 各品牌价格
         */
        private List<BrandPrice> brandPriceList;
    }

    /**
     * 品牌价格
     */
    @Data
    public static class BrandPrice implements Serializable {
        private Long id;
        private Long billId;
        private Long brandId;
        private double price;
        private Long fileId;

        public BrandPrice() {
        }

        public BrandPrice(Long billId, Long brandId) {
            this.billId = billId;
            this.brandId = brandId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price / 100;
        }

        public void setPrice(Long price) {
            this.price = price / 100;
        }
    }

    /**
     * 所有品牌
     */
    private List<Brand> brands;

    @Data
    public static class Brand implements Serializable {
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
        private boolean checked;
    }
}
