package com.zifuji.cloud.server.base.feign.sys;

import com.zifuji.cloud.base.bean.MyFile;
import com.zifuji.cloud.base.bean.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@FeignClient(name = "sys", contextId = "file", path = "/file")
public interface FileFeignClient {

    @PostMapping(value = "/uploadFile")
    Result<String> uploadFile(String uploadPath, MultipartFile file);

    @PostMapping(value = "/downloadFile")
    Result<MyFile> downloadFile(@NotBlank(message = "uploadPath") String uploadPath, @NotNull(message = "id") Long id) throws IOException;
}
