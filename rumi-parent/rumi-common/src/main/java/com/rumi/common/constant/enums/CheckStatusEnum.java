package com.rumi.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: CheckStatus
 * @Description: 审核状态
 * @Author: CSH
 * @Date: 2025-05-08 21:06
 */
@Getter
@AllArgsConstructor
public enum CheckStatusEnum {
    UNCHECK("0", "待审核"),
    CHECK_PASS("1", "审核通过"),
    CHECK_NOT_PASS("2", "审核未通过");
    private final String code;
    private final String message;
}
