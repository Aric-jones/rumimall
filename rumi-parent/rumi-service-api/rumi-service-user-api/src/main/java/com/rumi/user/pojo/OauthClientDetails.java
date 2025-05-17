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
@TableName("oauth_client_details")
@ApiModel(value="OauthClientDetails对象", description="")
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "客户端ID，主要用于标识对应的应用")
    @TableId(value = "client_id")
    private String clientId;

    private String resourceIds;

    @ApiModelProperty(value = "客户端秘钥，BCryptPasswordEncoder加密算法加密")
    private String clientSecret;

    @ApiModelProperty(value = "对应的范围")
    private String scope;

    @ApiModelProperty(value = "认证模式")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "认证后重定向地址")
    private String webServerRedirectUri;

    private String authorities;

    @ApiModelProperty(value = "令牌有效期")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "令牌刷新周期")
    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;


}
