package com.zifuji.cloud.server.base.properties;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.UUID;

@Data
@Component
public class ZfjProperties {

    @Value(value = "${temppath:c:/tmp/zfj}")
    private String temppath;


    public String getTempDirPath() {
        String path = temppath + File.separator + StrUtil.uuid();
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
