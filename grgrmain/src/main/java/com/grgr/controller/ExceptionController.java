package com.grgr.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grgr.exception.CommentBlindException;
import com.grgr.exception.CommentInsertException;
import com.grgr.exception.CommentModifyException;
import com.grgr.exception.CommentRemoveException;
import com.grgr.exception.FileDeleteException;
import com.grgr.exception.FileUploadFailException;
import com.grgr.exception.NoCommentsException;
import com.grgr.exception.PostUpdateException;
import com.grgr.exception.WriteNullException;

//작성자 : 김정현
//수정일 - 수정내용
//게시글등록 예외 추가
@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(WriteNullException.class)
	public String writeNullExceptionHandler(WriteNullException e, Model model) {
		model.addAttribute("message", e.getMessage());
		return "board/info_write";
	}
	@ExceptionHandler(PostUpdateException.class)
	public String postUpdateExceptionHandler(PostUpdateException e, Model model) {
		model.addAttribute("message", e.getMessage());
		return "board/info_list";
	}

	@ExceptionHandler(FileUploadFailException.class)
	public String fileUploadFailExceptionHandler(FileUploadFailException e, Model model) {
		model.addAttribute("message", e.getMessage());
		return "board/info_write";
	}
	
	@ExceptionHandler(FileDeleteException.class)
	public ResponseEntity<String> fileDeleteExceptionHandler(FileDeleteException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	@ExceptionHandler(CommentInsertException.class)
	public ResponseEntity<Object> commentInsertExceptionHandler(CommentInsertException exception) {
		Map<String, Object> response = new HashMap<>();
		response.put("status", "error");
		response.put("message", exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CommentModifyException.class)
	public ResponseEntity<String> commentModifyExceptionHandler(CommentModifyException exception){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
				}
	@ExceptionHandler(CommentRemoveException.class)
	public ResponseEntity<String> commentRemoveExceptionHandler(CommentRemoveException exception){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
	@ExceptionHandler(CommentBlindException.class)
	public ResponseEntity<String> commentBlindExceptionHandler(CommentBlindException exception){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}

}
