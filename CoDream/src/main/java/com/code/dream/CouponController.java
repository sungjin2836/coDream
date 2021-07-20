package com.code.dream;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.code.dream.coupon.ICouponService;
import com.code.dream.dto.CouponDto;

@Controller
public class CouponController {

	@Autowired
	private ICouponService service;

	@RequestMapping(value = "/coupon/insertpage", method = { RequestMethod.GET, RequestMethod.POST })
	public String CouponPage() {
		return "coupon/insertCoupon";
	}

	@RequestMapping(value = "/coupon/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String CouponList(Model model) {
		List<CouponDto> lists = service.CouponAll();
		System.out.println(lists);
		model.addAttribute("lists", lists);
		return "coupon/CouponList";
	}

	@RequestMapping(value = "/coupon/input", method = { RequestMethod.GET, RequestMethod.POST })
	public String Couponinput(HttpServletRequest req) {
		System.out.println(req.getParameter("couponname"));
		System.out.println(req.getParameter("discount"));
		System.out.println(req.getParameter("maxprice"));
		System.out.println(req.getParameter("status"));
		
		 String COUPONNAME = req.getParameter("couponname");
		 int DISCOUNT = Integer.parseInt(req.getParameter("discount"));
		 int MAXPRICE = Integer.parseInt(req.getParameter("maxprice"));
		 String STATUS = req.getParameter("status");
		 CouponDto dto = new CouponDto();
		 dto.setCouponname(COUPONNAME);
		 dto.setDiscount(DISCOUNT);
		 dto.setMaxprice(MAXPRICE);
		 dto.setStatus(STATUS);
		 System.out.println(dto);
		
		service.insertCoupon(dto);
		
		
		

		return "coupon/insertCoupon";
	}
}
