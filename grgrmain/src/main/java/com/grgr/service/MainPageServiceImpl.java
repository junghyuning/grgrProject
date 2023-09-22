package com.grgr.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.grgr.dao.MainPageDAO;
import com.grgr.dao.NoticeBoardDAO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MainPageServiceImpl implements MainPageService{
	private final MainPageDAO mainPageDAO;
	private final NoticeBoardDAO noticeBoardDAO;
	


	@Override
	public Map<String, Object> selectNewPost() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newInfoList", mainPageDAO.selectNewInfo());
		map.put("newFreeList", mainPageDAO.selectNewFree());
		map.put("newSalesList", mainPageDAO.selectNewSales());
		map.put("latestNotice", noticeBoardDAO.selectLatestNotice());
		return map;
	}
}
