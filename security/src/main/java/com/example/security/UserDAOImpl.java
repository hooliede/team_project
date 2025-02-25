package com.example.security;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDAOImpl implements UserDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.insert("user.insert", map);
	}

	@Override
	public Map<String, Object> detail(String userid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.detail", userid);
	}

}
