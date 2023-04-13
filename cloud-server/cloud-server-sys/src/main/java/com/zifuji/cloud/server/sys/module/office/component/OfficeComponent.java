package com.zifuji.cloud.server.sys.module.office.component;

import com.aspose.words.Document;
import com.zifuji.cloud.server.base.properties.ZfjProperties;
import com.zifuji.cloud.server.sys.module.office.util.OfficeUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

@Slf4j
@Component
@AllArgsConstructor
public class OfficeComponent {

    private ZfjProperties zfjProperties;

    public void test() throws Exception {
        String dirPath = zfjProperties.getTempDirPath();
        String docName = "test.doc";
        String pdfName = "test.pdf";
        String docPath = dirPath + File.separator + docName;
        String pdfPath = dirPath + File.separator + pdfName;


        Document document = new Document();
        document.save(docPath);

        OfficeUtil.doc2pdf(docPath, pdfPath);
    }


}
