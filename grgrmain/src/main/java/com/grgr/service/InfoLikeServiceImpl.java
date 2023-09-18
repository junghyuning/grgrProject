package com.grgr.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.grgr.dao.InfoLikeDAO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InfoLikeServiceImpl implements InfoLikeService{
	private final InfoLikeDAO infoLikeDAO;
	
	@Override
	public Map<String, Object> getMyLikeStatus(int infoBno, int loginUno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("infoBno", infoBno);
		map.put("uno", loginUno);
		boolean isLiked=false;
		int myLikeStatus = infoLikeDAO.getMyLikeStatus(map); // 0 or 1 반환
		if(myLikeStatus>0) {
			isLiked = true;
		}
		int likeCnt = infoLikeDAO.InfoLikeCnt(infoBno);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("isLiked", isLiked);
		resultMap.put("likeCnt", likeCnt);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> likeToggle(int infoBno, int loginUno) {
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("infoBno", infoBno);
		map.put("uno", loginUno);
		
		boolean isLiked=false;
		//좋아요 테이블에 내가 좋아요한 목록 조회
		int myLikeStatus = infoLikeDAO.getMyLikeStatus(map);
		if(myLikeStatus>0) { 
			//좋아요 목록 제거
			infoLikeDAO.deleteMyInfoLike(map);
		} else {
			//좋아요 목록 추가
			infoLikeDAO.insertMyInfoLike(map);
			isLiked = true;
		}
		
		int likeCnt = infoLikeDAO.InfoLikeCnt(infoBno);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("isLiked", isLiked );
		resultMap.put("likeCnt", likeCnt );
		
		return resultMap;
	}
	
	
	
	

}
