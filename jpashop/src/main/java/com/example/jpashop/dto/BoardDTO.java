package com.example.jpashop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
	private int idx;
	private int hit;
	private String title;
	private String contents;
	private String userid;
	private String regdate;
	private String[] files;
	

}
