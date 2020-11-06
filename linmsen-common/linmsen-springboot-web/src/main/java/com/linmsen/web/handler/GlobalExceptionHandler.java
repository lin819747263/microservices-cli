package com.linmsen.web.handler;


import com.linmsen.core.exception.ServiceException;
import com.linmsen.core.vo.CommonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult serviceExceptionHandler(ServiceException ex) {
//        logger.info("[serviceExceptionHandler]", ex);
        System.out.println("我执行了");
        return CommonResult.error(ex.getCode(), ex.getMessage());
    }

}
