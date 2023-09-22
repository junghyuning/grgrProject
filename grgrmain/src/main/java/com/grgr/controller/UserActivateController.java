package com.grgr.controller;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grgr.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Slf4j
public class UserActivateController {
	private final UserService userService;
	
	@PostMapping("/activateUser/{loginUno}")
	public ResponseEntity<String> activateUserController(@PathVariable int loginUno, @RequestParam("email") String email, HttpSession session){
		log.info("email" + email);
		int result = userService.activateUser(loginUno, email);
		if(result < 1) {
			log.info("resutl<1");
			return new ResponseEntity<String> ("denied",HttpStatus.OK);
		}
		
		session.setAttribute("loginActive", session.getAttribute("loginActive"));
		log.info("success");
		return new ResponseEntity<String> ("success",HttpStatus.OK);
	}
	
	
}
