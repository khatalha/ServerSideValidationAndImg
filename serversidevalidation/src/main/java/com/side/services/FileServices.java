package com.side.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileServices {
	
	String uploadImage(String path ,MultipartFile  file) throws IOException;
	
	
	InputStream getResource(String path , String filename) throws FileNotFoundException;

}