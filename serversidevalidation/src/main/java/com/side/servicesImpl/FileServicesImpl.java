package com.side.servicesImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.side.services.FileServices;


@Service
public class FileServicesImpl implements FileServices{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		//file name
		
		String name = file.getOriginalFilename();
		
		String rendomID =UUID.randomUUID().toString();
		String fileName1=rendomID.concat(name.substring(name.lastIndexOf(".")));
		
		//full path
		String filePath = path+File.separator+fileName1;
		
		//create folder  if not created
		
		File f =new File(path);
		
		if(!f.exists()) {
			f.mkdir();
		}
		
		//copy file
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return name;
	}

	@Override
	public InputStream getResource(String path, String filename) throws FileNotFoundException {
		
		String fullPath =path+File.separator+filename;
		FileInputStream res = new FileInputStream(fullPath);
		
		return res;
	}

}
