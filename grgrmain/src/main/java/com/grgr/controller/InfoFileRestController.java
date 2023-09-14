package com.grgr.controller;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grgr.exception.FileDeleteException;
import com.grgr.service.InfoBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


//작성자 : 김정현
//작성일 : 230909
//수정일 - 수정내용
//0909 - 파일삭제관련 컨트롤러 생성
//0911 - 파일 삭제 컨트롤러 수정 (사유 : 여러 사진을 폼제출시 한번에 일괄 삭제하기위하여)
@RestController
@RequestMapping("/infofile")
@RequiredArgsConstructor
@Slf4j
public class InfoFileRestController {
	private final InfoBoardService infoBoardService;
	
	//이미지 삭제 버튼 클릭시 이미지 삭제
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteInfoImg(@RequestBody List<Integer> deleteFileList) throws FileDeleteException{
		
		infoBoardService.removeInfoFiles(deleteFileList);
		
		return new ResponseEntity<String>("img_del_ok", HttpStatus.OK);
	}
}
