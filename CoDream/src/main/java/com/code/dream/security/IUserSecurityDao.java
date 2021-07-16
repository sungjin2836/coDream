package com.code.dream.security;

import java.util.List;

import com.code.dream.dto.RegisterDto;

public interface IUserSecurityDao {

	public boolean registUser(RegisterDto dto);
	public RegisterDto login(String id);
	public List<RegisterDto> selectUser();
	public boolean deleteUser(String id);
	public boolean modifyUser(RegisterDto dto);
	public List<String> selectRole(String id);
	public boolean addRole(String id, String role);
	public boolean deleteRole(String id, String role);

}
