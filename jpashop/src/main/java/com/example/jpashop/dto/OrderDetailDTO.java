package com.example.jpashop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailDTO {
	private int idx;
	private int price;
	private int amount;
	private int money;
	private long productCode;
	private String productName;
	private String method;
	private String cardNumber;
	private int zipcode;
	private String address1;
	private String address2;
	private String tel;

}
