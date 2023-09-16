package com.grgr.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MyCommentDTO {
	private int bno;
	private int uno;
	private String content;
	private Date regdate;
	private String boardtype;
}
