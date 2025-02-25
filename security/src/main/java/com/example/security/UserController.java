package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@Autowired
	PasswordEncoder pwdEncoder;
	
	
	@Autowired
	UserDAO userDao;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/user/login.do")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/user/join.do")
	public String join() {
		return "user/join";
	}
	
	
	@RequestMapping("/admin/*")
	public String admin_main() {
		return "admin/main";
	}

}
