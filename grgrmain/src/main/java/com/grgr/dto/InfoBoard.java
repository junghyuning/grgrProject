package com.grgr.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

/*
CREATE TABLE INFO_BOARD (INFO_BNO NUMBER PRIMARY KEY,
UNO NUMBER NOT NULL, CONSTRAINT INFO_BOARD_FK FOREIGN KEY(UNO) references USERINFO (UNO),
INFO_KEYWORD NUMBER,
INFO_TITLE VARCHAR2(100) NOT NULL,
INFO_CONTENT VARCHAR2(2000) NOT NULL,
INFO_LOC VARCHAR2(50),
INFO_VIEW_CNT NUMBER DEFAULT 0,
INFO_REPORT_CNT NUMBER DEFAULT 0,
INFO_BLINDSTATE NUMBER DEFAULT 1 NOT NULL CHECK(INFO_BLINDSTATE BETWEEN 1 AND 3),
INFO_REGDATE DATE DEFAULT SYSDATE,
INFO_UPDATE DATE DEFAULT SYSDATE,
INFO_UPDATE_UNO NUMBER);
CREATE SEQUENCE INFO_SEQ;

0913 - infoReportNo -> infoReportCnt 로 변경(컬럼명 변경)
 */

@Data
public class InfoBoard {
	
	private String infoTitle;
	private String infoContent;
	private String infoLoc;
	private int infoBno;
	private int uno;
	private String infoKeyword;
	private int infoViewCnt;
	private int infoReportCnt;
	private int infoBlindstate;
	private int infoUpdateUno;
	private Date infoRegdate;
	private Date infoUpdate;
	private String nickname;
	private String thumbnail;
	
}
