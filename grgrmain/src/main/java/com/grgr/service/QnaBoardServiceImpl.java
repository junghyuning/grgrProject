package com.grgr.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.grgr.dao.QnaBoardDAO;
import com.grgr.dto.QnaBoard;
import com.grgr.dto.QnaFile;
import com.grgr.dto.Userinfo;
import com.grgr.exception.FileDeleteException;
import com.grgr.exception.FileUploadFailException;
import com.grgr.exception.PostUpdateException;
import com.grgr.exception.WriteNullException;
import com.grgr.util.Pager;
import com.grgr.util.SearchCondition;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//0914 - 안소연_사진 업로드 추가
//		addQnaBoard, modifyQnaBoard : 비밀글&공개글 상태 추가
//0920 - 안소연_관리자가 비밀글 조회 가능하도록 수정(loginUserStatus 추가)

@Service
@RequiredArgsConstructor
@Slf4j
public class QnaBoardServiceImpl implements QnaBoardService {
	private final QnaBoardDAO qnaBoardDAO;
	private final WebApplicationContext context;

//	@Override
//	public int getQnaCount(SearchCondition searchCondition) {
//		Map<String, Object> searchMap = createSearchMap(searchCondition);
//		
//		return qnaBoardDAO.qnaBoardCount(searchMap);
//	}

	@Override
	@Transactional
	public int addQnaBoard(QnaBoard qnaBoard, Integer qnaBlindstate, List<MultipartFile> files)
	        throws WriteNullException, FileUploadFailException, IOException {
	    if (qnaBoard.getQnaTitle() == null || qnaBoard.getQnaContent() == null) {
	        throw new WriteNullException("제목 또는 내용이 비어있습니다.");
	    }
	    
	    //블라인드 상태가 null이 아닌 경우
	    if (qnaBlindstate != null) {
	    	//등록된 blindstate로 설정(비밀글 체크:4)
	        qnaBoard.setQnaBlindstate(qnaBlindstate);
	    } else {
	        // 기본값인 공개글(1)로 설정
	        qnaBoard.setQnaBlindstate(1);
	    }

	    qnaBoardDAO.insertQnaBoard(qnaBoard);
	    imgUpload(qnaBoard, files);

	    return qnaBoard.getQnaBno();
	}

	@Override
	@Transactional
	public void modifyQnaBoard(QnaBoard qnaBoard, Integer qnaBlindstate, List<MultipartFile> files)
			throws WriteNullException, FileUploadFailException, IOException {
		if (qnaBoard.getQnaTitle() == null || qnaBoard.getQnaContent() == null) {
			throw new WriteNullException("제목 또는 내용이 비어있습니다.");
		}

		//블라인드 상태가 null이 아닌 경우
	    if (qnaBlindstate != null) {
	    	//등록된 blindstate로 설정(비밀글 체크:4)
	        qnaBoard.setQnaBlindstate(qnaBlindstate);
	    } else {
	        // 기본값인 공개글(1)로 설정
	        qnaBoard.setQnaBlindstate(1);
	    }

		qnaBoardDAO.updateQnaBoard(qnaBoard);

		imgUpload(qnaBoard, files);
	}

	@Override
	public void removeQnaBoard(int qnaBno, int uno) throws PostUpdateException {
		int result = qnaBoardDAO.deleteQnaBoard(qnaBno, uno);

		if (result < 1) {
			throw new PostUpdateException("게시글 삭제에 실패하였습니다.");
		}
	}

	@Override
	public void hideQnaBoard(int qnaBno, int loginUno, int loginUserStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("qnaBno", qnaBno);
		map.put("uno", loginUno);
		map.put("userStatus", loginUserStatus);

		qnaBoardDAO.blindQnaBoard(map);
	}

	@Override
	@Transactional
	public Map<String, Object> getQnaBoard(int loginUno, int qnaBno) {
		// 게시글 출력
		Map<String, Object> readMap = new HashMap<String, Object>();
		QnaBoard qnaBoard = qnaBoardDAO.selectQnaBoard(qnaBno);
		// 게시글 조회시 조회수 증가
		if (qnaBoard.getUno() != loginUno) {
			qnaBoardDAO.increaseQnaViewCnt(qnaBno);
		}

		String qnaConentIncludeEnter = qnaBoard.getQnaContent().replace("\r\n", "<br>"); // 개행문자를 포함하여 출력하기위함
		qnaBoard.setQnaContent(qnaConentIncludeEnter);
		readMap.put("qnaBoard", qnaBoard);
		readMap.put("qnaFiles", qnaBoardDAO.selectQnaFile(qnaBno));

		return readMap;
	}

	//게시글 List 출력(0914_비밀글 추가)
	@Override
	public Map<String, Object> getQnaBoardList(SearchCondition searchCondition, HttpSession session, int loginUserStatus) {
		Map<String, Object> searchMap = createSearchMap(searchCondition);
		searchMap.put("loginUserStatus", loginUserStatus);
		
		int totalBoard = qnaBoardDAO.qnaBoardCount(searchMap);
		Pager pager = new Pager(totalBoard, searchCondition);
		// 페이징 계산
		searchMap.put("startRow", pager.getStartRow());
		searchMap.put("endRow", pager.getEndRow());

		Integer loginUno = (Integer) session.getAttribute("loginUno");
		Userinfo userinfo = (Userinfo) session.getAttribute("userinfo");

		List<QnaBoard> qnaBoardList = qnaBoardDAO.selectQnaBoardList(searchMap);

		List<String> fileList = new ArrayList<String>();
		for (QnaBoard qnaBoard : qnaBoardList) {
			List<QnaFile> qnaFiles = qnaBoardDAO.selectQnaFile(qnaBoard.getQnaBno());
			if (!qnaFiles.isEmpty()) {
				fileList.add(qnaFiles.get(0).getQnaFileUpload());
			} else {
				fileList.add("placeholder-square.jpg");
			}
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("qnaBoardList", qnaBoardList);
		resultMap.put("pager", pager); // pager 객체를 반환
		resultMap.put("fileList", fileList);

		log.info("#####QnaBoardSerivce의 getQnaBoardList");
		return resultMap;
	}

	@Override
	public Integer prevQnaBno(SearchCondition searchCondition, int qnaBno) {
		Map<String, Object> searchMap = createSearchMap(searchCondition);
		searchMap.put("qnaBno", qnaBno);

		return qnaBoardDAO.selectPrevQnaBno(searchMap);
	}

	@Override
	public Integer nextQnaBno(SearchCondition searchCondition, int qnaBno) {
		Map<String, Object> searchMap = createSearchMap(searchCondition);
		searchMap.put("qnaBno", qnaBno);

		return qnaBoardDAO.selectNextQnaBno(searchMap);
	}

	@Override
	@Transactional
	public void removeQnaFiles(List<Integer> deleteFileList) throws FileDeleteException {
		for (Integer fileNo : deleteFileList) {
			qnaBoardDAO.deleteQnaFile(fileNo);
		}
	}

	// ====================================================================================
	// 검색관련 맵객체 생성 메서드
	private Map<String, Object> createSearchMap(SearchCondition searchCondition) {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		if (searchCondition != null) {
			if (searchCondition.getSearchType() != null && !searchCondition.getSearchType().isEmpty()) {
				searchMap.put("searchType", searchCondition.getSearchType());
			}
			if (searchCondition.getSearchKeyword() != null && !searchCondition.getSearchKeyword().isEmpty()) {
				searchMap.put("searchKeyword", searchCondition.getSearchKeyword());
			}
			if (searchCondition.getKeyword() != null && !searchCondition.getKeyword().trim().isEmpty()) {
				searchMap.put("qnaKeyword", searchCondition.getKeyword().trim());
			}
		}
		return searchMap;
	}

	// 파일 업로드 처리 메서드
	private void imgUpload(QnaBoard qnaBoard, List<MultipartFile> files) throws FileUploadFailException, IOException {
		String uploadDirectory = context.getServletContext().getRealPath("/resources/upload");

		// 파일을 하나도 업로드하지 않아도 되므로 null이 아닐시에만 업로드 작업
		if (files != null && !files.isEmpty()) {
			for (MultipartFile multipartfile : files) {
				if (multipartfile.isEmpty()) {
					continue; // 파일이 비어 있으면 다음 파일로 넘어감
				}

				if (!multipartfile.getContentType().startsWith("image/")) {
					throw new FileUploadFailException("사진이 아닌 파일입니다.");
				}

				String uploadFileName = UUID.randomUUID().toString() + "_" + multipartfile.getOriginalFilename();
				System.out.println(uploadFileName);
				File file = new File(uploadDirectory, uploadFileName);
				System.out.println(file);

				multipartfile.transferTo(file);

				// 파일 정보 객체 생성
				QnaFile qnaFile = new QnaFile();
				qnaFile.setQnaBno(qnaBoard.getQnaBno());
				qnaFile.setQnaFileOrigin(multipartfile.getOriginalFilename());
				qnaFile.setQnaFileUpload(uploadFileName);

				qnaBoardDAO.insertQnaFile(qnaFile);
			}
		}
	}
}