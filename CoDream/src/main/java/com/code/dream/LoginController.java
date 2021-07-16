package com.code.dream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	public String login() {
		
		
		return "member/login";
	}
	
	@RequestMapping(value="/member/agree", method=RequestMethod.GET)
	public String agree() {
		
		
		return "member/agree";
	}
	
	@RequestMapping(value="/member/signup", method=RequestMethod.POST)
	public String signup() {
		
		
		return "member/signup";
	}
	
	@RequestMapping(value="/member/register", method=RequestMethod.POST)
	public String register() {
		
		
		return "member/login";
	}
}
