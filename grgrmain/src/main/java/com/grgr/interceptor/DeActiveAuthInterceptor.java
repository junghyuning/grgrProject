package com.grgr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DeActiveAuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();


		Integer loginActive = (Integer) session.getAttribute("loginActive");
		// 휴면상태의 사용자의 경우
		if (loginActive != 2) {
			return true;
		}


		response.sendRedirect(request.getContextPath() + "/mypage/activateUser");
		return false;
	}

}