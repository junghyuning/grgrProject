package com.grgr.dto;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/*
작성자 : 김정현
수정일 - 수정내용 : 
09-13 : 테이블 생성 및 DTO 작성 / @Valid 어노테이션 및 report_error.properties 작성


CREATE TABLE REPORT_INFOBOARD(
REPORT_INFOBOARD_NO NUMBER PRIMARY KEY,
INFO_BNO NUMBER, CONSTRAINT REPORT_INFOBOARD_BNO_FK FOREIGN KEY (INFO_BNO) REFERENCES INFO_BOARD(INFO_BNO),
UNO NUMBER, CONSTRAINT REPORT_INFOBOARD_UNO_FK FOREIGN KEY (UNO) REFERENCES USERINFO(UNO),
REPORT_INFOBOARD_REASON VARCHAR2(500),
REPORT_INFOBOARD_REGDATE DATE DEFAULT SYSDATE
);

CREATE SEQUENCE REPORT_INFOBOARD_SEQ;

이름                       널?       유형            
------------------------ -------- ------------- 
REPORT_INFOBOARD_NO      NOT NULL NUMBER        
INFO_BNO                          NUMBER        
UNO                               NUMBER        
REPORT_INFOBOARD_REASON           VARCHAR2(500) 
REPORT_INFOBOARD_REGDATE          DATE    
*/

@Data
@Valid
public class ReportInfoboard {
	private int reportInfoboardNo;
	private int infoBno;
	private int uno;
	@NotEmpty
	private String reportInfoboardReason;
	private Date reportInfoboardRegdate;
}
