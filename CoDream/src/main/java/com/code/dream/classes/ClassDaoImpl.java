package com.code.dream.classes;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.dream.dto.ClassDto;
import com.code.dream.dto.RegisterDto;
import com.code.dream.dto.StudentDto;

@Repository
public class ClassDaoImpl implements IClassDao{
	
	private static final Logger logger = LoggerFactory.getLogger(ClassDaoImpl.class);
	private final String NS = "class.";
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public boolean insertClass(ClassDto dto) {
		logger.info("[ClassDaoImpl] insertClass 강의 개설 {}", dto);
		int n = session.insert(NS+"insertClass", dto);
		return (n>0)?true:false;
	}

	@Override
	public boolean insertHash(String value) {
		logger.info("[ClassDaoImpl] insertHash 신규 해시 등록 {}", value);
		int n = session.insert(NS+"insertHash", value);
		return (n>0)?true:false;
	}

	@Override
	public List<Map<Integer, String>> checkHash(String value) {
		logger.info("[ClassDaoImpl] 해시 존재여부 검색 {}", value);
		return session.selectList(NS+"checkHash",value);
	}

	@Override
	public List<ClassDto> hashedClassList(String value) {
		logger.info("[ClassDaoImpl] 해시별 강의 조회 {}", value);
		return session.selectList(NS+"hashedClassList", value);
	}

	@Override
	public List<String> linkHashList(int cl_seq) {
		logger.info("[ClassDaoImpl] 강의별 해시 조회 {}", cl_seq);
		return session.selectList(NS+"linkHashList", cl_seq);
	}

	@Override
	public boolean updateLinkHash(Map<String, Integer> linkhashMap) {
		logger.info("[ClassDaoImpl] 강의 해시 등록 {}", linkhashMap);
		int n = session.insert(NS+"updateLinkHash", linkhashMap);
		return (n>0)?true:false;
	}

	@Override
	public List<ClassDto> classList() {
		logger.info("[ClassDaoImpl] classList 개설 강의 조회");
		return session.selectList(NS+"classList");
	}

	@Override
	public ClassDto classDetail(int cl_seq) {
		logger.info("[ClassDaoImpl] classDetail 강의 상세 조회 {}", cl_seq);
		List<ClassDto> lists = session.selectList(NS+"classDetail", cl_seq);
		return (lists.size() > 0)?lists.get(0):null;
	}

	@Override
	public boolean insertStudent(StudentDto dto) {
		logger.info("[ClassDaoImpl] insertStudent 수강신청 {}", dto);
		int n = session.insert(NS+"insertStudent", dto);
		return (n>0)?true:false;
	}

	@Override
	public List<RegisterDto> emailList() {
		logger.info("[ClassDaoImpl] emailList 수신동의 리스트 조회");
		return session.selectList(NS+"emailList");
	}

	@Override
	public List<ClassDto> cheapestClass(Map<String, String[]> map) {
		logger.info("[ClassDaoImpl] cheapestClass 해시 최저가 강의 조회");
		return session.selectList(NS+"cheapestClass", map);
	}

}
