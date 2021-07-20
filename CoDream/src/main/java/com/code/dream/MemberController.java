package com.code.dream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.code.dream.dto.RegisterDto;
import com.code.dream.oauth.IOAuthService;
import com.code.dream.security.IUserSecurityService;
import com.code.dream.security.UserSecurityDto;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private IUserSecurityService service;
	
	@Autowired
	private IOAuthService auth;
	
	@RequestMapping(value="agree", method=RequestMethod.GET)
	public String agree() {
		return "member/agree";
	}
	
	@RequestMapping(value="signup", method=RequestMethod.POST)
	public String signup(Model model, HttpServletRequest request) {
		model.addAttribute("site", "normal");
		return "member/signup";
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String register(HttpServletRequest request, RegisterDto dto) {
		System.out.println(dto);
		String site = request.getParameter("site");
		// adrecieve는 체크시 on 아닐시 null 처리.
		if(dto.getAddress()!=null) {
			dto.setAdrecieve("Y");
		} else {
			dto.setAdrecieve("N");
		}
		
		service.regist(dto);
		if(!site.equals("normal")) {
			String uid = request.getParameter("uid");
			auth.registOAuth(site, dto.getId(), uid);
		}
		
		return "member/login";
	}
	
	@RequestMapping(value="idChk", method=RequestMethod.POST)
	@ResponseBody
	public String idChk(String id) {
		boolean isc = false;
		isc = service.idChk(id);
		return String.valueOf(isc);
	}
	
	
}
