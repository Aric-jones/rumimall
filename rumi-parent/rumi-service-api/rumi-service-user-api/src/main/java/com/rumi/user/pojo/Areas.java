package com.rumi.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author:CSH
 * @updator:CSH
 * @date 2025/5/17 22:26
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_areas")
@ApiModel(value="Areas对象", description="行政区域县区信息表")
public class Areas implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域ID")
    @TableId(value = "areaid")
    private String areaid;

    @ApiModelProperty(value = "区域名称")
    private String area;

    @ApiModelProperty(value = "城市ID")
    private String cityid;


}
