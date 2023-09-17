package com.grgr.dto;
/*
-- 회원정보 테이블
CREATE TABLE USERINFO(
    UNO NUMBER PRIMARY KEY,
    USER_NAME VARCHAR2(200) NOT NULL,
    NICKNAME VARCHAR2(200) NOT NULL,
    USER_PW VARCHAR2(200) NOT NULL,
    USER_ID VARCHAR2(200) CONSTRAINT USER_ID_UNIQUE UNIQUE,
    PHONE VARCHAR2(30),
    EMAIL VARCHAR2(50),
    USER_STATUS NUMBER DEFAULT 1,
    ACTIVE NUMBER DEFAULT 1,
    POINT NUMBER DEFAULT 0,
    BUSINESS_NO VARCHAR2(50),
    LAST_LOGIN DATE DEFAULT SYSDATE,
    REGDATE DATE DEFAULT SYSDATE,
    USER_UPDATE DATE DEFAULT SYSDATE,
    USER_UPDATE_UNO NUMBER
);

CREATE SEQUENCE UNO_SEQ;
*/

import java.util.Date;

import lombok.Data;

@Data
public class Userinfo {
	private int uno;
	private String userName;
	private String nickname;
	private String userPw;
	private String userId;
	private String phone;
	private String email;
	private int userStatus;
	private int active;
	private String businessNo;
	private Date lastLogin;
	private Date regdate;
	private Date userUpdate;
	private int userUpdateUno;
	private int userLoc;
}
