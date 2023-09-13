package com.grgr.dto;

import lombok.Data;

/*
CREATE TABLE QNA_FILE( 
QNA_FILE_NO NUMBER PRIMARY KEY,
QNA_BNO NUMBER NOT NULL,
CONSTRAINT QNA_FILE_FK FOREIGN KEY(QNA_BNO) REFERENCES QNA_BOARD(QNA_BNO),
QNA_FILE_ORIGIN VARCHAR2(50),
QNA_FILE_UPLOAD VARCHAR2(50),
QNA_FILE_REGDATE DATE DEFAULT SYSDATE,
QNA_FILE_UPDATE DATE DEFAULT SYSDATE,
QNA_FILE_UPDATE_UNO NUMBER);

CREATE SEQUENCE QNA_FILE_SEQ;
 */

@Data
public class QnaFile {
	private int qnaFileNO;
	private int qnaBno;
	private String qnaFileOrigin;
	private String qnaFileUpload;
	private String qnaFileRegdate;
	private String qnaFileUpdate;
	private String qnaFileUpdateUno;
}
