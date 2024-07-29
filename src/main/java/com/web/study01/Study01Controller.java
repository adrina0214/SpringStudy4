package com.web.study01;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.components.FileComponent;
import com.web.mapper.Study01DTO;
import com.web.mapper.Study01Mapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/s1")
public class Study01Controller {

	@GetMapping("/")
	public String index() {
		return "s1/index";
	}
	
	@Autowired
	private FileComponent fc;
	
	@PostMapping("/fileUpload")
	public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes ra) {
		ra.addAttribute("url", fc.upload(file));
	    return "redirect:/s1/view";
	}
	
	@GetMapping("/view")
	public ResponseEntity<?> view(@RequestParam("url") String url) {
		return fc.getFile(url);
	}
	
	@PostMapping("/file")
	public String file(@RequestParam("file") MultipartFile file) {
		Map<String, Object> map = fc.setFile(file);
		System.out.println(map);
		/*********************************************************
		 * {
		 * 	> LastPath=/upload, 
		 *  > Extension=.png, 
		 *  > Root=C:\IDE\sts-4.22.1.RELEASE\work\SpringStudy4, 
		 *  NewName=495442386388300, 
		 *  > CurrnetDate=/20240716, 
		 *  > Middle=/src/main/resources/static, 
		 *  > Name=대기화면(시간표).png
		 * }
		 * 
		 * C:\IDE\sts-4.22.1.RELEASE\work\SpringStudy4 /src/main/resources/static /upload
		 * Root + Middle + LastPath  << 절대 폴더 위치
		 * 
		 * /20240716 /대기화면(시간표).png
		 * CurrnetDate + Name  	  << 폴더 관리 및 파일명 >> URL 주소
		 * 
		 * /20240716 /495442386388300
		 * CurrnetDate + NewName  << 폴더 관리 및 파일명 >> URL 주소
		 *********************************************************/
		return null;
	}
	
	@PostMapping("/test1")
	public String test1(@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		long fileSize = file.getSize();
		String contentType = file.getContentType();
		log.info("FileName : {}", fileName);
		log.info("FileSize : {}", fileSize);
		log.info("ContentType : {}", contentType);
		
		// 파일 저장 경로가 필요하기 때문에 생성해야 한다.
		String path = "c:\\upload\\2024-07-16\\";
		try {
			File f = new File(path + fileName);
			f.mkdirs(); // 파일 저장할 경로 생성
			file.transferTo(f);  // 파일 복사 내용 생성
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//c:/upload/2024-07-16/1.jpeg << URL 주소가 없다.
		// /upload/2024-07-16/1.jpeg
		
		// c:/upload
		return null;
	}
	
	@Autowired
	private Study01Mapper s1Mapper;
	
	@PostMapping("/data")
	public ResponseEntity<?> data(@RequestParam("file") MultipartFile file, RedirectAttributes ra) throws IOException {
		Study01DTO dto = Study01DTO.builder()
				.file(file.getBytes()).type(file.getContentType()).size(file.getSize()).build();
		int status = s1Mapper.save(dto);
		log.info("Status : {}", status);
		try {
		      return ResponseEntity.ok()
		        .contentLength(dto.getSize())
		        .contentType(MediaType.parseMediaType(dto.getType()))
		        .body(dto.getFile());
		    } catch (Exception e) {
		      e.printStackTrace();
		      return ResponseEntity.notFound().build();
		    }
	}
	
	@GetMapping("/data/{no:[0-9]+}")
	public ResponseEntity<?> view(@PathVariable("no") int no) {
		Study01DTO dto = s1Mapper.findByNo(no);
		try {
		      return ResponseEntity.ok()
		        .contentLength(dto.getSize())
		        .contentType(MediaType.parseMediaType(dto.getType()))
		        .body(dto.getFile());
		    } catch (Exception e) {
		      e.printStackTrace();
		      return ResponseEntity.notFound().build();
		    }
	}
	
}
