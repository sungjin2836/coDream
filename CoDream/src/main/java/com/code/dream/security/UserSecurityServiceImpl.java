package com.code.dream.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.dream.dto.RegisterDto;

@Service
@Transactional
public class UserSecurityServiceImpl implements IUserSecurityService  {

	@Autowired
	private IUserSecurityDao dao;
	
	@Autowired
	private PasswordEncoder passwordEncoding;
	
	@Override
	public UserDetails loadUserByUsername(String inputUserId) {

		// 최종적으로 리턴해야할 객체
		UserSecurityDto userDetails = new UserSecurityDto();
		System.out.println("loadUserByUsername" + inputUserId);
		// 사용자 정보 select
		System.out.println(dao);
		RegisterDto userInfo = dao.login(inputUserId);

		// 사용자 정보 없으면 null 처리
		if (userInfo == null) {
			return null;

		// 사용자 정보 있을 경우 로직 전개 (userDetails에 데이터 넣기)
		} else {
			userDetails.setUsername(userInfo.getId());
			userDetails.setPassword(userInfo.getPassword());

			// 사용자 권한 select해서 받아온 List<String> 객체 주입
			List<String> auth = dao.selectRole(inputUserId);
			userDetails.setDto(userInfo);
			userDetails.setAuthorities(auth);
		}

		return userDetails;
	}

	@Override
	public boolean regist(RegisterDto dto) {
		String enPassword = passwordEncoding.encode(dto.getPassword());
		dto.setPassword(enPassword);
		return dao.registUser(dto)&&dao.addRole(dto.getId(), "ROLE_USER");
	}
	
	public boolean idChk(String id) {
		return dao.idChk(id);
	}

	@Override
	public List<RegisterDto> selectUser() {
		return dao.selectUser();
	}

	@Override
	public boolean deleteUser(String id) {
		return dao.deleteUser(id);
	}

	@Override
	public boolean modifyUser(RegisterDto dto) {
		return dao.modifyUser(dto);
	}

	@Override
	public boolean deleteRole(String id, String role) {
		return dao.deleteRole(id, role);
	}
	
	

}
