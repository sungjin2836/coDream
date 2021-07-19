package com.code.dream;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.code.dream.oauth.SNSLogin;
import com.code.dream.oauth.SnsValue;


@Controller
public class OauthController {

	private static final Logger logger = LoggerFactory.getLogger(OauthController.class);
	
	@Autowired
	private SnsValue naverSns;
	
	@Autowired
	private SnsValue googleSns;
	
	@Autowired
	private SnsValue kakaoSns;
	
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;
	
	@Autowired
	private PersistentTokenBasedRememberMeServices rememberMeServices;
	
	@RequestMapping(value = "/login/oauth2/naver", method = {RequestMethod.GET, RequestMethod.POST})
	public String naver(Model model, HttpServletRequest request) throws IOException, InterruptedException, ExecutionException {
		String code = request.getParameter("code");
		
		// 1. code를 이용해서 access_token 받기
		// 2. access_token을 이용해서 사용자 profile 정보 가져오기
		SNSLogin snsLogin = new SNSLogin(naverSns);
		String profile = snsLogin.getUserProfile(code);
		System.out.println("Profile>>" + profile);
		model.addAttribute("result", profile);
		// 3. DB에 해당 유저가 존재하는지 체크 (googleid, naverid 컬럼 추가)
		// 4. 존재시 강제로그인, 부존재시 가입페이지로
		return "loginResult";
	}
	
	@RequestMapping(value = "/login/oauth2/google", method = {RequestMethod.GET, RequestMethod.POST})
	public String google(Model model, HttpServletRequest request) throws IOException, InterruptedException, ExecutionException {
		String code = request.getParameter("code");
		
		// 1. code를 이용해서 access_token 받기
		// 2. access_token을 이용해서 사용자 profile 정보 가져오기
		SNSLogin snsLogin = new SNSLogin(googleSns);
		String profile = snsLogin.getUserProfile(code);
		System.out.println("Profile>>" + profile);
		model.addAttribute("result", profile);
		// 3. DB에 해당 유저가 존재하는지 체크 (googleid, naverid 컬럼 추가)
		// 4. 존재시 강제로그인, 부존재시 가입페이지로
		return "loginResult";
	}
	
	@RequestMapping(value = "/login/oauth2/kakao", method = {RequestMethod.GET, RequestMethod.POST})
	public String kakao(Model model, HttpServletRequest request) throws IOException, InterruptedException, ExecutionException, URISyntaxException, ParseException {
		String code = request.getParameter("code");
		
		// 1. code를 이용해서 access_token 받기
		// 2. access_token을 이용해서 사용자 profile 정보 가져오기
		SNSLogin snsLogin = new SNSLogin(kakaoSns);
		String profile = snsLogin.getKakaoProfile(code);
		System.out.println("Profile>>" + profile);
		model.addAttribute("result", profile);
		// 3. DB에 해당 유저가 존재하는지 체크 (googleid, naverid 컬럼 추가)
		// 4. 존재시 강제로그인, 부존재시 가입페이지로
		return "loginResult";
	}
	
	@RequestMapping(value = "/loginForm.do")
	public String loginForm(Model model, HttpServletRequest request) {
		
		SNSLogin naverLogin = new SNSLogin(naverSns);
		model.addAttribute("naver_url", naverLogin.getAuthURL());
		SNSLogin kakaoLogin = new SNSLogin(kakaoSns);
		model.addAttribute("kakao_url", kakaoLogin.getAuthURL());
		
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		model.addAttribute("google_url", url);
		
		String uri = request.getHeader("Referer");
		if (!uri.contains("/loginForm.do")) {
			request.getSession().setAttribute("prevPage",
					request.getHeader("Referer"));
		}
		return "loginForm";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			rememberMeServices.logout(request, response, auth);
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}
	
	
//	@RequestMapping(value = "/login/oauth2/google")
//	public String google(String idtoken, Model model) throws GeneralSecurityException, IOException {
//		
//		return "home";
//	    
//	}
	
}
