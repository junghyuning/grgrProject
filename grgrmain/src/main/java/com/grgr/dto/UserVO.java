package com.grgr.dto;

import java.util.Date;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserVO {
	private int uno;
	private String userName;
	private String nickName;
	private String userPw;

	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,15}$", message = "아이디를 형식에 맞게 입력해 주세요.")
	private String userId;
	private String phone;
	private String email;
	private int userStatus;
	private int active;
	private int point;
	private String businessNo;
	private Date lastLogin;
	private Date regDate;
	private Date userUpdate;
	private int userUpdateUno;
	private String userLoc;
	private String naverId;
	private String kakaoId;
	private String googleId;
}
