package com.example.main_project.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.main_project.dto.AdminDTO;
import com.example.main_project.entity.Admin;
import com.example.main_project.repository.AdminRepository;

@RestController
@RequestMapping("/api/admin/*")
public class AdminApi {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	//member에서는 회원가입을 먼저 진행하지만 관리자는 db를 통해서 아이디와 비밀번호를 만들 것이다.
//	react(프론트엔드) 실행 서버랑 spring boot(백엔드)실행 서버가 다르지만
//	url로 찍어서 관리자 페이지에 들어온다면 그거는 사고 그래서 url자체를 특정 경로로만
//	올 수 있게 하던가 모든 페이지에 session을 걸던가 constructor를 걸던가

	@PostMapping("login")
	public Map<String, Object> login(AdminDTO dto) {
		Optional<Admin> result = adminRepository.findByAdminidAndPasswd(dto.getAdminid(), dto.getPasswd());
		//repository를 활용해서 관리자의 정보를 dto로 가져오는 코드
		Map<String, Object> map = new HashMap<>();
		if (result.isPresent()) {
			Admin a = result.get();
			map.put("message", "success");
			map.put(null, a)
			
		}
	}
}
