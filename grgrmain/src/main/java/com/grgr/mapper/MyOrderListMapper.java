package com.grgr.mapper;

import java.util.List;

import com.grgr.dto.MyOrderListDTO;

public interface MyOrderListMapper {
	List<MyOrderListDTO> selectMyOrderList(int uno);
}
