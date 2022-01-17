package com.mysite.service;

import java.util.List;

import com.mysite.vo.MemberUpdateCommand;
import com.mysite.vo.MemberVo;

public interface IMemberService {
	//직원 등록
	public void memberRegist(MemberVo member) throws Exception;
	//직원 목록 조회
	public List<MemberVo> memberListAll() throws Exception;
	//등록된 직원번호 확인
	public MemberVo memberExist(int num) throws Exception;
	//직원 삭제
	public void memberDelete(int num) throws Exception;
	//직원 수정
	public void memberUpdate(MemberUpdateCommand memUpCmd) throws Exception;
	//직원 검색
	public List<MemberVo> searchMember(String searchWord) throws Exception;
}
