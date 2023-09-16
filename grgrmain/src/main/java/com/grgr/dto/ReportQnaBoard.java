package com.grgr.dto;

import java.util.Date;

import lombok.Data;

/*
안소연_0908

CREATE TABLE REPORT_QNABOARD(
    REPORT_QNABOARD_NO NUMBER PRIMARY KEY,		신고글번호
    UNO NUMBER,									유저번호
    CONSTRAINT REPORT_UNO_FK FOREIGN KEY(UNO) references USERINFO(UNO),
    QNA_BNO NUMBER,								문의게시글 번호
    CONSTRAINT REPORT_QNA_BNO_FK FOREIGN KEY(QNA_BNO) references QNA_BOARD(QNA_BNO),
    REPORT_REASON VARCHAR2(500),				신고 사유				
    REPORT_REGDATE DATE DEFAULT SYSDATE)		신고 일자
 */

@Data
public class ReportQnaBoard {
	private int reportQnaBoardNo;
	private int uno;
	private int qnaBno;
	private String reportReason;
	private Date reportRegdate;
}