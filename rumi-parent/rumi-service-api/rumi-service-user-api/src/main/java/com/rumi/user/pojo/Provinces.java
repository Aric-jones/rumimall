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
@TableName("tb_provinces")
@ApiModel(value="Provinces对象", description="省份信息表")
public class Provinces implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "省份ID")
    @TableId(value = "provinceid")
    private String provinceid;

    @ApiModelProperty(value = "省份名称")
    private String province;


}
