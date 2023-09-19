package com.grgr.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.grgr.dao.FreeBoardDAO;
import com.grgr.dto.FreeBoard;
import com.grgr.dto.FreeFile;
import com.grgr.exception.FileDeleteException;
import com.grgr.exception.FileUploadFailException;
import com.grgr.exception.PostUpdateException;
import com.grgr.exception.WriteNullException;
import com.grgr.util.Pager;
import com.grgr.util.SearchCondition;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FreeBoardServiceImpl implements FreeBoardService {
	private final FreeBoardDAO freeBoardDAO;
	private final WebApplicationContext context;

	@Override
	@Transactional
	public int addFreeBoard(FreeBoard freeBoard, List<MultipartFile> files) throws WriteNullException, FileUploadFailException, IOException {
		
		if (freeBoard.getFreeTitle() == null || freeBoard.getFreeContent() == null) {
	        throw new WriteNullException("제목 또는 내용이 비어있습니다.");
	    }
		freeBoardDAO.insertFreeBoard(freeBoard);
		
		imgUpload(freeBoard, files);

		return freeBoard.getFreeBno();
	}

	@Override
	@Transactional
	public void modifyFreeBoard(FreeBoard freeBoard, List<MultipartFile> files) throws WriteNullException, FileUploadFailException, IOException {
		if (freeBoard.getFreeTitle() == null || freeBoard.getFreeContent() == null) {
	        throw new WriteNullException("제목 또는 내용이 비어있습니다.");
	    }
		freeBoardDAO.updateFreeBoard(freeBoard);
		
		imgUpload(freeBoard, files);
	}

	@Override
	public void removeFreeBoard(int freeBno, int uno) throws PostUpdateException {
		// TODO Auto-generated method stub
		
		int result = freeBoardDAO.deleteFreeBoard(freeBno, uno);
		
		if(result<1) {
			throw new PostUpdateException("게시글 삭제에 실패하였습니다.");
		}
		
	}

	@Override
	public void hideFreeBoard(int freeBno, int loginUno, int loginUserStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("freeBno", freeBno);
		map.put("uno", loginUno);

		
		freeBoardDAO.blindFreeBoard(map);
	}

	@Override
	@Transactional
	public Map<String, Object> getFreeBoard(int loginUno, int freeBno) {
		
		//게시글 출력
		Map<String, Object> readMap = new HashMap<String, Object>();
		FreeBoard freeBoard = freeBoardDAO.selectFreeBoard(freeBno);
		//게시글 조회시 조회수 증가
		
		if(freeBoard.getUno()!=loginUno) {
			freeBoardDAO.increaseFreeViewCnt(freeBno);			
		}
		String freeConentIncludeEnter = freeBoard.getFreeContent().replace("\r\n", "<br>"); //개행문자를 포함하여 출력하기위함
		freeBoard.setFreeContent(freeConentIncludeEnter);
		readMap.put("freeBoard", freeBoard);
		readMap.put("freeFiles", freeBoardDAO.selectFreeFile(freeBno));
		
		return readMap;
	}

	@Override
	public Map<String, Object> getFreeBoardList(SearchCondition searchCondition) {
		log.warn("리스트 서비스메서드 진입");
		Map<String, Object> searchMap = createSearchMap(searchCondition);


		//int totalBoard = getFreeCount(searchCondition);
		int totalBoard = freeBoardDAO.freeBoardCount(searchMap);
		log.warn("리스트 서비스 메서드에서 게시글 수 카운트" );
		Pager pager = new Pager(totalBoard, searchCondition);
		// 페이징 계산
		searchMap.put("startRow", pager.getStartRow());
		searchMap.put("endRow", pager.getEndRow());

		List<FreeBoard> freeBoardList = freeBoardDAO.selectFreeBoardList(searchMap);
		
		List<String> fileList=new ArrayList<String>();
		for (FreeBoard freeBoard : freeBoardList) {
	        List<FreeFile> freeFiles = freeBoardDAO.selectFreeFile(freeBoard.getFreeBno());
	        if (!freeFiles.isEmpty()) {
	        	fileList.add(freeFiles.get(0).getFreeFileUpload());
	        } else {
	        	fileList.add("placeholder-square.jpg");
	        }
	    }

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("freeBoardList", freeBoardList);
		resultMap.put("pager", pager); // pager 객체를 반환
		//resultMap.put("searchMap", searchMap); //pager내부네 sc가 있으므로 map에 저장하지 않아도 될듯
		resultMap.put("fileList", fileList);

		return resultMap;
	}

	@Override
	public Map<String, Object> prevAndNextFreeBno(SearchCondition searchCondition, int freeBno) {
		Map<String, Object> searchMap = createSearchMap(searchCondition);
		searchMap.put("freeBno", freeBno);
		Integer prevFreeBno = freeBoardDAO.selectPrevFreeBno(searchMap);
		
		Integer nextFreeBno = freeBoardDAO.selectNextFreeBno(searchMap);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nextFreeBno", nextFreeBno);
		map.put("prevFreeBno", prevFreeBno);
		map.put("isLastPost", nextFreeBno ==null );
		map.put("isFirstPost", prevFreeBno==null);
		map.put("searchCondition", searchCondition);
		return map;
	}
	
	@Override
	@Transactional
	public void removeFreeFiles(List<Integer> deleteFileList) throws FileDeleteException {
		
		for(Integer fileNo : deleteFileList) {
			freeBoardDAO.deleteFreeFile(fileNo);
		}
	}
	
	
	//검색관련 맵객체 생성 메서드
	private Map<String, Object> createSearchMap(SearchCondition searchCondition){
		Map<String, Object> searchMap = new HashMap<String, Object>();
		if (searchCondition != null) {
			if (searchCondition.getSearchType() != null && !searchCondition.getSearchType().isEmpty()) {
				searchMap.put("searchType", searchCondition.getSearchType());
			}
			if (searchCondition.getSearchKeyword() != null && !searchCondition.getSearchKeyword().isEmpty()) {
				searchMap.put("searchKeyword", searchCondition.getSearchKeyword());
			}
		}
		
		return searchMap;
	}
	
	//파일 업로드 처리 메서드
	@Transactional
	private void imgUpload(FreeBoard freeBoard, List<MultipartFile> files) throws FileUploadFailException, IOException {
		
		String uploadDirectory=context.getServletContext().getRealPath("/resources/upload");
		
		//파일을 하나도 업로드하지 않아도 되므로 null이 아닐시에만 업로드 작업
		if(files != null && !files.isEmpty()) {
			for(MultipartFile multipartfile : files) {
				 if(multipartfile.isEmpty()) {
			            continue; // 파일이 비어 있으면 다음 파일로 넘어감
			        }

			        if(!multipartfile.getContentType().startsWith("image/")) {
			            throw new FileUploadFailException("사진이 아닌 파일입니다.");
			        }
				
				String uploadFileName = UUID.randomUUID().toString()+"_"+multipartfile.getOriginalFilename();
				System.out.println(uploadFileName);
				File file = new File(uploadDirectory, uploadFileName);
				System.out.println(file);
				

				multipartfile.transferTo(file);
					
			
				
				// 파일 정보 객체 생성
		        FreeFile freeFile = new FreeFile();
		        freeFile.setFreeBno(freeBoard.getFreeBno());
		        freeFile.setFreeFileOrigin(multipartfile.getOriginalFilename());
		        freeFile.setFreeFileUpload(uploadFileName);
		        
		        freeBoardDAO.insertFreeFile(freeFile);

			}
		}
		
	}

}
