package com.grgr.controller;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grgr.exception.FileDeleteException;
import com.grgr.service.InfoBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


//작성자 : 김정현
//작성일 : 230909
//수정일 - 수정내용
//0909 - 파일삭제관련 컨트롤러 생성

@RestController
@RequestMapping("/infofile")
@RequiredArgsConstructor
@Slf4j
public class InfoFileRestController {
	private final InfoBoardService infoBoardService;
	
	//이미지 삭제 버튼 클릭시 이미지 삭제
	@DeleteMapping("/delete/{infoFileNo}")
	public ResponseEntity<String> deleteInfoImg(@PathVariable int infoFileNo) throws FileDeleteException{
		
		infoBoardService.removeInfoFile(infoFileNo);
		
		return new ResponseEntity<String>("img_del_ok", HttpStatus.OK);
	}
}
