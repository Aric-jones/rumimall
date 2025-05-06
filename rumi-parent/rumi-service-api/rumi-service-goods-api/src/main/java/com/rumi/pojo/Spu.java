package com.rumi.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/4 16:00
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_spu")
@AllArgsConstructor
@NoArgsConstructor
public class Spu implements Serializable {

    //主键
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    //货号
    private String sn;
    //SPU名
    private String name;
    //副标题
    private String caption;
    //品牌ID
    private Integer brandId;
    //一级分类
    private Integer category1Id;
    //二级分类
    private Integer category2Id;
    //三级分类
    private Integer category3Id;
    //模板ID
    private Integer templateId;
    //运费模板id
    private Integer freightId;
    //图片
    private String image;
    //图片列表
    private String images;
    //售后服务
    private String saleService;
    //介绍
    private String introduction;
    //规格列表
    private String specItems;
    //参数列表
    private String paraItems;
    //销量
    private Integer saleNum;
    //评论数
    private Integer commentNum;
    //是否上架
    private String isMarketable;
    //是否启用规格
    private String isEnableSpec;
    //是否删除
    private String isDelete;
    //审核状态
    private String status;


    //get方法
    public Long getId() {
        return id;
    }

    //set方法
    public void setId(Long id) {
        this.id = id;
    }

    //get方法
    public String getSn() {
        return sn;
    }

    //set方法
    public void setSn(String sn) {
        this.sn = sn;
    }

    //get方法
    public String getName() {
        return name;
    }

    //set方法
    public void setName(String name) {
        this.name = name;
    }

    //get方法
    public String getCaption() {
        return caption;
    }

    //set方法
    public void setCaption(String caption) {
        this.caption = caption;
    }

    //get方法
    public Integer getBrandId() {
        return brandId;
    }

    //set方法
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    //get方法
    public Integer getCategory1Id() {
        return category1Id;
    }

    //set方法
    public void setCategory1Id(Integer category1Id) {
        this.category1Id = category1Id;
    }

    //get方法
    public Integer getCategory2Id() {
        return category2Id;
    }

    //set方法
    public void setCategory2Id(Integer category2Id) {
        this.category2Id = category2Id;
    }

    //get方法
    public Integer getCategory3Id() {
        return category3Id;
    }

    //set方法
    public void setCategory3Id(Integer category3Id) {
        this.category3Id = category3Id;
    }

    //get方法
    public Integer getTemplateId() {
        return templateId;
    }

    //set方法
    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    //get方法
    public Integer getFreightId() {
        return freightId;
    }

    //set方法
    public void setFreightId(Integer freightId) {
        this.freightId = freightId;
    }

    //get方法
    public String getImage() {
        return image;
    }

    //set方法
    public void setImage(String image) {
        this.image = image;
    }

    //get方法
    public String getImages() {
        return images;
    }

    //set方法
    public void setImages(String images) {
        this.images = images;
    }

    //get方法
    public String getSaleService() {
        return saleService;
    }

    //set方法
    public void setSaleService(String saleService) {
        this.saleService = saleService;
    }

    //get方法
    public String getIntroduction() {
        return introduction;
    }

    //set方法
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    //get方法
    public String getSpecItems() {
        return specItems;
    }

    //set方法
    public void setSpecItems(String specItems) {
        this.specItems = specItems;
    }

    //get方法
    public String getParaItems() {
        return paraItems;
    }

    //set方法
    public void setParaItems(String paraItems) {
        this.paraItems = paraItems;
    }

    //get方法
    public Integer getSaleNum() {
        return saleNum;
    }

    //set方法
    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    //get方法
    public Integer getCommentNum() {
        return commentNum;
    }

    //set方法
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    //get方法
    public String getIsMarketable() {
        return isMarketable;
    }

    //set方法
    public void setIsMarketable(String isMarketable) {
        this.isMarketable = isMarketable;
    }

    //get方法
    public String getIsEnableSpec() {
        return isEnableSpec;
    }

    //set方法
    public void setIsEnableSpec(String isEnableSpec) {
        this.isEnableSpec = isEnableSpec;
    }

    //get方法
    public String getIsDelete() {
        return isDelete;
    }

    //set方法
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    //get方法
    public String getStatus() {
        return status;
    }

    //set方法
    public void setStatus(String status) {
        this.status = status;
    }


}
