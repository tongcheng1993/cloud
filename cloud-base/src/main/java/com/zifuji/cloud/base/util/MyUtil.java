package com.zifuji.cloud.base.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MyUtil {

    public static String getPropertiesTmpdir() {

        String tmpdir = System.getProperty("java.io.tmpdir");
        return tmpdir;

    }


}
