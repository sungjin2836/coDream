package com.code.dream;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.code.dream.dto.RegisterDto;
import com.code.dream.security.UserSecurityDto;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	
	@RequestMapping(value="/myInfo", method=RequestMethod.GET)
	public String myInfo(Model model, Authentication authentication) {
		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		RegisterDto dto = usDto.getDto();
		dto.setPassword(null);
		model.addAttribute("dto", dto);
		return "admin/myInfo";
	}
	
	
}
