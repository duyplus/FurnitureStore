package com.store.service.impl;

import com.store.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    ServletContext app;

    @Override
    public File save(MultipartFile file, String folder) {
        File dir = new File(app.getRealPath("/assets/" + folder));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String s = System.currentTimeMillis() + file.getOriginalFilename();
        String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
        try {
            File savedFile = new File(dir, name);
            file.transferTo(savedFile);
            System.err.println(savedFile.getAbsolutePath());
            return savedFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    @Override
	public File save1(MultipartFile file, String folder) {
		File temp = new File(app.getRealPath("/Admin/assets/img/"+folder));
		 String test = temp.getAbsolutePath().substring(0,temp.getAbsolutePath().lastIndexOf("webapp"))+"resources/static/assets/" + folder;
		 File dir = new File(test);
		 System.err.println(test);
		// File dir = new File(test + folder);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		System.out.println(dir.getAbsolutePath());
		String s = System.currentTimeMillis() + file.getOriginalFilename();
		String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
		try {
			File savedFile = new File(dir, name);
			file.transferTo(savedFile);
			System.err.println(savedFile.getAbsolutePath());
			return savedFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
