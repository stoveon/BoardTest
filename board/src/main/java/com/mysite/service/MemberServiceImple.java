package com.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.dao.MemberDaoImple;
import com.mysite.vo.MemberVo;

@Service("memberService")
public class MemberServiceImple implements IMemberService{
	
	@Autowired
	private MemberDaoImple memberDao;

	@Override
	public void memberRegist(MemberVo member) throws Exception {
		member.setMemberRank(member.getMemberRank().replace(" ", ""));
		member.setName(member.getName().replace(" ", ""));
		member.setEmail(member.getEmail().replace(" ", ""));
		member.setPhone(member.getPhone().replace("-", ""));
		member.setPhone(member.getPhone().replace(" ", ""));
		System.out.println(member.toString());
		memberDao.memberRegist(member);
	}

	@Override
	public List<MemberVo> memberListAll() throws Exception {
		return memberDao.memberListAll();
	}

	@Override
	public MemberVo memberExist(int num) throws Exception {
		return memberDao.memberExist(num);
	}

	@Override
	public void memberDelete(int num) throws Exception {
		memberDao.memberDelete(num);
	}

}
