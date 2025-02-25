package com.example.main_project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminDTO {
	private String adminid;
	private String passwd;
	private int level;

}

//admin은 이용자의 구매목록,개인정보,질문 등등을 다루는 거라 admin은 로그인만 하면 됨