package com.grgr.dto;

import java.sql.Date;

import lombok.Data;

//주문번호에 따른 주문내역을 저장하기위한 DTO (PRODUCT_BOARD & ORDERPAGE)
@Data
public class OrderListDTO {
	private int orderNo;//주문 테이블 번호(PK)
	private int orderGroup; //주문번호
	private int productId;//상품번호
	private Date orderDate;//주문 일자
	private int orderStatus;//주문 상태(1: 결제대기 2: 결제중, 3: 결제완료)
	private int orderQuantity;//주문 수량
	private int uno;//판매자의회원번호
	private String productTitle; // 상품 제목
	private String productContent; // 상품 설명
	private int productPrice; // 상품 가격
	private String nickname; // 판매자 닉네임
}
