package com.grgr.dto;

import java.util.Date;

import lombok.Data;

/*
이름             널?       유형             
-------------- -------- -------------- 
QNA_BNO        NOT NULL NUMBER         
UNO               		NUMBER         
QNA_KEYWORD             VARCHAR2(20)         
QNA_TITLE      NOT NULL VARCHAR2(100)  
QNA_CONTENT    NOT NULL VARCHAR2(2000) 
QNA_VIEW_CNT            NUMBER         
QNA_BLINDSTATE          NUMBER         
QNA_REGDATE             DATE           
QNA_UPDATE				DATE           
QNA_UPDATE_UNO          NUMBER         

create sequence qna_seq;
 */

@Data
public class QnaBoard {
	private int qnaBno;
	private int uno;
	private String qnaKeyword;
	private String qnaTitle;
	private String qnaContent;
	private int qnaViewCnt;
	private int qnaBlindstate;
	private Date qnaRegdate;
	private Date qnaUpdate;
	private String nickname;
	private int qnaUpdateUno;
	private int qnaReportCnt;//0909_소연_신고추가
}
