package com.grgr.dto;

import lombok.Data;

/*
CREATE TABLE INFO_FILE( 
INFO_FILE_NO NUMBER PRIMARY KEY,
INFO_BNO NUMBER NOT NULL,
CONSTRAINT INFO_FILE_FK FOREIGN KEY(INFO_BNO) REFERENCES INFO_BOARD(INFO_BNO),
INFO_FILE_ORIGIN VARCHAR2(50),
INFO_FILE_UPLOAD VARCHAR2(50),
INFO_FILE_REGDATE DATE DEFAULT SYSDATE,
INFO_FILE_UPDATE DATE DEFAULT SYSDATE,
INFO_FILE_UPDATE_UNO NUMBER);

CREATE SEQUENCE INFO_FILE_SEQ;
  
  
 
 이름                   널?       유형           
-------------------- -------- ------------ 
INFO_FILE_NO         NOT NULL NUMBER       
INFO_BNO             NOT NULL NUMBER       
INFO_FILE_ORIGIN              VARCHAR2(50) 
INFO_FILE_UPLOAD            VARCHAR2(50) 
INFO_FILE_REGDATE             DATE         
INFO_FILE_UPDATE              DATE         
INFO_FILE_UPDATE_UNO          NUMBER       
 */

@Data
public class InfoFile {
	private int infoFileNO;
	private int infoBno;
	private String infoFileOrigin;
	private String infoFileUpload;
	private String infoFileRegdate;
	private String infoFileUpdate;
	private String infoFileUpdateUno;
}
