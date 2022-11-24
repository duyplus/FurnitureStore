package com.store.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface UploadService {
	File save(MultipartFile file, String folder);
	File save1(MultipartFile file, String folder);
}
