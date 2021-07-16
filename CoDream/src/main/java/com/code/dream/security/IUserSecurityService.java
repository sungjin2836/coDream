package com.code.dream.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.code.dream.dto.RegisterDto;

public interface IUserSecurityService extends UserDetailsService  {

	public boolean regist(RegisterDto dto);
	public List<RegisterDto> selectUser();
	public boolean deleteUser(String id);
	public boolean modifyUser(RegisterDto dto);
	public boolean deleteRole(String id, String role);
}
