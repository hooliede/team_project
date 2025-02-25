package com.example.jpashop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cart {
	@Id//프라이머리 키를 나타내는 어노테이션
	@GeneratedValue(strategy = GenerationType.AUTO)//시퀀스 어노테이션
	private long cartId;//cartId에서 대문자 앞에서 _ 생성 => cart_id
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private Member member;//member entity를 기준으로 만든 변수
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productCode")
	private Product product;
	
	private int amount;
}
