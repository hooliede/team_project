package com.example.main_project.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.main_project.dto.Admin2DTO;
import com.example.main_project.dto.AdminDTO;
import com.example.main_project.entity.Admin;
import com.example.main_project.entity.Admin2;
import com.example.main_project.repository.Admin2Repository;
import com.example.main_project.repository.AdminRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin/*")
public class AdminApi {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	Admin2Repository admin2Repository;
	
	@Autowired
	ModelMapper modelMapper;
	
	//member에서는 회원가입을 먼저 진행하지만 관리자는 db를 통해서 아이디와 비밀번호를 만들 것이다.
//	react(프론트엔드) 실행 서버랑 spring boot(백엔드)실행 서버가 다르지만
//	url로 찍어서 관리자 페이지에 들어온다면 그거는 사고 그래서 url자체를 특정 경로로만
//	올 수 있게 하던가 모든 페이지에 session을 걸던가 interrupt를 걸던가

	@RequestMapping("first_login")
	public Map<String, Object> first_login(@RequestBody AdminDTO dto, HttpSession session) {
		/* data를 react로부터 받을 때 사용되는 것들이 많음 일단 RequestParam으로 받아도 좋지만 보안상 혹은 여러 이유로 객체 받는 게 좋음
		   그렇다보니 dto 객체로 받는 RequestBody나 RequestAttribute로 받는 게 좋음
		   RequestBody와 RequestAttribute의 차이는 react에서 data를 보낼 때 형식에서의 차이임
		   body: json이면 => RequestBody, body: form이면 => RequestAttribute		 
		 */
		Optional<Admin> result = adminRepository.findByAdminidAndPasswd(dto.getAdminid(), dto.getPasswd());
		/* 클라이언트 뷰에서 받아온 값이 실제 admin table에 있는지 확인하는 로직
		   그런데 이제 그 값이 일치하면 실제 db에 있는 값이면 result에 그 값이 들어감 아니면 result가 비어버림
		   비어있을 수도 있으니 optional을 통해서 is.Present를 사용 => null을 직접적으로 다루지 않게 처리
		 */		
		Map<String, Object> map = new HashMap<>();
		if (result.isPresent()) {
			Admin a = result.get();
			session.setAttribute("adminid", a.getAdminid());			
			session.setAttribute("level", a.getLevel());
			//세션에 필요한 값을 저장
			map.put("message", "success");
			map.put("adminid", a.getAdminid());						
		} else {
			map.put("message", "error");
		}
		return map;
	}
	
	
	@RequestMapping("second_login")
	public Map<String, Object> second_login(Admin2DTO dto, HttpSession session, HttpServletRequest request) {
		Optional<Admin2> result = admin2Repository.findByadminidAndPasswd(dto.getAdminid(), dto.getPasswd());
		Map<String, Object> map = new HashMap<>();
		if(result.isEmpty()) {
			map.put("message", "error");
			map.put("status", 401);
			return map;
		}
		Integer sessionLevel = (Integer) session.getAttribute("level");
		//int sessionLevel = (int) session.getAttribute("level");
		if(sessionLevel == null) {
			map.put("message", "error");
			map.put("status", 401);
			return map;
		}
		
		if(!Objects.equals(result.get().getLevel(), sessionLevel)) {
			map.put("message", "본인에게 해당되는 관리자 계정이 아닙니다.");
			map.put("message", 401);
			return map;			
		}
		session.invalidate();//로그인을 성공했으면 2차 로그인 정보를 session에 넣기 위해 초기화
		session = request.getSession(true);
		session.setAttribute("adminid", result.get().getAdminid());
		session.setAttribute("name", result.get().getName());
		
		map.put("message", "success");
		map.put("status", 200);
		map.put("adminid", result.get().getAdminid());
		map.put("name", result.get().getName());
		map.put("level", result.get().getLevel());		
		
		return map;
	}
	
	
	
	
	
	
	
	
	
	
}
