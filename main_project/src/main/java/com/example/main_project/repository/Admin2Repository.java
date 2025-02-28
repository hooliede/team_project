package com.example.main_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.main_project.entity.Admin2;


@Repository
public interface Admin2Repository extends JpaRepository<Admin2, String> {
	
	Optional<Admin2> findByadminidAndPasswd(String adminid, String passwd);
	
	//하나의 공통 칼럼을 기준으로 나는 data를 비교하고 싶은 거잖아 그러면 entity 코드 조인보단
	//여기서 query문 작성이 더 적합해
	
	@Query("SELECT ")
}
