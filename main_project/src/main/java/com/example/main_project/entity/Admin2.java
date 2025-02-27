package com.example.main_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Admin2 {
	
	@Id
	private String adminid;
	
	private int level;
	private String passwd;
	private String name;
	
	
	public Admin2(String adminid) {
		this.adminid = adminid;
	}

}
