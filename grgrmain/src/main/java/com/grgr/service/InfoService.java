package com.grgr.service;

import java.util.List;

import com.grgr.dto.Matzib;

public interface InfoService {

	/* 맛집 리스트 */
	List<Matzib> getMatzibList(String qeury);
}
