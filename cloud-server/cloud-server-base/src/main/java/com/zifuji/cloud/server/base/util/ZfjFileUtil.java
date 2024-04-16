package com.zifuji.cloud.server.base.util;


import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class ZfjFileUtil {

    public static FileItem createFileItem(InputStream inputStream, String fileName) {
        FileItemFactory fileItemFactory = new DiskFileItemFactory(16, null);
        String textFileName = "file";
        FileItem fileItem = fileItemFactory.createItem(textFileName, MediaType.MULTIPART_FORM_DATA_VALUE, true, fileName);
        int byteRead = 0;
        byte[] buffer = new byte[1024];
        OutputStream os = null;
        try {
            os = fileItem.getOutputStream();
            while ((byteRead = inputStream.read(buffer)) != -1) {
                os.write(buffer, 0, byteRead);
            }
            inputStream.close();
        } catch (Exception e) {

        } finally {

        }
        return fileItem;
    }


    public static String getMimeType(String fileName) {
        if (fileName == null || "".equals(fileName.trim()) || fileName.indexOf(".") <= 0) {
            return "";
        }
        String type = new MimetypesFileTypeMap().getContentType(fileName);
        if (type == null || "".equals(type)) {
            return "";
        }
        return type;
    }


    public static String getUrlFileName(String urlFilePath) {
        String[] split = urlFilePath.split("\\/");
        return split[split.length - 1];
    }


    public static MultipartFile getMultipartFile(File file) {
        FileItem item = new DiskFileItemFactory().createItem("file"
                , getMimeType(file.getName())
                , true
                , file.getName());
        try {
            InputStream input = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            // 流转移
            IoUtil.copy(input, os);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid file: " + e, e);
        }

        return new CommonsMultipartFile(item);
    }


}
