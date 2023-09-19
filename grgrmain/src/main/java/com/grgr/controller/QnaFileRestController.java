package com.grgr.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grgr.exception.FileDeleteException;
import com.grgr.service.QnaBoardService;

import lombok.RequiredArgsConstructor;


//작성자 : 안소연
//작성일 : 230918
//파일 삭제 레스트 컨트롤러 생성
@RestController
@RequestMapping("/qnafile")
@RequiredArgsConstructor
public class QnaFileRestController {
	private final QnaBoardService qnaBoardService;
	
	//이미지 삭제 버튼 클릭시 이미지 삭제
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteQnaImg(@RequestBody List<Integer> deleteFileList) throws FileDeleteException{
		
		qnaBoardService.removeQnaFiles(deleteFileList);
		
		return new ResponseEntity<String>("img_del_ok", HttpStatus.OK);
	}
}
