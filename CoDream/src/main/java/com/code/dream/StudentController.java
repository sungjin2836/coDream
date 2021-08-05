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
		
		// 학생 아이디(로그인한 사람의 아이디)와 강의 번호를 받아 넘겨준다
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto rdto = usDto.getDto();
		String student = rdto.getId();
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("student", student);
		map.put("cl_seq", String.valueOf(cl_seq));
		
		boolean isc = iStudentService.dropStudent(map);
		
		return isc?"/board/classList":"redirect:/";
	}
	
	@RequestMapping(value = "/class/classMain", method = RequestMethod.GET)
	public String classMain(Authentication authentication, int cl_seq, Model model) {
		logger.info("[StudentController] classMain");
		
		if(checkAccess(authentication, cl_seq)) { // 만약, 해당 강의에 등록한 학생이라면
			// 강의에 대한 데이터를 받아와서 화면에 뿌려줌	
			ClassDto cDto = iClassService.classDetail(cl_seq); // 강의 정보를 가져옴		
			model.addAttribute("cDto", cDto);
			
			// 강의자료와 필기자료 조회
			model.addAttribute("dList",iStudentService.recentDocList(cl_seq));
			model.addAttribute("mList",iStudentService.recentMemoList(cl_seq));
			
			return "/class/classMain";
		} else { // 아니라면, 잘못된 접근으로 처리함
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/class/memoList", method = RequestMethod.GET)
	public String memoList(Authentication authentication, Model model, int cl_seq) {
		logger.info("[StudentController] memoList");		
		
		if(checkAccess(authentication, cl_seq)) { // 만약, 해당 강의에 등록한 학생이라면
			// 강의에 대한 데이터를 받아와서 화면에 뿌려줌	
			ClassDto cDto = iClassService.classDetail(cl_seq); // 강의 정보를 가져옴		
			model.addAttribute("cDto", cDto);
			
			List<MemoDto> list = iStudentService.memoList(cl_seq);
			model.addAttribute("list", list);
			
			return "/class/memoList";
		}else { // 아니라면, 잘못된 접근으로 처리함
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value = "/class/docList", method = RequestMethod.GET)
	public String docList(Authentication authentication, Model model, int cl_seq) {
		logger.info("[StudentController] docList");			
		
		if(checkAccess(authentication, cl_seq)) { // 만약, 해당 강의에 등록한 학생이라면
			// 강의에 대한 데이터를 받아와서 화면에 뿌려줌	
			ClassDto cDto = iClassService.classDetail(cl_seq); // 강의 정보를 가져옴		
			model.addAttribute("cDto", cDto);
			
			List<DocumentDto> list = iStudentService.docList(cl_seq);
			model.addAttribute("list", list);
			
			return "/class/docList";
		}else { // 아니라면, 잘못된 접근으로 처리함
			return "redirect:/";
		}
		
	}
	
	// 강의의 관련자 혹은 관리자만 강의에 접근할 수 있도록 한다
	private boolean checkAccess(Authentication authentication, int cl_seq) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		// 학생 아이디(로그인한 사람의 아이디)와 강의 번호를 받아서
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto rdto = usDto.getDto();
		String id = rdto.getId();
		
		map.put("id", id);
		map.put("cl_seq", String.valueOf(cl_seq));
		
		// 강의에 실제로 등록한 사람인가? 혹은, 해당 강의의 강사인가?
		boolean isc = iStudentService.checkStudent(map)||iClassService.checkTeacher(map);
		
		return isc;
	}
	
	
}
