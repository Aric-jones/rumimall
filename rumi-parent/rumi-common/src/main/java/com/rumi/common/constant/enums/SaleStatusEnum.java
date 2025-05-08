package com.rumi.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: SaleStatusEnum
 * @Description: 商品销售状态
 * @Author: CSH
 * @Date: 2025-05-08 21:13
 */
@Getter
@AllArgsConstructor
public enum SaleStatusEnum {
    ON_SALE("1","已上架"),
    NOT_OFF_SALE("0","未下架");
    private final String code;
    private final String message;
}
