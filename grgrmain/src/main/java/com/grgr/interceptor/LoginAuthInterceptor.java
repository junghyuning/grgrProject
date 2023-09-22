package com.grgr.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginAuthInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		Object loginUno = session.getAttribute("loginUno");
		

		if (loginUno == null) {
			String requestURI = request.getRequestURI();
			String queryString = request.getQueryString(); // 쿼리 스트링 가져오기
			
			String returnUrl ="";
			if (queryString != null) {
				returnUrl=requestURI+"?"+queryString;
			}

			session.setAttribute("requestUri", requestURI);

			// 로그인 페이지로 리다이렉트할 때, 원래의 URL을 쿼리 스트링으로 함께 보냅니다.
			returnUrl=URLEncoder.encode(returnUrl, "utf-8");
			response.sendRedirect(request.getContextPath()+ "/user/login"); //?returnUrl=\" + returnUrl
			
			return false;
		}

		return true;
	}

}
