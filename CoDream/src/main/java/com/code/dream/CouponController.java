package com.code.dream;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String CouponList(Model model, Authentication authentication) {
//		UserSecurityDto usDto = (UserSecurityDto) authentication.getPrincipal();
		List<CouponDto> lists = service.CouponAll();
//		model.addAttribute("usDto", usDto);
		System.out.println(lists);
		model.addAttribute("lists", lists);
		return "coupon/CouponList";
	}
	
	@RequestMapping(value = "/coupon/memlist", method = { RequestMethod.GET, RequestMethod.POST })
	public String MemCouponList(Model model) {
		List<CouponDto> lists = service.MemCoupon();
		System.out.println(lists);
		model.addAttribute("lists", lists);
		return "coupon/member_CouponList";
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
	
	@RequestMapping(value = "coupon/kakaopay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> kakaopay(HttpSession session, String result) {
		Map<String,String> map = new HashMap<String, String>();
		System.out.println("-----------결제 시작-----------");
		try {
			URL kakaourl = new URL("https://kapi.kakao.com/v1/payment/ready"); //카카오페이 단건 결제 준비에 필요한 REST API
			HttpURLConnection kakaoserver = (HttpURLConnection) kakaourl.openConnection();
			kakaoserver.setRequestMethod("POST"); // POST로 요청
			kakaoserver.setRequestProperty("Authorization", "KakaoAK da2e0e25242d6645fdabd978c6a02c92");
			//카카오페이에서 발급받은 admin key로 권한 부여
			kakaoserver.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			// content-type 설정
			kakaoserver.setDoOutput(true);
			String amount = result;
			String param = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&"
					+ "item_name=JAVA강의&quantity=1&total_amount="+amount+"&vat_amount=200&tax_free_amount=0&"
							+ "approval_url=http://localhost:8099/coupon/list?&"
					+ "fail_url=http://localhost:8099&cancel_url=http://localhost:8099";
			// 결제에 필요한 parameter값들을 param에 넣음
			OutputStream outst = kakaoserver.getOutputStream();
			DataOutputStream dataout = new DataOutputStream(outst); 
			dataout.writeBytes(param);
			dataout.close();	
			
			int resultcode = kakaoserver.getResponseCode(); //http 응답 상태 코드
			
			InputStream inst;
			if(resultcode == 200) {
				inst = kakaoserver.getInputStream();
			}else {
				inst = kakaoserver.getErrorStream();
			}
			InputStreamReader readst = new InputStreamReader(inst);
			BufferedReader br = new BufferedReader(readst);
			String str = br.readLine();
			System.out.println("BufferedReader 값"+str);
			map.put("json", str);
			session.setAttribute("payinfo", str);
			String str2 = (String)session.getAttribute("payinfo");
			System.out.println("session 값"+str2);
			System.out.println("-----------결제 시도-----------");
			return map;
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		map.put("json", "{\"result\":\"NO\"}");
		return map;
	}
	
}
