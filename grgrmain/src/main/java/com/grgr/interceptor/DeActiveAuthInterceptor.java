package com.grgr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DeActiveAuthInterceptor implements HandlerInterceptor {


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		Integer loginActive = (Integer)session.getAttribute("loginActive"); 
		//휴면상태의 사용자의 경우
		if(loginActive == 2) {
			
			response.sendRedirect(request.getContextPath()+"/user/active");
		}
	}
}
