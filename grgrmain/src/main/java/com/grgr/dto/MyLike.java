package com.grgr.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MyLike {
	private int uno;
	private int bno;
	private int likeNo;
	private Date likeRegdate;
	private String title;
	private String content;
	private int viewCnt;
	private Date regdate;
	private String nickName;
	private String boardtype;
}
