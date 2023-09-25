package com.grgr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grgr.dao.MyOrderListDAO;
import com.grgr.dto.MyOrderListDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyOrderListServiceImpl implements MyOrderListService {
	private final MyOrderListDAO myOrderListDao;

	@Override
	public List<MyOrderListDTO> getMyOrderList(int uno) {
		List<MyOrderListDTO> orderList = myOrderListDao.selectMyOrderList(uno);
		return orderList;

	}

}
