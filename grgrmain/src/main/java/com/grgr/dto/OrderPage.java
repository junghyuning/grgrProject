package com.grgr.dto;

import java.sql.Date;

import lombok.Data;

/*
0921 - 안소연_주문페이지DTO 추가
CREATE TABLE ORDERPAGE(
    ORDER_NO NUMBER PRIMARY KEY,
    UNO NUMBER,
    CONSTRAINT ORDERPAGE_UNO_FK FOREIGN KEY(UNO) REFERENCES USERINFO(UNO),
    PRODUCT_ID NUMBER,
    CONSTRAINT ORDERPAGE_PRODUCT_ID_FK FOREIGN KEY(PRODUCT_ID) REFERENCES PRODUCT_BOARD(PRODUCT_ID),
    ORDER_DATE DATE DEFAULT SYSDATE,
    ORDER_STATUS NUMBER,-- 1: 주문중/2: 주문완료/3: 배송중/4: 배송완료
    ORDER_QUANTITY NUMBER);
    
CREATE SEQUENCE ORDERPAGE_NO_SEQ;
 */

@Data
public class OrderPage {
	private int orderNo;//주문 번호
	private int uno;//회원번호
	private Integer productId;//상품번호
	private Date orderDate;//주문 일자
	private int orderStatus;//주문 상태(1: 주문중, 2: 주문완료, 3: 배송중, 4: 배송완료)
	private int orderQuantity;//주문 수량
}
