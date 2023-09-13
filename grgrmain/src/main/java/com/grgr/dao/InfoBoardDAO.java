package com.grgr.dao;

import java.util.List;
import java.util.Map;

import com.grgr.dto.InfoBoard;
import com.grgr.dto.InfoFile;

public interface InfoBoardDAO {
	int infoBoardCount(Map<String, Object> map);
	int insertInfoBoard(InfoBoard infoBoard);
	int updateInfoBoard(InfoBoard infoBoard);
	int deleteInfoBoard(int infoBno, int uno);
	int blindInfoBoard(Map<String, Object> map);
	int increaseInfoViewCnt(int infoBno);
	int increaseInfoReportNo(int infoBno);
	InfoBoard selectInfoBoard(int infoBno);
	Integer selectPrevInfoBno(Map<String, Object> map);
	Integer selectNextInfoBno(Map<String, Object> map);
	List<InfoBoard> selectInfoBoardList(Map<String, Object> map);
	
	int insertInfoFile(InfoFile infoFile);
	List<InfoFile> selectInfoFile(int infoBno);
}
