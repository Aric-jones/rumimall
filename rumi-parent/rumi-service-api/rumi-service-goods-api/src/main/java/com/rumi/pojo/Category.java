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

/****
 * @Author:admin
 * @Description:Category构建
 * @Date 2019/6/14 19:13
 *****/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_category")
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    private String name;
    private Integer goodsNum;
    private String isShow;
    private String isMenu;
    private Integer seq;
    private Integer parentId;
    private Integer templateId;
}
