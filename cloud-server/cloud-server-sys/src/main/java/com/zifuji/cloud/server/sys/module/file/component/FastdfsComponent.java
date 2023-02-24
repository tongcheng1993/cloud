package com.zifuji.cloud.server.sys.module.file.component;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

import cn.hutool.core.io.FileUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class FastdfsComponent {

	private FastFileStorageClient storageClient;

	public String uploadFile(MultipartFile file) throws IOException {
		StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
				FileUtil.extName(file.getOriginalFilename()), null);
		return storePath.getFullPath();
	}

	public InputStream download(String fileUrl) {
		StorePath storePath = StorePath.parseFromUrl(fileUrl);
		return download(storePath.getGroup(), storePath.getPath());
	}

	public InputStream download(String groupName, String path) {
		InputStream ins = storageClient.downloadFile(groupName, path, new DownloadCallback<InputStream>() {
			@Override
			public InputStream recv(InputStream ins) throws IOException {
				// 将此ins返回给上面的ins
				return ins;
			}
		});
		return ins;
	}

	public void deleteFile(String fileUrl) {
		StorePath storePath = StorePath.parseFromUrl(fileUrl);
		deleteFile(storePath.getGroup(), storePath.getPath());
	}

	public void deleteFile(String groupPath, String path) {
		storageClient.deleteFile(groupPath, path);
	}
}
