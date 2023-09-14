package com.grgr.dto;

import java.util.Date;

import lombok.Data;
/*
CREATE TABLE PRODUCT_COMMENT(
PRODUCT_COMMENT_NO NUMBER PRIMARY KEY,
UNO NUMBER,
PRODUCT_ID NUMBER,
PRODUCT_COMMENT_REPORTING NUMBER,
PRODUCT_COMMENT_CONTENT VARCHAR2(2000),
PRODUCT_COMMENT_GROUP NUMBER,
PRODUCT_COMMENT_BLINDSTATE NUMBER,
PRODUCT_COMMENT_REGDATE DATE DEFAULT SYSDATE,
PRODUCT_COMMENT_UPDATE DATE DEFAULT SYSDATE,
PRODUCT_COMMENT_UPDATE_UNO NUMBER,
CONSTRAINT FK_PRODUCT_COMMENT_UNO 
FOREIGN KEY (UNO) 
REFERENCES USERINFO (UNO)
);

CREATE SEQUENCE PRODUCT_COMMENT_SEQ; 
  
이름                         널?       유형             
-------------------------- -------- -------------- 
PRODUCT_COMMENT_NO         NOT NULL NUMBER         
UNO                                 NUMBER         
PRODUCT_ID                          NUMBER         
PRODUCT_COMMENT_REPORTING           NUMBER         
PRODUCT_COMMENT_CONTENT             VARCHAR2(2000) 
PRODUCT_COMMENT_GROUP               NUMBER         
PRODUCT_COMMENT_BLINDSTATE          NUMBER         
PRODUCT_COMMENT_REGDATE             DATE           
PRODUCT_COMMENT_UPDATE              DATE           
PRODUCT_COMMENT_UPDATE_UNO          NUMBER 

230913 - PRODUCT_COMMENT_BLINDSTATE 의 default 값을 1로 설정함

*/

@Data
public class ProductComment {
	private int productCommentNo;
	private int uno;
	private int productId;
	private String productCommentContent;
	private int productCommentGroup;
	private int productCommentBlindstate;
	private String productCommentRegdate;
	private String productCommentUpdate;
	private int productCommentUpdateUno;

	private String nickname; // 조회시에만 사용할 닉네임 변수

}
