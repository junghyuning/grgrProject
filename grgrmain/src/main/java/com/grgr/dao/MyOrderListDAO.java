package com.grgr.dao;

import java.util.List;

import com.grgr.dto.MyOrderListDTO;

public interface MyOrderListDAO {
	List<MyOrderListDTO> selectMyOrderList(int uno);
}
