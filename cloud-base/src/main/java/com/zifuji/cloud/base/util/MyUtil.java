package com.zifuji.cloud.base.util;

import java.io.File;

import com.zifuji.cloud.base.bean.Exception20000;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class MyUtil {
	/**
	 * 获取系统定义的临时文件夹
	 * 
	 * @return
	 */
	public static String getPropertiesTmpdir() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getRandomDir() {
		String parentPath = getPropertiesTmpdir() + File.separator + StrUtil.uuid();
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
