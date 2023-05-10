package com.zifuji.cloud.server.office.module.words.util;

import com.aspose.words.Document;
import com.aspose.words.FontSettings;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class WordUtil {
    public static boolean getLicense() {
        boolean result = false;
        try {
            String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<!-- -->\n" +
                    "<License>\n" +
                    "    <Data>\n" +
                    "        <Products>\n" +
                    "            <Product>Aspose.Total for Java</Product>\n" +
                    "            <Product>Aspose.Words for Java</Product>\n" +
                    "        </Products>\n" +
                    "        <EditionType>Enterprise</EditionType>\n" +
                    "        <SubscriptionExpiry>20991231</SubscriptionExpiry>\n" +
                    "        <LicenseExpiry>20991231</LicenseExpiry>\n" +
                    "        <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n" +
                    "    </Data>\n" +
                    "    <Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature>\n" +
                    "</License>";
            InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
            License license = new License();
            license.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void doc2pdf(String docPath, String pdfPath) throws Exception {
        long old = System.currentTimeMillis();
        if (!getLicense()) {
            System.out.println("非法");
            return;
        }
        String osName = System.getProperty("os.name", "");
        if (osName.startsWith("Mac OS")) {

        } else if (osName.startsWith("Windows")) {

        } else {
            //如果是linux执行，需要添加这个 ，如果还有乱码，可以把/usr/share/fonts/chinese路径下的所有文件拷贝到有问题的环境。并且再执行：source /etc/profile
            new FontSettings().setFontsFolder("/usr/share/fonts/chinese",true);
        }
        FileOutputStream pdfPathStream = new FileOutputStream(pdfPath);
        Document doc = new Document(docPath);
        doc.save(pdfPathStream, SaveFormat.PDF);
        long now = System.currentTimeMillis();
        System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");
    }
}
