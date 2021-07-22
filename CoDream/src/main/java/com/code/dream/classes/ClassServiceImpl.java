package com.code.dream.classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.dream.dto.ClassDto;
import com.code.dream.dto.RegisterDto;
import com.code.dream.dto.StudentDto;

@Service
public class ClassServiceImpl implements IClassService {
	
	private static final Logger logger = LoggerFactory.getLogger(ClassServiceImpl.class);
	
	@Autowired
	IClassDao dao;
	
	@Override
	@Transactional
	public boolean insertClass(ClassDto dto, String hash) {
		logger.info("[ClassServiceImpl] insertClass 강의 개설 {}", dto);
		
		boolean isc = dao.insertClass(dto)&&updateLinkHash(dto.getCl_seq(), hash);
		
		return isc;
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	@Transactional
	public boolean updateLinkHash(int cl_seq, String values) {
		logger.info("[ClassServiceImpl] 강의 해시 등록 {} : {}", cl_seq, values);
		
		String[] valueArray = (values.trim()).split(" ");
		boolean isc = false;
		
		for(String value : valueArray) {
			Map<String, Integer> linkhashMap = new HashMap<String, Integer>(); 
			
			// 해시 존재여부 검색 후
			List<Map<Integer, String>> hashList = dao.checkHash(value);
			logger.info("[ClassServiceImpl] hashList : {}", hashList);
			
			if(hashList.size() != 0) {
				
				// 존재할 경우, 해당 해시의 번호를 가져와서
				Map<Integer, String> hash = hashList.get(0);
				String hash_seq = String.valueOf(hash.get("hash_seq"));
				linkhashMap.put("cl_seq", cl_seq);
				linkhashMap.put("hash_seq", Integer.parseInt(hash_seq));
				logger.info("linkhashMap : {}",linkhashMap);
				
				// 등록
				isc = dao.updateLinkHash(linkhashMap);
				
			} else {
				// 존재하지 않을 경우
				
				//신규 해시를 등록한 뒤 
				if(dao.insertHash(value)){
					// 해당 seq를 받아와서
					Map<Integer, String> hash = (dao.checkHash(value)).get(0);
					String hash_seq = String.valueOf(hash.get("hash_seq"));
					linkhashMap.put("cl_seq", cl_seq);
					linkhashMap.put("hash_seq", Integer.parseInt(hash_seq));
					logger.info("linkhashMap : {}",linkhashMap);
					
					// 등록
					isc = dao.updateLinkHash(linkhashMap);
				}
			}
		}
		
		return isc;
	}

	@Override
	public List<ClassDto> hashedClassList(String value) {
		logger.info("[ClassServiceImpl] 해시별 강의 조회 {}", value);
		return dao.hashedClassList(value);
	}

	@Override
	public List<String> linkHashList(int cl_seq) {
		logger.info("[ClassServiceImpl] 강의별 해시 조회 {}", cl_seq);
		return dao.linkHashList(cl_seq);
	}

	@Override
	public List<ClassDto> classList() {
		logger.info("[ClassServiceImpl] classList 개설 강의 조회");
		return dao.classList();
	}

	@Override
	public ClassDto classDetail(int cl_seq) {
		logger.info("[ClassServiceImpl] classDetail 강의 상세 조회 {}", cl_seq);
		return dao.classDetail(cl_seq);
	}

	@Override
	public boolean insertStudent(StudentDto dto) {
		logger.info("[ClassServiceImpl] insertStudent 수강신청 {}", dto);
		return dao.insertStudent(dto);
	}

	@Override
	public List<RegisterDto> emailList() {
		logger.info("[ClassServiceImpl] emailList 수신동의 리스트 조회");
		return dao.emailList();
	}

	@Override
	public boolean insertHash(String value) {
		logger.info("[ClassServiceImpl] insertHash 신규 해시 등록");
		return dao.insertHash(value);
	}

	@Override
	public List<Map<Integer, String>> checkHash(String value) {
		logger.info("[ClassServiceImpl] checkHash 해시 조회");
		return dao.checkHash(value);
	}
}
