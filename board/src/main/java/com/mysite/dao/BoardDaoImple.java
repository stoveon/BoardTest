package com.mysite.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("boardDao")
public class BoardDaoImple {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
}
