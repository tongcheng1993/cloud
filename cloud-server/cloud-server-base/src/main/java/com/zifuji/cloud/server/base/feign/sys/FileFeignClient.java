package com.zifuji.cloud.server.base.feign.sys;

import com.zifuji.cloud.base.bean.BaseFile;
import com.zifuji.cloud.base.bean.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name = "cloud-server-sys", contextId = "file", path = "/file")
public interface FileFeignClient {

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Result<String> uploadFile(@RequestPart(value = "file", required = true) MultipartFile file);

    @PostMapping(value = "/downloadFile")
    Result<BaseFile> downloadFile(@RequestBody Long id) throws IOException;
}
