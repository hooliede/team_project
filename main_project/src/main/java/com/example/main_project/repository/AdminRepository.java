package com.example.main_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.main_project.entity.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{
	//List<Cart> findByCartList(String memberId);
	//Cart에서 이용자의 아이디를 기준으로 장바구니 or 구매내역을 가져오게 하도록 sql 구문으로 바꾸는 코드
	Optional<Admin> findByAdminidAndPasswd(String adminid, String passwd);
}

//repository코드임
//controller에 적힌 로직이 sql 구문으로 바뀌어서 db에도 영향을 미치게 됨
//모든 로직이 sql 구문으로 바뀌는 건 아님 어떤 명령어를 sql 구문으로 바꾸게 할지 적어놓는 공간
