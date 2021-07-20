package com.code.dream.dto;

public class CouponDto {

	private String coupon_seq         ;
	private String couponname         ;
	private int discount           ;
	private int maxprice           ;
	private String status             ;
	
	public CouponDto() {
		// TODO Auto-generated constructor stub
	}

	public String getCoupon_seq() {
		return coupon_seq;
	}

	public void setCoupon_seq(String coupon_seq) {
		this.coupon_seq = coupon_seq;
	}

	public String getCouponname() {
		return couponname;
	}

	public void setCouponname(String couponname) {
		this.couponname = couponname;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(int maxprice) {
		this.maxprice = maxprice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CouponDto(String coupon_seq, String couponname, int discount, int maxprice, String status) {
		super();
		this.coupon_seq = coupon_seq;
		this.couponname = couponname;
		this.discount = discount;
		this.maxprice = maxprice;
		this.status = status;
	}

	@Override
	public String toString() {
		return "CouponDto [coupon_seq=" + coupon_seq + ", couponname=" + couponname + ", discount=" + discount
				+ ", maxprice=" + maxprice + ", status=" + status + "]";
	}

	
}
