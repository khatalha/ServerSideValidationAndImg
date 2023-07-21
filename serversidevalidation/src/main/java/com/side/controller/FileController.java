package com.side.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import com.side.payloads.FileRespones;
import com.side.services.FileServices;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileServices fileServices;
	@Value("${project.image}")
	private String path;
	
	
	@PostMapping("/upload")
	public ResponseEntity<FileRespones> fileUpload(@RequestParam("image") MultipartFile image){
		
		String uploadImage=null;
		try {
			uploadImage = this.fileServices.uploadImage(path, image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(new FileRespones(null, "image is not upload success fully"),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(new FileRespones(uploadImage, "image upload success fully"),HttpStatus.OK);
		
	}
	
	
	
	//serve the image
	//Fetch the image in the browser
	
	@GetMapping(value="/profiles/{imageName}")
	public void downloadImage(@PathVariable("imageName") String imageName ,HttpServletResponse respones) throws IOException {
		
		InputStream resource = this.fileServices.getResource(path, imageName);
		respones.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, respones.getOutputStream());
	}
	
	
	// http://localhost:8080/file/profiles/fd1dc8fb-83a1-433e-803e-60749c4aaac8.jpg
	
	
	
}
