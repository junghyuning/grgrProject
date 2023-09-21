package com.grgr.controller;

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

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class UserActivateController {
	private final UserService userService;
	
	@PostMapping("/activateUser/{loginUno}")
	public ResponseEntity<String> activateUserController(@PathVariable int loginUno, @RequestParam("email") String email){
		
		int result = userService.activateUser(loginUno, email);
		if(result < 1) {
			return new ResponseEntity<String> ("denied",HttpStatus.OK);
		}
		
		return new ResponseEntity<String> ("success",HttpStatus.OK);
	}
	
	
}
