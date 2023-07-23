package com.zifuji.cloud.server.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class DicChangeHandle {
    public DicChangeHandle() {
        System.out.println("DicChangeHandle");
    }


}
