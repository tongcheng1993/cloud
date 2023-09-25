package com.zifuji.cloud.server.base.config;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.base.exception.Exception20000;
import com.zifuji.cloud.base.exception.Exception30000;
import com.zifuji.cloud.base.exception.Exception40000;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ResultExceptionHandle {


    // 入参框架报错
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(MethodArgumentNotValidException e) {
        String err = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return Result.set20000Mes(err);
    }

    // 业务中自主抛出的异常
    @ExceptionHandler(Exception20000.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(Exception20000 e) {
        log.error("Exception200业务异常：" + e.getMessage(), e);
        return Result.set20000Mes(e.getMessage());
    }

    // 业务中自主抛出的300异常
    @ExceptionHandler(Exception30000.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(Exception30000 e) {
        log.error("Exception300业务异常：" + e.getMessage(), e);
        return Result.set30000Mes("权限不足，请联系管理员" + e.getMessage());
    }

    // spring security 抛出的权限不足异常
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(AccessDeniedException e) {
        log.error("AccessDeniedException业务异常：" + e.getMessage(), e);
        return Result.set30000Mes("权限不足，请联系管理员" + e.getMessage());
    }

    // 业务中自主抛出的400异常
    @ExceptionHandler(Exception40000.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(Exception40000 e) {
        log.error("Exception400业务异常：" + e.getMessage(), e);
        return Result.set40000Mes("身份凭证异常，请联系管理员" + e.getMessage());
    }

    // 业务中自主抛出的400异常
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(AuthenticationException e) {
        log.error("AuthenticationException业务异常：" + e.getMessage(), e);
        return Result.set40000Mes("身份凭证异常，请联系管理员" + e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(Exception e) {
        log.error("Exception业务异常：" + e.getMessage(), e);
        return Result.set50000Mes("服务器网络错误，请向管理员求助" + e.getMessage());
    }
}
