package com.rumi.goods.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;



/****
 * @Author:admin
 * @Description:Brand构建
 * @Date 2019/6/14 19:13
 *****/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_brand")
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    private String name;
    private String image;
    private String letter;
    private Integer seq;

}
