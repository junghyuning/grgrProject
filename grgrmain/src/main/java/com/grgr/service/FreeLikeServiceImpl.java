package com.grgr.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.grgr.dao.FreeLikeDAO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FreeLikeServiceImpl implements FreeLikeService{
	private final FreeLikeDAO freeLikeDAO;
	
	@Override
	public Map<String, Object> getMyLikeStatus(int freeBno, int loginUno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("freeBno", freeBno);
		map.put("uno", loginUno);
		boolean isLiked=false;
		int myLikeStatus = freeLikeDAO.getMyLikeStatus(map); // 0 or 1 반환
		if(myLikeStatus>0) {
			isLiked = true;
		}
		int likeCnt = freeLikeDAO.FreeLikeCnt(freeBno);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("isLiked", isLiked);
		resultMap.put("likeCnt", likeCnt);
		return resultMap;
	}
	
	@Override
	public Map<String, Object> likeToggle(int freeBno, int loginUno) {
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("freeBno", freeBno);
		map.put("uno", loginUno);
		
		boolean isLiked=false;
		//좋아요 테이블에 내가 좋아요한 목록 조회
		int myLikeStatus = freeLikeDAO.getMyLikeStatus(map);
		if(myLikeStatus>0) { 
			//좋아요 목록 제거
			freeLikeDAO.deleteMyFreeLike(map);
		} else {
			//좋아요 목록 추가
			freeLikeDAO.insertMyFreeLike(map);
			isLiked = true;
		}
		
		int likeCnt = freeLikeDAO.FreeLikeCnt(freeBno);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("isLiked", isLiked );
		resultMap.put("likeCnt", likeCnt );
		
		return resultMap;
	}
	
	
	
	

}
