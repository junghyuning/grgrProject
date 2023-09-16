package com.grgr.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AdminAuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();		
		if(session.getAttribute("loginUserStatus")==null) {
			response.sendRedirect(request.getContextPath()+"/404");
			return false;
		}
		
		Integer loginUserStatus =(Integer)session.getAttribute("loginUserStatus"); 
		//int loginActive = (int)session.getAttribute("loginActive");
		if(loginUserStatus != 1) {
			response.sendRedirect(request.getContextPath()+"/404");
			return false;
		}
		
		return true;
	}
	
	

}
