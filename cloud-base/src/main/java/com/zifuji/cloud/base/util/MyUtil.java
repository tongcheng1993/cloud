package com.zifuji.cloud.base.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MyUtil {

    public static void getProperties() {

        String tmpdir = System.getProperty("java.io.tmpdir");
        log.info("java.io.tmpdir:" + tmpdir);


    }


}
