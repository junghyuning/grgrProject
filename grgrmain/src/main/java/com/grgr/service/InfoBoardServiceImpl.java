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

import com.grgr.dao.InfoBoardDAO;
import com.grgr.dto.InfoBoard;
import com.grgr.dto.InfoFile;
import com.grgr.exception.FileUploadFailException;
import com.grgr.exception.WriteNullException;
import com.grgr.util.Pager;
import com.grgr.util.SearchCondition;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InfoBoardServiceImpl implements InfoBoardService {
	private final InfoBoardDAO infoBoardDAO;
	private final WebApplicationContext context;
	
		

	@Override
	public int getInfoCount(SearchCondition searchCondition) {
		Map<String, Object> searchMap = createSearchMap(searchCondition);
		
		return infoBoardDAO.infoBoardCount(searchMap);
	}

	@Override
	@Transactional
	public int addInfoBoard(InfoBoard infoBoard, List<MultipartFile> files) throws WriteNullException, FileUploadFailException, IOException {
		
		if (infoBoard.getInfoTitle() == null || infoBoard.getInfoContent() == null) {
	        throw new WriteNullException("제목 또는 내용이 비어있습니다.");
	    }
		infoBoardDAO.insertInfoBoard(infoBoard);

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
		        InfoFile infoFile = new InfoFile();
		        infoFile.setInfoBno(infoBoard.getInfoBno());
		        infoFile.setInfoFileOrigin(multipartfile.getOriginalFilename());
		        infoFile.setInfoFileUpload(uploadFileName);
		        
		        infoBoardDAO.insertInfoFile(infoFile);

			}
		}
		return infoBoard.getInfoBno();
	}

	@Override
	public void modifyInfoBoard(InfoBoard infoBoard) {
		// TODO Auto-generated method stub
		infoBoardDAO.updateInfoBoard(infoBoard);

	}

	@Override
	public void removeInfoBoard(int infoBno, int uno) {
		// TODO Auto-generated method stub
		infoBoardDAO.deleteInfoBoard(infoBno, uno);

	}

	@Override
	public void hideInfoBoard(int infoBno, int loginUno, int loginUserStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("infoBno", infoBno);
		map.put("uno", loginUno);
		map.put("userStatus", loginUserStatus);
		
		infoBoardDAO.blindInfoBoard(map);
	}

	@Override
	public Map<String, Object> getInfoBoard(int infoBno) {
		Map<String, Object> readMap = new HashMap<String, Object>();
		// TODO Auto-generated method stub
		InfoBoard infoBoard = infoBoardDAO.selectInfoBoard(infoBno);
		String infoConentIncludeEnter = infoBoard.getInfoContent().replace("\r\n", "<br>"); //개행문자를 포함하여 출력하기위함
		infoBoard.setInfoContent(infoConentIncludeEnter);
		readMap.put("infoBoard", infoBoard);
		readMap.put("infoFiles", infoBoardDAO.selectInfoFile(infoBno));
		
		return readMap;
	}

	@Override
	public Map<String, Object> getInfoBoardList(SearchCondition searchCondition) {
		Map<String, Object> searchMap = createSearchMap(searchCondition);
		int totalBoard = getInfoCount(searchCondition);
		
		Pager pager = new Pager(totalBoard, searchCondition);
		// 페이징 계산
		searchMap.put("startRow", pager.getStartRow());
		searchMap.put("endRow", pager.getEndRow());

		List<InfoBoard> infoBoardList = infoBoardDAO.selectInfoBoardList(searchMap);
		
		List<String> fileList=new ArrayList<String>();
		for (InfoBoard infoBoard : infoBoardList) {
	        List<InfoFile> infoFiles = infoBoardDAO.selectInfoFile(infoBoard.getInfoBno());
	        if (!infoFiles.isEmpty()) {
	        	fileList.add(infoFiles.get(0).getInfoFileUpload());
	        } else {
	        	fileList.add("placeholder-square.jpg");
	        }
	    }

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("infoBoardList", infoBoardList);
		resultMap.put("pager", pager); // pager 객체를 반환
		//resultMap.put("searchMap", searchMap);
		resultMap.put("fileList", fileList);

		return resultMap;
	}

	@Override
	public Integer prevInfoBno(SearchCondition searchCondition, int infoBno) {
		Map<String, Object> searchMap = createSearchMap(searchCondition);
		searchMap.put("infoBno", infoBno);
		
		return infoBoardDAO.selectPrevInfoBno(searchMap);
	}

	@Override
	public Integer nextInfoBno(SearchCondition searchCondition, int infoBno) {
		Map<String, Object> searchMap = createSearchMap(searchCondition);
		searchMap.put("infoBno", infoBno);
		
		
		

		return infoBoardDAO.selectNextInfoBno(searchMap);
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
			if (searchCondition.getKeyword() != null && !searchCondition.getKeyword().trim().isEmpty()) {
				searchMap.put("infoKeyword", searchCondition.getKeyword().trim());
			}
//			if (searchCondition.getLocation() != null && !searchCondition.getLocation().trim().isEmpty()) {
//				searchMap.put("infoLoc", searchCondition.getLocation().trim());
//			}
		}
		searchMap.put("infoLoc", "강남구");
		
		return searchMap;
	}

}
