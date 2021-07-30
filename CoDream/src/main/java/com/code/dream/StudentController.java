package com.code.dream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.code.dream.dto.RegisterDto;
import com.code.dream.dto.StudentDto;
import com.code.dream.security.UserSecurityDto;
import com.code.dream.student.IStudentService;

@Controller
public class StudentController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	IStudentService iStudentService;
	
	@RequestMapping(value = "/class/studentTest", method = RequestMethod.GET)
	public String studentTest(Authentication authentication, int cl_seq) {
		logger.info("[ClassController] classMain");
		
		StudentDto sDto = new StudentDto();
		
		// 학생 아이디(로그인한 사람의 아이디)와 강의 번호를 받아 넘겨준다
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto rdto = usDto.getDto();
		String student = rdto.getId();
		
		sDto.setStudent(student);
		sDto.setCl_seq(cl_seq);
		//{"날짜":"출석여부", "날짜":"출석여부", ...}
		sDto.setVisit("{}");
		sDto.setStatus("수강중");
		
		boolean isc = iStudentService.insertStudent(sDto);
		
		return isc?"/class/classMain":"redirect:/";
	}
	
	@RequestMapping(value = "/class/classMain", method = RequestMethod.POST)
	public String checkStudent(Authentication authentication, int cl_seq) {
		logger.info("[ClassController] classMain");
		
		// 학생 아이디를 조회하여 학생이 해당 강의에 실등록한 학생인지 확인한다
		
		return "/board/classMain";
	}
	
	
}
