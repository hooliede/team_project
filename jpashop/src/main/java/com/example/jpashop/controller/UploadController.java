package com.example.jpashop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.jpashop.repository.AttachRepository;
import com.example.jpashop.util.UploadFileUtils;

import jakarta.transaction.Transactional;

@Controller
public class UploadController {
	

	@Autowired
	AttachRepository attachRepository;
	
	String upload_path = "c:/upload";
	
	@ResponseBody
	@PostMapping(value = "/upload/ajax_upload", produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> ajax_upload(@RequestParam(name = "file") MultipartFile file) throws Exception {
		String filename = UploadFileUtils.uploadFile(upload_path, file.getOriginalFilename(), file.getBytes());
		return new ResponseEntity<String>(filename, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/upload/display_file")
	public ResponseEntity<byte[]> display_file(@RequestParam(name = "file_name") String file_name)  {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(file_name);
			file_name = file_name.substring(file_name.indexOf("_") +1);
			file_name = new String(file_name.getBytes("utf-8"), "iso-8859-1");
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment; filename=\"" + file_name + "\" ");
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
		
	}
	
	@Transactional
	@ResponseBody
	@PostMapping("/upload/delete_file")
	public ResponseEntity<String> delete_file(@RequestParam(name = "file_name") String file_name) {
		new File(file_name.replace("/", File.separator)).delete();
		attachRepository.deleteByFileName(file_name);
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	 
	
}
