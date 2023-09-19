package com.grgr.dao;

import java.util.List;

import com.grgr.dto.FreeBoard;
import com.grgr.dto.MainInfo;
import com.grgr.dto.MainProduct;
import com.grgr.dto.ProductBoardVO;

public interface MainPageDAO {
	List<FreeBoard> selectNewFree();
	List<MainInfo> selectNewInfo();
	List<MainProduct> selectNewSales();

}
