package com.rumi.common.handler.exception;

import com.rumi.common.entity.Result;
import com.rumi.common.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:CSH
 * @Updator:CSH
 * @Date 2025/5/4 21:58
 * @Description: 全局异常处理器
 */
@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value= Exception.class)
    @ResponseBody
    public Result<String> handlerException(Exception e){
        e.printStackTrace();
        return new Result<String>(false, StatusCode.ERROR,e.getMessage());
    }



}
