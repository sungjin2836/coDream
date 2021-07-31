package com.code.dream.student;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.dream.dto.StudentDto;

@Repository
public class StudentDaoImpl implements IStudentDao {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);
	private final String NS = "student.";
	
	@Autowired
	private SqlSessionTemplate session;


	@Override
	public boolean insertStudent(StudentDto dto) {
		logger.info("[StudentDaoImpl] insertStudent 수강신청 {}", dto);
		int n = session.insert(NS+"insertStudent", dto);
		return (n>0)?true:false;
	}
}
