package com.code.dream.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dream.dto.StudentDto;

@Service
public class StudentServiceImpl implements IStudentService {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired
	IStudentDao dao;
	
	@Override
	public boolean insertStudent(StudentDto dto) {
		logger.info("[StudentServiceImpl] insertStudent 수강신청 {}", dto);
		return dao.insertStudent(dto);
	}

}
