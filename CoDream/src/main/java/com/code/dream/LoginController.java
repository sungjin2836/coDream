package com.code.dream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.code.dream.dto.RegisterDto;
import com.code.dream.security.IUserSecurityService;

@Controller
public class LoginController {

	@Autowired
	private IUserSecurityService service;
	
	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	public String login() {
		return "member/login";
	}
	
	@RequestMapping(value="/member/agree", method=RequestMethod.GET)
	public String agree() {
		return "member/agree";
	}
	
	@RequestMapping(value="/member/signup", method=RequestMethod.POST)
	public String signup(Model model, HttpServletRequest request) {
		return "member/signup";
	}
	
	@RequestMapping(value="/member/register", method=RequestMethod.POST)
	public String register(HttpServletRequest request, RegisterDto dto) {
		System.out.println(dto);
		
		// adrecieve는 체크시 on 아닐시 null 처리.
		if(dto.getAddress()!=null) {
			dto.setAdrecieve("Y");
		} else {
			dto.setAdrecieve("N");
		}
		
		service.regist(dto);
		
		
		return "member/login";
	}
	
	@RequestMapping(value="/member/idChk", method=RequestMethod.POST)
	@ResponseBody
	public String idChk(String id) {
		boolean isc = false;
		isc = service.idChk(id);
		return String.valueOf(isc);
	}
}
