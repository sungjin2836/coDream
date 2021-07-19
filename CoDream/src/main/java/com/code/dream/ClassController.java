package com.code.dream;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.code.dream.classes.IClassService;
import com.code.dream.dto.ClassDto;

@Controller
public class ClassController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassController.class);
	
	@Autowired
	IClassService iClassService;
	
	@RequestMapping(value = "/board/classList", method = RequestMethod.GET)
	public String classList(Model model) {
		logger.info("[ClassController] classList 강의 전체 조회 페이지");
		
		// ClassDto에 조회 결과값을 받아 Model에 넘겨줌 
		List<ClassDto> cList = iClassService.classList();
		
		model.addAttribute("cList",cList);
		
		return "/board/classList";
	}
	
	

/*
 * 강의 개설
신규 해시 등록
해시태그 검색
해시태그별 강의 조회
강의 해시 조회
강의 해시 등록
강의 전체 조회
강의 상세 조회
수강 신청
알림 서비스
 * */
	
}
