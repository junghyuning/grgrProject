package com.grgr.service;

import java.util.List;

import com.grgr.dto.MyOrderListDTO;

public interface MyOrderListService {
	List<MyOrderListDTO> getMyOrderList(int uno);
}
