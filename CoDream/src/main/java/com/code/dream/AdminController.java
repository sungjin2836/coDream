package com.code.dream;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.code.dream.dto.RegisterDto;
import com.code.dream.security.IUserSecurityService;
import com.code.dream.security.UserSecurityDto;


@Controller
@RequestMapping("/admin/")
public class AdminController {
	
	@Autowired
	private IUserSecurityService service;
	
//	@RequestMapping(value="/myInfo", method=RequestMethod.GET)
//	public String myInfo(Model model, Authentication authentication) {
//		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
//		RegisterDto dto = usDto.getDto();
//		dto.setPassword(null);
//		model.addAttribute("dto", dto);
//		return "admin/myInfo";
//	}
	
	@RequestMapping(value="/admin/memberList", method=RequestMethod.GET)
	public String memberList(Model model) {
		List<UserSecurityDto> list = service.selectUserList();
		
		model.addAttribute("list", list);
		
		return "admin/memberList";
	}
	
	@RequestMapping(value="deleteUser", method=RequestMethod.POST)
	@ResponseBody
	public String deleteUser(String id) {
		boolean isc = false;
		isc = service.deleteUser(id);
		return String.valueOf(isc);
	}
	
	@RequestMapping(value="changeRole", method=RequestMethod.POST)
	@ResponseBody
	public String changeRole(String id, String role) {
		int nowLevel = service.selectRole(id).size();
		String[] roleList = {"ROLE_USER", "ROLE_TEACHER", "ROLE_ADMIN"};
		System.out.println(id + "//" + role);
		int goalLevel = 0;
		for(int i = 0; i<roleList.length; i++) {
			if(role.equals(roleList[i])) {
				goalLevel = i+1;
			}
		}
		boolean isc = false;
		while(nowLevel != goalLevel) {
			if(nowLevel > goalLevel) {
				isc = service.deleteRole(id, roleList[--nowLevel]);
			} else {
				isc = service.addRole(id, roleList[nowLevel++]);
			}
			if(!isc) {
				break;
			}
		}
		
		return String.valueOf(isc);
	}
}
