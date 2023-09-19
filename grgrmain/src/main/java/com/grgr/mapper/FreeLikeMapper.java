package com.grgr.mapper;

import java.util.Map;

public interface FreeLikeMapper {
	int insertMyFreeLike(Map<String, Object> map);
	int deleteMyFreeLike(Map<String, Object> map);
	int getMyLikeStatus(Map<String, Object> map);
	int FreeLikeCnt(int freeBno);

}
