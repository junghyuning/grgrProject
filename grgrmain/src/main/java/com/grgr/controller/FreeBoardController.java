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

import com.grgr.dto.FreeBoard;
import com.grgr.exception.FileUploadFailException;
import com.grgr.exception.WriteNullException;
import com.grgr.service.FreeBoardService;
import com.grgr.util.SearchCondition;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/freeboard")
@RequiredArgsConstructor
public class FreeBoardController {
	private final FreeBoardService freeBoardService;

//글 목록
	@RequestMapping("/list")
	public String list(SearchCondition searchCondition, Model model) {
		Map<String, Object> result = freeBoardService.getFreeBoardList(searchCondition);
		model.addAttribute("freeBoardList", result.get("freeBoardList"));
		model.addAttribute("pager", result.get("pager"));
		model.addAttribute("fileList", result.get("fileList"));
		return "board/free_boardlist";
	}

//선택글 조회
	@GetMapping("/read")
	public String freeBoardRead(@RequestParam("freeBno") int freeBno, SearchCondition searchCondition, Model model) {
		try {
			Map<String, Object> freeBoardWithFiles = freeBoardService.getFreeBoard(freeBno);
			Integer prevFreeBno = freeBoardService.prevFreeBno(searchCondition, freeBno);
			Integer nextFreeBno = freeBoardService.nextFreeBno(searchCondition, freeBno);

			model.addAllAttributes(freeBoardWithFiles);
			model.addAttribute("nextFreeBno", nextFreeBno);
			model.addAttribute("prevFreeBno", prevFreeBno);
			model.addAttribute("isLastPost", nextFreeBno == null);
			model.addAttribute("isFirstPost", prevFreeBno == null);
			model.addAttribute("searchCondition", searchCondition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board/free_board";
	}

	//
	@GetMapping(value = "/write")
	public String freeBoardWrite(Model model) {
		return "board/free_write";
	}

	//
	@PostMapping(value = "/write")
	public String freeBoardWrite(FreeBoard freeBoard,
			@RequestParam(value = "files", required = false) List<MultipartFile> files)
			throws WriteNullException, FileUploadFailException, IOException {
		int newBno = freeBoardService.addFreeBoard(freeBoard, files);

		return "redirect:/freeboard/read?freeBno=" + newBno;
	}

	// 수정페이지 요청
	@GetMapping("/modify")
	public String freeBoardModify(int freeBno, SearchCondition searchCondition, HttpSession session, Model model) {
		Integer loginUno = (Integer) session.getAttribute("loginUno");

		Map<String, Object> freeBoardWithFiles = freeBoardService.getFreeBoard(freeBno);
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
	public String freeBoardModify(FreeBoard freeBoard, RedirectAttributes rattr) throws WriteNullException {
		if (freeBoard.getFreeTitle() == null || freeBoard.getFreeContent() == null) {
			throw new WriteNullException("제목 또는 내용이 비어있습니다.");
		}

		freeBoardService.modifyFreeBoard(freeBoard);
		return "redirect:/freeboard/read";

	}

	@RequestMapping("/remove")
	public String freeBoardRemove(@RequestParam Integer freeBno, @RequestParam Integer uno, int pageNum,
			RedirectAttributes rattr) {
		freeBoardService.removeFreeBoard(freeBno, uno);
		rattr.addFlashAttribute("pageNum", pageNum);
		return "redirect:/freeboard/list?pageNum=" + pageNum;
	}
}
