package com.rumi.goods.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/4 16:00
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_sku")
@AllArgsConstructor
@NoArgsConstructor
public class Sku implements Serializable {

    //商品id
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    //商品条码
    private String sn;
    //SKU名称
    private String name;
    //价格（分）
    private Integer price;
    //库存数量
    private Integer num;
    //库存预警数量
    private Integer alertNum;
    //商品图片
    private String image;
    //商品图片列表
    private String images;
    //重量（克）
    private Integer weight;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //SPUID
    private Long spuId;
    //类目ID
    private Integer categoryId;
    //类目名称
    private String categoryName;
    //品牌名称
    private String brandName;
    //规格
    private String spec;
    //销量
    private Integer saleNum;
    //评论数
    private Integer commentNum;
    //商品状态 1-正常，2-下架，3-删除
    private String status;


}
