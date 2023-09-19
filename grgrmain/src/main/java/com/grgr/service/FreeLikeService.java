package com.grgr.service;

import java.util.Map;

public interface FreeLikeService {
	Map<String, Object> getMyLikeStatus(int freeBno, int loginUno);
	Map<String, Object> likeToggle(int freeBno, int loginUno);
	
}
