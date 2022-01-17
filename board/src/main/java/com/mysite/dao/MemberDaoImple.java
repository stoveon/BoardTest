package com.mysite.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysite.vo.MemberUpdateCommand;
import com.mysite.vo.MemberVo;

@Repository("memberDao")
public class MemberDaoImple implements IMemberDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void memberRegist(MemberVo member) throws Exception {
		sqlSessionTemplate.insert("memberRegist", member);
	}

	@Override
	public List<MemberVo> memberListAll() throws Exception {
		return sqlSessionTemplate.selectList("memberListAll");
	}

	@Override
	public MemberVo memberExist(int num) throws Exception {
		return sqlSessionTemplate.selectOne("memberExist", num);
	}

	@Override
	public void memberDelete(int num) throws Exception {
		sqlSessionTemplate.delete("memberDelete", num);
	}
	
	@Override
	public void memberUpdate(MemberUpdateCommand memUpCmd) throws Exception {
		sqlSessionTemplate.delete("memberUpdate", memUpCmd);		
	}

	@Override
	public List<MemberVo> searchMember(String searchWord) throws Exception {
		return sqlSessionTemplate.selectList("searchMember", searchWord);
	}

}
