package com.grgr.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.grgr.dto.InfoBoard;
import com.grgr.dto.InfoFile;

public interface InfoBoardMapper {
	int infoBoardCount(Map<String, Object> map);
	int insertInfoBoard(InfoBoard infoBoard);
	int updateInfoBoard(InfoBoard infoBoard);
	int deleteInfoBoard(@Param("infoBno") int infoBno,@Param("uno") int uno);
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
