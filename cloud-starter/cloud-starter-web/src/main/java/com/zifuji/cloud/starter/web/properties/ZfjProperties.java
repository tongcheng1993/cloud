package com.zifuji.cloud.starter.web.properties;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import cn.hutool.core.io.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

@Data
@Component
public class ZfjProperties {

    @Value(value = "${temppath:c:/tmp/zfj}")
    private String temppath;


    public String getTempDirPath() {
        String path = temppath + File.separator + UUID.randomUUID().toString();
        // 先清空临时目录
        FileUtil.del(path);
        File pathFile = new File(path);
        if (!pathFile.exists()) {
            // 如果路径不存在创建路径
            pathFile.mkdirs();
        }
        return path;
    }


}
