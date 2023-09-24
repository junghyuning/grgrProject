package com.grgr.dto;

import java.util.Date;

import lombok.Data;

@Data
public class VisitCountVO {

	private Date regDate;

    private int visitCount;
}
