package com.code.dream.coupon;

import java.util.List;

import com.code.dream.dto.CouponDto;

public interface ICouponService {

	public List<CouponDto> CouponAll();
	public int insertCoupon(CouponDto dto);
	public List<CouponDto> MemCoupon();
	public int insertMemCoupon(CouponDto dto);
}
