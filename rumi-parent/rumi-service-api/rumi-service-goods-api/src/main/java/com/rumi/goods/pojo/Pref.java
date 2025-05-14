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
@TableName("tb_pref")
@AllArgsConstructor
@NoArgsConstructor
public class Pref implements Serializable {

    //ID
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    //分类ID
    private Integer cateId;
    //消费金额
    private Integer buyMoney;
    //优惠金额
    private Integer preMoney;
    //活动开始日期
    private Date startTime;
    //活动截至日期
    private Date endTime;
    //类型,1:普通订单，2：限时活动
    private String type;
    //状态,1:有效，0：无效
    private String state;


}
