package com.zifuji.cloud.server.base.module.exception.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zifuji.cloud.base.bean.Exception20000;
import com.zifuji.cloud.base.bean.Exception30000;
import com.zifuji.cloud.base.bean.Exception40000;
import com.zifuji.cloud.base.bean.Result;

import java.util.Objects;

@Slf4j
@Component
@RestControllerAdvice
public class ResultExceptionHandle {


    // 入参框架报错
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<String> handleException(MethodArgumentNotValidException e) {
        return setErrorCode(20000, e);
    }

    // 业务中自主抛出的异常
    @ExceptionHandler(Exception20000.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<String> handleException(Exception20000 e) {
        return setErrorCode(20000, e);
    }

    // 业务中自主抛出的300异常
    @ExceptionHandler(Exception30000.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<String> handleException(Exception30000 e) {
        return setErrorCode(30000, e);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<String> handleException(AuthenticationException e) {
        return setErrorCode(30000, e);
    }

    // 业务中自主抛出的400异常
    @ExceptionHandler(Exception40000.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<String> handleException(Exception40000 e) {
        return setErrorCode(40000, e);
    }

    // spring security 抛出的权限不足异常
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<String> handleException(AccessDeniedException e) {
        return setErrorCode(40000, e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<String> handleException(Exception e) {
        return setErrorCode(50000, e);
    }


    private Result<String> setErrorCode(Integer code, Exception e) {
        log.info("异常：", e);
        if (20000 == code) {
            return Result.set20000Mes(e.getMessage());
        } else if (30000 == code) {
            return Result.set30000Mes(e.getMessage());
        } else if (40000 == code) {
            return Result.set40000Mes(e.getMessage());
        } else if (50000 == code) {
            return Result.set50000Mes(e.getMessage());
        } else {
            return Result.set50000Mes(e.getMessage());
        }
    }


}
