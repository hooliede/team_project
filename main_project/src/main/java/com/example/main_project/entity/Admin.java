package com.example.main_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Admin {
	
	@Id
	@JoinColumn(name = "adminid")
	private String adminid;
	
	private int level;
	private String passwd;
	private String name;
	
	
	
	public Admin(String adminid) {
		this.adminid = adminid;
	}

}
