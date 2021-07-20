package com.code.dream.coupon;

import java.util.List;

import com.code.dream.dto.CouponDto;

public interface ICouponDao {

	public List<CouponDto> CouponAll();
	public int insertCoupon(CouponDto dto);
}
