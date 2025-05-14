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

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/4 16:00
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_template")
@AllArgsConstructor
@NoArgsConstructor
public class Template implements Serializable {

    //ID
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    //模板名称
    private String name;
    //规格数量
    private Integer specNum;
    //参数数量
    private Integer paraNum;

}
