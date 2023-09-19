package com.grgr.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

import com.grgr.dto.FreeBoard;
import com.grgr.exception.FileUploadFailException;
import com.grgr.exception.PostUpdateException;
import com.grgr.exception.WriteNullException;
import com.grgr.service.FreeBoardService;
import com.grgr.util.SearchCondition;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/freeboard")
@RequiredArgsConstructor
@Slf4j
public class FreeBoardController {
	private final FreeBoardService freeBoardService;


	// 글목록 (전체 & 검색 조건)
	@RequestMapping("/list")
	public String list(SearchCondition searchCondition, HttpSession session, Model model) {
		searchCondition.setLoginLocation(extractLoginLocation(session));

		Map<String, Object> result = freeBoardService.getFreeBoardList(searchCondition);
	
		
		model.addAttribute("freeBoardList", result.get("freeBoardList"));
		model.addAttribute("pager", result.get("pager"));
		model.addAttribute("fileList", result.get("fileList"));
		return "board/free_boardlist";
	}

	// 선택 게시글 조회 -> 읽어올때, 글번호로 사진정보를 select해서 출력할것 -> 글 수정, 삭제, 숨김권한 필요 ,
	@GetMapping("/read")
	public String freeBoardRead(@RequestParam int freeBno, SearchCondition searchCondition, HttpSession session,
			Model model) {

		searchCondition.setLoginLocation(extractLoginLocation(session));
		Integer loginUno = (Integer)session.getAttribute("loginUno");
		if(loginUno==null) loginUno = -1;
		Map<String, Object> freeBoardWithFiles = freeBoardService.getFreeBoard(loginUno, freeBno);
		Map<String, Object> nextAndPrev = freeBoardService.prevAndNextFreeBno(searchCondition, freeBno);
		model.addAllAttributes(freeBoardWithFiles);
		model.addAllAttributes(nextAndPrev);
		return "board/free_board";
	}

	// 글쓰기 페이지 요청
	@GetMapping(value = "/write")
	public String freeBoardWrite(SearchCondition searchCondition, HttpSession session, Model model) {
		searchCondition.setLoginLocation(extractLoginLocation(session));
		model.addAttribute("searchCondition", searchCondition);
		return "board/free_write";
	}

	// 글 작성 및 제출
	@PostMapping(value = "/write")
	public String freeBoardWrite(FreeBoard freeBoard,
			@RequestParam(value = "files", required = false) List<MultipartFile> files)
			throws WriteNullException, FileUploadFailException, IOException {
		freeBoard.setFreeTitle(HtmlUtils.htmlEscape(freeBoard.getFreeTitle()));
		freeBoard.setFreeContent(HtmlUtils.htmlEscape(freeBoard.getFreeContent()));
		int newBno = freeBoardService.addFreeBoard(freeBoard, files);

		return "redirect:/freeboard/read?freeBno=" + newBno;
	}

	// 글 수정페이지 요청
	@GetMapping("/modify")
	public String freeBoardModify(int freeBno, SearchCondition searchCondition, HttpSession session, Model model) {
		Integer loginUno = (Integer) session.getAttribute("loginUno");
		searchCondition.setLoginLocation(extractLoginLocation(session));
		Map<String, Object> freeBoardWithFiles = freeBoardService.getFreeBoard(loginUno, freeBno);
		FreeBoard freeBoard = (FreeBoard) freeBoardWithFiles.get("freeBoard");

		if (loginUno != freeBoard.getUno()) {
			return "/404";
		}
		model.addAllAttributes(freeBoardWithFiles);
		model.addAttribute("searchCondition", searchCondition);
		return "board/free_modify";
	}

	// 글 수정 제출
	@PostMapping(value = "/modify")
	public String freeBoardModify(FreeBoard freeBoard,
			@RequestParam(value = "files", required = false) List<MultipartFile> files)
			throws WriteNullException, FileUploadFailException, IOException {
		if (freeBoard.getFreeTitle() == null || freeBoard.getFreeContent() == null) {
			throw new WriteNullException("제목 또는 내용이 비어있습니다.");
		}
		freeBoard.setFreeTitle(HtmlUtils.htmlEscape(freeBoard.getFreeTitle()));
		freeBoard.setFreeContent(HtmlUtils.htmlEscape(freeBoard.getFreeContent()));
		freeBoardService.modifyFreeBoard(freeBoard, files);
		return "redirect:/freeboard/read?freeBno=" + freeBoard.getFreeBno();

	}

	// 글 제거
	@RequestMapping("/remove")
	public String freeBoardRemove(@RequestParam Integer freeBno, SearchCondition searchCondition, HttpSession session,
			RedirectAttributes rattr) throws PostUpdateException {
		Integer loginUno = (Integer) session.getAttribute("loginUno");
		freeBoardService.removeFreeBoard(freeBno, loginUno);
		String redirectUri = "redirect:/freeboard/list" + searchCondition.getQueryString();
		return redirectUri;
	}

	// 글 숨김
	@GetMapping("/hide")
	public String freeBoardBlind(@RequestParam Integer freeBno, SearchCondition searchCondition, HttpSession session) {
		Integer loginUno = (Integer) session.getAttribute("loginUno");
		Integer loginUserStatus = (Integer) session.getAttribute("loginUserStatus");
		freeBoardService.hideFreeBoard(freeBno, loginUno, loginUserStatus);

		String redirectUri = "redirect:/freeboard/list" + searchCondition.getQueryString();
		return redirectUri;

	}
	// 세션의 위치정보에서 <구>에 대한 정보만 추출하는 메서드
	private String extractLoginLocation(HttpSession session) {
		String loginLocation = (String) session.getAttribute("loginLocation");
		if (loginLocation != null && !loginLocation.trim().isEmpty()) {
			return loginLocation.split(",")[1].trim();

		}
		return null;
	}

}
