package com.grgr.dao;

import java.util.Map;

public interface FreeLikeDAO {
	int insertMyFreeLike(Map<String, Object> map);
	int deleteMyFreeLike(Map<String, Object> map);
	int getMyLikeStatus(Map<String, Object> map);
	int FreeLikeCnt(int freeBno);
}
