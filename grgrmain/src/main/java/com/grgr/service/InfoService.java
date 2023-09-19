package com.grgr.service;

import java.util.List;

import com.grgr.dto.Matzib;
import com.grgr.dto.Medical;

public interface InfoService {

	/* 맛집 리스트 */
	List<Matzib> getMatzibList(String query);
	
	/* 의료 리스트*/
	List<Medical> getMedicalList(String query);
}
