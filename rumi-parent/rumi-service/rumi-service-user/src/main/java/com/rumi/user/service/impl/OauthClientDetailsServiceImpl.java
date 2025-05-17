package com.rumi.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.user.dao.OauthClientDetailsMapper;
import com.rumi.user.pojo.OauthClientDetails;
import com.rumi.user.service.IOauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {

    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    @Override
    public IPage<OauthClientDetails> findPage(OauthClientDetails oauthClientDetails, int page, int size) {
        QueryWrapper<OauthClientDetails> queryWrapper = new QueryWrapper<>();

        if (oauthClientDetails != null) {
            if (oauthClientDetails.getClientId() != null && !oauthClientDetails.getClientId().isEmpty()) {
                queryWrapper.eq("client_id", oauthClientDetails.getClientId());
            }
            if (oauthClientDetails.getResourceIds() != null && !oauthClientDetails.getResourceIds().isEmpty()) {
                queryWrapper.eq("resource_ids", oauthClientDetails.getResourceIds());
            }
            if (oauthClientDetails.getClientSecret() != null && !oauthClientDetails.getClientSecret().isEmpty()) {
                queryWrapper.eq("client_secret", oauthClientDetails.getClientSecret());
            }
            if (oauthClientDetails.getScope() != null && !oauthClientDetails.getScope().isEmpty()) {
                queryWrapper.eq("scope", oauthClientDetails.getScope());
            }
            if (oauthClientDetails.getAuthorizedGrantTypes() != null && !oauthClientDetails.getAuthorizedGrantTypes().isEmpty()) {
                queryWrapper.eq("authorized_grant_types", oauthClientDetails.getAuthorizedGrantTypes());
            }
            if (oauthClientDetails.getWebServerRedirectUri() != null && !oauthClientDetails.getWebServerRedirectUri().isEmpty()) {
                queryWrapper.eq("web_server_redirect_uri", oauthClientDetails.getWebServerRedirectUri());
            }
            if (oauthClientDetails.getAuthorities() != null && !oauthClientDetails.getAuthorities().isEmpty()) {
                queryWrapper.eq("authorities", oauthClientDetails.getAuthorities());
            }
            if (oauthClientDetails.getAccessTokenValidity() != null) {
                queryWrapper.eq("access_token_validity", oauthClientDetails.getAccessTokenValidity());
            }
            if (oauthClientDetails.getRefreshTokenValidity() != null) {
                queryWrapper.eq("refresh_token_validity", oauthClientDetails.getRefreshTokenValidity());
            }
            if (oauthClientDetails.getAdditionalInformation() != null && !oauthClientDetails.getAdditionalInformation().isEmpty()) {
                queryWrapper.eq("additional_information", oauthClientDetails.getAdditionalInformation());
            }
            if (oauthClientDetails.getAutoapprove() != null && !oauthClientDetails.getAutoapprove().isEmpty()) {
                queryWrapper.eq("autoapprove", oauthClientDetails.getAutoapprove());
            }
        }

        IPage<OauthClientDetails> iPage = new Page<>(page, size);
        return this.page(iPage, queryWrapper);
    }

    @Override
    public IPage<OauthClientDetails> findPage(int page, int size) {
        IPage<OauthClientDetails> iPage = new Page<>(page, size);
        return this.page(iPage);
    }
}