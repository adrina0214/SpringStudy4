package com.web.study03;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.components.FileComponent;
import com.web.study02.FileDTO;

@RestController
@RequestMapping("/s3")
public class Study03DataController {
	
	@Autowired
	private FileComponent fc;

	@PostMapping("/fileUpload")
	public Map<String, Object> fileUpload(@ModelAttribute FileDTO fileDto) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("url", fc.upload2(fileDto.getFile()));
		resultMap.put("title", fileDto.getTitle());
		resultMap.put("content", fileDto.getContent());
	    return resultMap;
	}
	
}
