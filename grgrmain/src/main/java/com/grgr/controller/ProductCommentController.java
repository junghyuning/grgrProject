package com.grgr.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.grgr.dto.ProductComment;
import com.grgr.exception.CommentBlindException;
import com.grgr.exception.CommentInsertException;
import com.grgr.exception.CommentModifyException;
import com.grgr.exception.CommentRemoveException;
import com.grgr.service.ProductCommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/productcomment")
@RequiredArgsConstructor
@Slf4j
public class ProductCommentController {
	private final ProductCommentService productCommentService;

	// 댓글 조회
	@GetMapping("/list/{productId}")
	public Map<String, Object> commentList(@PathVariable int productId, @RequestParam(defaultValue = "1") int pageNum,
			HttpSession session) {
		log.info("ProductCommentController() 클래스의 commentList() 메소드 호출");

		Integer loginUno = (Integer) session.getAttribute("loginUno");
		Integer loginUserStatus = (Integer) session.getAttribute("loginUserStatus");

		Map<String, Object> resultMap = productCommentService.getProductCommentList(productId, pageNum);
		resultMap.put("loginUno", loginUno); // 로그인한 사용자의 uno 값을 결과 맵에 포함
		resultMap.put("loginUserStatus", loginUserStatus);

		return resultMap;

	}

	// 댓글 삽입
	@PostMapping("/write/{productId}")
	public ResponseEntity<String> productCommentAdd(@RequestBody ProductComment productComment,
			@PathVariable int productId, HttpSession session) throws CommentInsertException {
		log.info("ProductCommentController() 클래스의 qnaCommentAdd() 메소드 호출");
		Integer loginUno = (Integer) session.getAttribute("loginUno");
		productComment.setUno(loginUno);
		productComment.setProductId(productId);
		productCommentService.addProductComment(productComment);

		return new ResponseEntity<String>("WRT_OK", HttpStatus.OK);
	}

	// 댓글 수정
	@PatchMapping("/modify/{productCommentNo}")
	public ResponseEntity<String> productCommentModify(@PathVariable int productCommentNo,
			@RequestBody ProductComment productComment, HttpSession session) throws CommentModifyException {
		log.info("ProductCommentController() 클래스의 qnaCommentModify() 메소드 호출");
		Integer loginUno = (Integer) session.getAttribute("loginUno");
		productComment.setUno(loginUno);
		productComment.setProductCommentContent(HtmlUtils.htmlEscape(productComment.getProductCommentContent()));
		productCommentService.modifyProductComment(productComment);
		return new ResponseEntity<String>("MOD_OK", HttpStatus.OK);
	}

	// 댓글 삭제
	@PutMapping("/remove/{productCommentNo}")
	public ResponseEntity<String> productCommentRemove(@PathVariable int productCommentNo, HttpSession session)
			throws CommentRemoveException {
		log.info("ProductCommentController() 클래스의 qnaCommentRemove() 메소드 호출");
		Integer loginUno = (Integer) session.getAttribute("loginUno");
		productCommentService.removeProductComment(productCommentNo, loginUno);

		return new ResponseEntity<String>("DEL_OK", HttpStatus.OK);

	}

	// 댓글 숨김
	@PutMapping("/hide/{productCommentNo}")
	public ResponseEntity<String> productCommentHide(@PathVariable int productCommentNo, HttpSession session)
			throws CommentBlindException {
		log.info("ProductCommentController() 클래스의 qnaCommentBlind() 메소드 호출");
		Integer loginUno = (Integer) session.getAttribute("loginUno");
		Integer loginUserStatus = (Integer) session.getAttribute("loginUserStatus");
		log.info("hide 메서드");
		productCommentService.hideProductComment(loginUno, productCommentNo, loginUserStatus);

		return new ResponseEntity<String>("HIDE_OK", HttpStatus.OK);
	}

}
