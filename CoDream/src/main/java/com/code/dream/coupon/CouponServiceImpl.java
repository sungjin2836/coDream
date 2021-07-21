package com.code.dream.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dream.dto.CouponDto;

@Service
public class CouponServiceImpl implements ICouponService {

	@Autowired
	private ICouponDao dao;
	
	@Override
	public List<CouponDto> CouponAll() {
		
		return dao.CouponAll();
	}
	
	@Override
	public int insertCoupon(CouponDto dto) {
		return dao.insertCoupon(dto);
	}
	
	@Override
	public List<CouponDto> MemCoupon() {
		return dao.MemCoupon();
	}
	
	@Override
	public int insertMemCoupon(CouponDto dto) {
		return dao.insertMemCoupon(dto);
	}
	
}
