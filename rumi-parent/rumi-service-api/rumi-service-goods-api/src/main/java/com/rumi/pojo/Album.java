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
 * @Description:Album构建
 * @Date 2019/6/14 19:13
 *****/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_album")
@AllArgsConstructor
@NoArgsConstructor
public class Album implements Serializable {

    //编号
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    //相册名称
    private String title;
    //相册封面
    private String image;
    //图片列表
    private String imageItems;
}
