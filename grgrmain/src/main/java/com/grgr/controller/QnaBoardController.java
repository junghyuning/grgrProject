package com.grgr.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grgr.dto.QnaBoard;
import com.grgr.exception.WriteNullException;
import com.grgr.service.QnaBoardService;
import com.grgr.util.SearchCondition;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/qnaboard")
@RequiredArgsConstructor
@Slf4j
public class QnaBoardController {
	private final QnaBoardService qnaBoardService;
	
	// 글목록 (전체 & 검색 조건)
	@RequestMapping("/list")
	public String qnaBoardList(SearchCondition searchCondition, Model model)  {
		log.info("QnaBoardController() 클래스의 qnaBoardList() 메소드 호출");
		Map<String, Object> result = qnaBoardService.getQnaBoardList(searchCondition);
		model.addAttribute("qnaBoardList", result.get("qnaBoardList"));
		model.addAttribute("pager", result.get("pager"));
		return "board/qna_boardlist";
	}
	
	// 선택 게시글 조회
	@GetMapping("/read")
	public String qnaBoardRead(@RequestParam int qnaBno, SearchCondition searchCondition, Model model) {
		log.info("QnaBoardController() 클래스의 qnaBoardRead() 메소드 호출");
	    try {
	    	// 조회수 증가 처리
			qnaBoardService.riseQnaViewCnt(qnaBno);
			qnaBoardService.getQnaBoard(qnaBno);

	        Map<String, Object> readMap = qnaBoardService.getQnaBoard(qnaBno);
	        Integer prevQnaBno = qnaBoardService.prevQnaBno(searchCondition, qnaBno);
	        Integer nextQnaBno = qnaBoardService.nextQnaBno(searchCondition, qnaBno);
	        //int loginUno = (int)session.getAttribute("loginUno");
	        //model.addAttribute("loginUno", loginUno);
	        model.addAllAttributes(readMap);
	        model.addAttribute("nextQnaBno", nextQnaBno);
	        model.addAttribute("prevQnaBno", prevQnaBno);
	        model.addAttribute("isLastPost", nextQnaBno == null);
	        model.addAttribute("isFirstPost", prevQnaBno == null);
	        model.addAttribute("searchCondition", searchCondition); // 검색 조건 추가
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "board/qna_board";
	}
	
	// 글쓰기 페이지 요청
	@GetMapping(value = "/write")
	public String qnaBoardWrite(SearchCondition searchCondition, Model model) {
		//model.addAttribute("model", "write");
		model.addAttribute("searchCondition", searchCondition);
		return "board/qna_write";
	}
		
	// 글 작성 및 제출
	@PostMapping(value = "/write")
	public String qnaBoardWrite(QnaBoard qnaBoard) throws WriteNullException {
		int newBno = qnaBoardService.addQnaBoard(qnaBoard);
		return "redirect:/qnaboard/read?qnaBno="+newBno;
	}
		
	// 글 수정페이지 요청
	@GetMapping("/modify")
	public String qnaBoardModify(int qnaBno, SearchCondition searchCondition, Model model) {
		log.info("QnaBoardController() 클래스의 get qnaBoardModify() 메소드 호출");
		Map<String, Object> modifyMap = qnaBoardService.getQnaBoard(qnaBno);
		model.addAllAttributes(modifyMap);
		model.addAttribute("searchCondition", searchCondition);
		return "board/qna_modify";
	}
		
	// 글 수정 제출
	@PostMapping(value="/modify")
	public String qnaBoardModify(QnaBoard qnaBoard, RedirectAttributes rattr) throws WriteNullException{
		log.info("QnaBoardController() 클래스의 post qnaBoardModify() 메소드 호출");
		//int uno = (int) session.getAttribute("uno");
		if (qnaBoard.getQnaTitle() == null || qnaBoard.getQnaContent() == null) {
			throw new WriteNullException("제목 또는 내용이 비어있습니다.");
		} 

		qnaBoardService.modifyQnaBoard(qnaBoard); 
		return "redirect:/qnaboard/read?qnaBno="+qnaBoard.getQnaBno();
	}
		
	// 글 제거
	@RequestMapping("/remove")
	public String qnaBoardRemove(@RequestParam Integer qnaBno, SearchCondition searchCondition, Integer loginUser, HttpSession session,
			RedirectAttributes rattr) {
		log.info("QnaBoardController() 클래스의 qnaBoardRemove() 메소드 호출");
		//int loginUser =(int) session.getAttribute("loginUser");
		loginUser=2;
		qnaBoardService.removeQnaBoard(qnaBno, loginUser);
		String redirectUri = "redirect:/qnaboard/list"+searchCondition.getQueryString();
		return redirectUri;
	}
	
	
	//글 숨김
	@GetMapping("/hide")
	public String qnaBoardBlind(@RequestParam Integer qnaBno, SearchCondition searchCondition, Integer loginUser, Integer loginUserStatus, HttpSession session ) {
		//int loginUser =(int) session.getAttribute("loginUser");
		//int loginUserStatus =(int) session.getAttribute("loginUserStatus");
		loginUser = 2;
		loginUserStatus=1;
		qnaBoardService.hideQnaBoard(qnaBno,loginUser,loginUserStatus);
		
		String redirectUri = "redirect:/qnaboard/list"+searchCondition.getQueryString();
		return redirectUri;
		
	}
}
