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

import com.grgr.dto.FreeComment;
import com.grgr.exception.CommentBlindException;
import com.grgr.exception.CommentInsertException;
import com.grgr.exception.CommentModifyException;
import com.grgr.exception.CommentRemoveException;
import com.grgr.service.FreeCommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/freecomment")
@RequiredArgsConstructor
public class FreeCommentController {
	private final FreeCommentService freeCommentService;

	// 댓글 조회 (GET 사용)
	@GetMapping("/list/{freeBno}")
	public Map<String, Object> commentList(@PathVariable int freeBno, @RequestParam(defaultValue = "1") int pageNum) {
		Map<String, Object> resultMap = freeCommentService.getFreeCommentList(freeBno, pageNum);

		return resultMap;
	}

	// 댓글 삽입(POST 사용)
	@PostMapping("/write/{freeBno}")
	public ResponseEntity<String> freeCommentAdd(@RequestBody FreeComment freeComment, @PathVariable int freeBno,
			HttpSession session) throws CommentInsertException {
		int loginUno = (int) session.getAttribute("loginUno");
		freeComment.setUno(loginUno);
		freeComment.setFreeBno(freeBno);
		freeComment.setFreeCommentContent(HtmlUtils.htmlEscape(freeComment.getFreeCommentContent()));
		freeCommentService.addFreeComment(freeComment);

		return new ResponseEntity<String>("WRT_OK", HttpStatus.OK);
	}

	// 댓글 수정(PUT 사용)
	@PatchMapping("/modify/{freeCommentNo}")
	public ResponseEntity<String> freeCommentModify(@PathVariable int freeCommentNo,
			@RequestBody FreeComment freeComment, HttpSession session) throws CommentModifyException {
		int loginUno = (int) session.getAttribute("loginUno");
		freeComment.setUno(loginUno);
		freeComment.setFreeCommentContent(HtmlUtils.htmlEscape(freeComment.getFreeCommentContent()));
		freeCommentService.modifyFreeComment(freeComment);
		return new ResponseEntity<String>("MOD_OK", HttpStatus.OK);
	}
 
	// 댓글 삭제(본인)
	@PutMapping("/remove/{freeCommentNo}")
	public ResponseEntity<String> freeCommentRemove(@PathVariable int freeCommentNo, HttpSession session)
			throws CommentRemoveException {
		int loginUno = (int) session.getAttribute("loginUno");
		freeCommentService.removeFreeComment(freeCommentNo, loginUno);

		return new ResponseEntity<String>("DEL_OK", HttpStatus.OK);

	}

	// 댓글 숨김
	@PutMapping("/hide/{freeCommentNo}")
	public ResponseEntity<String> freeCommentBlind(@PathVariable int freeCommentNo, HttpSession session)
			throws CommentBlindException {
		int loginUno = (int) session.getAttribute("loginUno");
		int loginUserStatus = (int) session.getAttribute("loginUserStatus");

		freeCommentService.hideFreeComment(loginUno, freeCommentNo, loginUserStatus);

		return new ResponseEntity<String>("DEL_OK", HttpStatus.OK);
	}

}
