package com.grgr.dto;

import lombok.Data;

@Data
public class MainProduct {
	private int productId;
	private int productBlindState;
	private String productTitle;
	private String productContent;
	private String productFileUpload;
}
