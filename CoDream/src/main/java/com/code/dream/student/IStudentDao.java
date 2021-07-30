package com.code.dream.student;

import com.code.dream.dto.StudentDto;

public interface IStudentDao {
	// 수강 신청
	public boolean insertStudent(StudentDto dto);
	
}
