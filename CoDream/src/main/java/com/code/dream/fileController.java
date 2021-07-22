package com.code.dream;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file/")
public class fileController {
	
	
	
	@RequestMapping(value="upload", method=RequestMethod.POST)
	@ResponseBody
	public String upload(List<MultipartFile> files) {
		return null;
	}
}
