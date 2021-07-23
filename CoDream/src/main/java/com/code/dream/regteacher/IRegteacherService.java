package com.code.dream.regteacher;

import java.util.List;

import com.code.dream.dto.AttachFileDto;
import com.code.dream.dto.RegteacherDto;

public interface IRegteacherService {

	public boolean insertRegteacher(RegteacherDto dto);
	public List<RegteacherDto> selectRegteacher();
	public RegteacherDto detailRegteacher(String te_seq);
	public boolean modifyRegteacher(String te_admit, String te_seq);
	public List<AttachFileDto> selectFiles(String file_gid);
}