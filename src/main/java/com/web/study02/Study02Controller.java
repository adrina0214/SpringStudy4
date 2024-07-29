package com.web.study02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.components.FileComponent;

@Controller
@RequestMapping("/s2")
public class Study02Controller {

	@GetMapping("/")
	public String index() {
		return "s2/index";
	}
	
	@Autowired
	private FileComponent fc;
	
	@PostMapping("/fileUpload")
	public String fileUpload(@ModelAttribute FileDTO fileDto, Model model) {
		model.addAttribute("url", fc.upload2(fileDto.getFile()));
		model.addAttribute("title", fileDto.getTitle());
		model.addAttribute("content", fileDto.getContent());
	    return "s2/view";
	}
	
}
