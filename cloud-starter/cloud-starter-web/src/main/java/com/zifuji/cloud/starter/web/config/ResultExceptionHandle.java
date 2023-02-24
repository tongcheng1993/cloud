package com.zifuji.cloud.starter.web.config;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.base.exception.Exception300;
import com.zifuji.cloud.base.exception.Exception400;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ResultExceptionHandle {

    public ResultExceptionHandle() {
    }

    @ExceptionHandler(Exception200.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(Exception200 e) {
        log.error("业务异常：" + e.getMessage(), e);
        return new Result<Object>().set200Mes(e.getMessage());
    }

    @ExceptionHandler(Exception300.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(Exception300 e) {
        log.error("业务异常：" + e.getMessage(), e);
        return new Result<Object>().set200Mes(e.getMessage());
    }

    @ExceptionHandler(Exception400.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(Exception400 e) {
        log.error("业务异常：" + e.getMessage(), e);
        return new Result<Object>().set400Mes(e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(AuthenticationException e) {
        log.error("业务异常：" + e.getMessage(), e);
        return new Result<>().set400Mes("权限不足，请联系管理员");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(AccessDeniedException e) {
        log.error("业务异常：" + e.getMessage(), e);
        return new Result<>().set300Mes("权限不足，请联系管理员");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(MethodArgumentNotValidException e) {
        log.error("业务异常：" + e.getMessage(), e);
        String err = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return new Result<>().set200Mes(err);

    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(Exception e) {
        log.error("业务异常：" + e.getMessage(), e);
        return new Result<>().set500Mes("逻辑错误，请向管理员求助");
    }
}
