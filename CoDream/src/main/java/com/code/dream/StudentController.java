package com.code.dream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.code.dream.classes.IClassService;
import com.code.dream.dto.ClassDto;
import com.code.dream.dto.DocumentDto;
import com.code.dream.dto.MemoDto;
import com.code.dream.dto.RegisterDto;
import com.code.dream.dto.StudentDto;
import com.code.dream.security.UserSecurityDto;
import com.code.dream.student.IStudentService;

@Controller
public class StudentController {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	IStudentService iStudentService;
	
	@Autowired
	IClassService iClassService;
	
	@RequestMapping(value = "/class/studentTest", method = RequestMethod.GET)
	public String studentTest(Authentication authentication, int cl_seq) {
		logger.info("[StudentController] classMain");
		
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
	
	@RequestMapping(value = "/class/classMain", method = RequestMethod.GET)
	public String classMain(Authentication authentication, int cl_seq, Model model) {
		logger.info("[StudentController] classMain");
		
		Map<String, String> map = new HashMap<String, String>();
		
		// 학생 아이디(로그인한 사람의 아이디)와 강의 번호를 받아서
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto rdto = usDto.getDto();
		String id = rdto.getId();
		
		map.put("id", id);
		map.put("cl_seq", String.valueOf(cl_seq));
		
		// 강의에 실제로 등록한 사람인가? 혹은, 해당 강의의 강사인가?
		boolean isc = iStudentService.checkStudent(map)||iClassService.checkTeacher(map);
		
		if(isc) { // 만약, 해당 강의에 등록한 학생이라면
			// 강의에 대한 데이터를 받아와서 화면에 뿌려줌	
			ClassDto cDto = iClassService.classDetail(cl_seq); // 강의 정보를 가져옴		
			model.addAttribute("cDto", cDto); // 강의 번호
			
			// 강의자료와 필기자료 조회
			model.addAttribute("dList",iStudentService.recentDocList(cl_seq));
			model.addAttribute("mList",iStudentService.recentMemoList(cl_seq));
			
			return "/class/classMain";
		} else { // 아니라면, 잘못된 접근으로 처리함
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/class/memoList", method = RequestMethod.GET)
	public String memoList(Model model, int cl_seq) {
		logger.info("[StudentController] memoList");		
		
		List<MemoDto> list = iStudentService.memoList(cl_seq);
		model.addAttribute("cl_seq", cl_seq);
		model.addAttribute("list", list);
		
		return "/class/memoList";
	}
	
	@RequestMapping(value = "/class/docList", method = RequestMethod.GET)
	public String docList(Model model, int cl_seq) {
		logger.info("[StudentController] docList");	
		
		List<DocumentDto> list = iStudentService.docList(cl_seq);	
		
		model.addAttribute("cl_seq", cl_seq);
		model.addAttribute("list", list);
		
		return "/class/docList";
	}
	
	
	
	
}
