package com.code.dream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class MemberController {
	
	@Autowired
	private IUserSecurityService service;
	
	@Autowired
	private IOAuthService auth;
	
	@RequestMapping(value="/member/agree", method=RequestMethod.GET)
	public String agree() {
		return "member/agree";
	}
	
	@RequestMapping(value="/member/signup", method=RequestMethod.POST)
	public String signup(Model model, HttpServletRequest request) {
		model.addAttribute("site", "normal");
		return "member/signup";
	}
	
	@RequestMapping(value="/member/register", method=RequestMethod.POST)
	public String register(HttpServletRequest request, RegisterDto dto) {
		System.out.println(dto);
		String site = request.getParameter("site");
		// adrecieve는 체크시 on 아닐시 null 처리.
		if(dto.getAdrecieve()!=null) {
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
	
	@RequestMapping(value="/getName", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getName(Authentication authentication) {
		if(authentication != null) {
			UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
			RegisterDto dto = usDto.getDto();
			return dto.getName();
		}
		return null;
	}
	
	@RequestMapping(value="/member/idChk", method=RequestMethod.POST)
	@ResponseBody
	public String idChk(String id) {
		boolean isc = false;
		isc = service.idChk(id);
		return String.valueOf(isc);
	}
	
	@RequestMapping(value="/myInfo", method=RequestMethod.GET)
	public String myInfo(Model model, Authentication authentication) {
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto dto = usDto.getDto();
		dto.setPassword(null);
		model.addAttribute("dto", dto);
		return "member/myInfo";
	}
	
	@RequestMapping(value="/modifyForm", method=RequestMethod.GET)
	public String modifyForm(Model model, Authentication authentication) {
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto dto = usDto.getDto();
		dto.setPassword(null);
		model.addAttribute("dto", dto);
		return "member/modifyForm";
	}
	
	@RequestMapping(value="/modifyInfo", method=RequestMethod.POST)
	public String modifyInfo(Model model, RegisterDto dto, Authentication authentication, HttpServletRequest request) {
		dto.setId(authentication.getName());
		
		if(dto.getAdrecieve()!=null) {
			dto.setAdrecieve("Y");
		} else {
			dto.setAdrecieve("N");
		}
		
		return "redirect:/myInfo";
	}
	
}
