package com.code.dream.coupon;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.dream.dto.CouponDto;

@Repository
public class CouponDaoImpl implements ICouponDao {

	@Autowired
	private SqlSessionTemplate sqlsession;
	
	@Override
	public List<CouponDto> CouponAll() {
		return sqlsession.selectList("coupon.selectCoupon");
	}
	
	@Override
	public int insertCoupon(CouponDto dto) {
		return sqlsession.insert("coupon.insertCoupon", dto);
	}
	
	@Override
	public List<CouponDto> MemCoupon() {
		return sqlsession.selectList("coupon.selectMemCoupon");
	}
	
	@Override
	public int insertMemCoupon(CouponDto dto) {
		return sqlsession.insert("coupon.insertMemCoupon", dto);
	}
	
}
