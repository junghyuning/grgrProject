package com.grgr.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserVO {
	private int uno;
	private String userName;
	private String nickName;
	private String userPw;
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
}
