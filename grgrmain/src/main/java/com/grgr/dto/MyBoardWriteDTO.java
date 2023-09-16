package com.grgr.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MyBoardWriteDTO {
	private int uno;
	private int bno;
	private String title;
	private String content;
	private int viewCnt;
	private String keyword;
	private Date regdate;
	private String nickName;
	private String boardtype;
}
