package com.rumi.pojo;

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
@TableName("tb_stock_back")
@AllArgsConstructor
@NoArgsConstructor
public class StockBack implements Serializable {

    //订单id
    private String orderId;
    //SKU的id
    private String skuId;
    //回滚数量
    private Integer num;
    //回滚状态
    private String status;
    //创建时间
    private Date createTime;
    //回滚时间
    private Date backTime;


}
