package com.zifuji.cloud.server.base.util;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.http.MediaType;

import java.io.InputStream;
import java.io.OutputStream;

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

}
