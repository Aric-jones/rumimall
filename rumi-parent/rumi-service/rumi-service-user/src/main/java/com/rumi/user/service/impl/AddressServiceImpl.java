package com.rumi.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rumi.user.dao.AddressMapper;
import com.rumi.user.pojo.Address;
import com.rumi.user.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public IPage<Address> findPage(Address address, int page, int size) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();

        if (address != null) {
            if (address.getId() != null) {
                queryWrapper.eq("id", address.getId());
            }
            if (address.getUsername() != null && !address.getUsername().isEmpty()) {
                queryWrapper.like("username", address.getUsername());
            }
            if (address.getProvinceid() != null) {
                queryWrapper.eq("provinceid", address.getProvinceid());
            }
            if (address.getCityid() != null) {
                queryWrapper.eq("cityid", address.getCityid());
            }
            if (address.getAreaid() != null) {
                queryWrapper.eq("areaid", address.getAreaid());
            }
            if (address.getPhone() != null && !address.getPhone().isEmpty()) {
                queryWrapper.eq("phone", address.getPhone());
            }
            if (address.getAddress() != null && !address.getAddress().isEmpty()) {
                queryWrapper.eq("address", address.getAddress());
            }
            if (address.getContact() != null && !address.getContact().isEmpty()) {
                queryWrapper.eq("contact", address.getContact());
            }
            if (address.getIsDefault() != null) {
                queryWrapper.eq("is_default", address.getIsDefault());
            }
            if (address.getAlias() != null && !address.getAlias().isEmpty()) {
                queryWrapper.eq("alias", address.getAlias());
            }
        }

        IPage<Address> iPage = new Page<>(page, size);
        return this.page(iPage, queryWrapper);
    }

    @Override
    public IPage<Address> findPage(int page, int size) {
        IPage<Address> iPage = new Page<>(page, size);
        return this.page(iPage);
    }

    @Override
    public List<Address> findList(Address address) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();

        if (address != null) {
            if (address.getId() != null) {
                queryWrapper.eq("id", address.getId());
            }
            if (address.getUsername() != null && !address.getUsername().isEmpty()) {
                queryWrapper.like("username", address.getUsername());
            }
            if (address.getProvinceid() != null) {
                queryWrapper.eq("provinceid", address.getProvinceid());
            }
            if (address.getCityid() != null) {
                queryWrapper.eq("cityid", address.getCityid());
            }
            if (address.getAreaid() != null) {
                queryWrapper.eq("areaid", address.getAreaid());
            }
            if (address.getPhone() != null && !address.getPhone().isEmpty()) {
                queryWrapper.eq("phone", address.getPhone());
            }
            if (address.getAddress() != null && !address.getAddress().isEmpty()) {
                queryWrapper.eq("address", address.getAddress());
            }
            if (address.getContact() != null && !address.getContact().isEmpty()) {
                queryWrapper.eq("contact", address.getContact());
            }
            if (address.getIsDefault() != null) {
                queryWrapper.eq("is_default", address.getIsDefault());
            }
            if (address.getAlias() != null && !address.getAlias().isEmpty()) {
                queryWrapper.eq("alias", address.getAlias());
            }
        }
        return this.list(queryWrapper);
    }
}