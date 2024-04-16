package com.zifuji.cloud.server.base.module.file.bean;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.zifuji.cloud.server.base.module.exception.bean.Exception20000;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Data
@Slf4j
public class ZfjTmpBean {

    public String getTempDirPath() {
        String tmpdir = System.getProperty("java.io.tmpdir");
        String parentPath = tmpdir + File.separator + StrUtil.uuid();
        // 先清空临时目录
        FileUtil.del(parentPath);
        File pathFile = new File(parentPath);
        if (!pathFile.exists()) {
            // 如果路径不存在创建路径
            boolean f = pathFile.mkdirs();
            if (!f) {
                throw new Exception20000("创建临时文件夹失败");
            }
        }
        return parentPath;
    }

}
