package com.grgr.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grgr.service.FreeLikeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/freelike")
@RequiredArgsConstructor
@Slf4j
public class FreeLikeController {
	private final FreeLikeService freeLikeService;
	
	// 현재 좋아요 상태 반환
	@GetMapping("/status/{freeBno}")
	public ResponseEntity<Map<String, Object>> getLikeStatus(@PathVariable int freeBno, HttpSession session){
		
		Integer loginUno = (Integer)session.getAttribute("loginUno");
		if(loginUno == null) {
			return new ResponseEntity<Map<String, Object>> (HttpStatus.UNAUTHORIZED);
		}
		Map<String, Object> data = freeLikeService.getMyLikeStatus(freeBno, loginUno);
		
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}
	
	// 좋아요 상태 전환 (좋아요 O->X / X->O)
	@PostMapping("/toggle/{freeBno}")
	public ResponseEntity<Map<String, Object>> toggleLike(@PathVariable int freeBno, HttpSession session){
		Integer loginUno = (Integer)session.getAttribute("loginUno");
		if(loginUno == null) {
			return new ResponseEntity<Map<String, Object>> (HttpStatus.UNAUTHORIZED);
		}
		//삽입or삭제 실행 후 resultMap -> isLiked 상태 + 좋아요 Cnt
		Map<String, Object> data = freeLikeService.likeToggle(freeBno, loginUno);
		return new ResponseEntity<Map<String, Object>> (data, HttpStatus.OK);
		
	}
	
	
}
