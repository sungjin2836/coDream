package com.code.dream.student;

import com.code.dream.dto.StudentDto;

public interface IStudentService {
	
	// 수강 신청
	public boolean insertStudent(StudentDto dto);

}
