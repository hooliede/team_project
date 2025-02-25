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
public class Admin {
	
	@Id
	private String adminid;
	
	private int level;
	private String passwd;
	
	
	public Admin(String adminid) {
		this.adminid = adminid;
	}

}
