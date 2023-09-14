package com.grgr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grgr.dao.ProductCartDAO;
import com.grgr.dto.ProductCartDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCartServiceImpl implements ProductCartService {
	private final ProductCartDAO productCartDAO;

	@Override
	public int addCart(ProductCartDTO cart) throws Exception {
		// 장바구니 데이터 체크
		ProductCartDTO checkCart = productCartDAO.selectCart(cart);
		if (checkCart != null) {
			return 2;
		}

		// 장바구니 등록 & 에러 발생 시 0 반환
		try {
			return productCartDAO.insertCart(cart);
		} catch (Exception e) {
			return 1;
		}
	}

	@Override
	public int removeCart(int productCartNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyCart(ProductCartDTO cart) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductCartDTO> getCartList(int uno) {
		return productCartDAO.selectCartList(uno);
	}

	@Override
	public ProductCartDTO getCart(ProductCartDTO cart) {
		return cart;

	}

}
