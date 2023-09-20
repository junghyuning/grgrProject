package com.grgr.mapper;

import java.util.List;

import com.grgr.dto.MainFree;
import com.grgr.dto.MainInfo;
import com.grgr.dto.MainProduct;

public interface MainPageMapper {
	List<MainFree> selectNewFree();
	
	List<MainInfo> selectNewInfo();

	List<MainProduct> selectNewSales();
}
