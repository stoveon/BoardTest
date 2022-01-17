package com.mysite.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysite.service.MemberServiceImple;
import com.mysite.vo.MemberRegCommand;
import com.mysite.vo.MemberUpdateCommand;
import com.mysite.vo.MemberVo;

@Controller("memberController")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String patternNumber = "^[0-9]*$";
	private static final String patternName = "^[가-힣a-zA-Z]*$";
	private static final String patternPhone = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$";
	private static final String patternEmail = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}";
	
	@Autowired
	private MemberServiceImple memberService;
	
	@RequestMapping(value="/memberIn", method=RequestMethod.GET)
	public String memberInsert() {
		logger.info("직원 등록 GET 요청");
		return "member/registForm";
	}
	
	@RequestMapping(value="/memberIn", method=RequestMethod.POST)
	public ModelAndView memberInsert(MemberRegCommand memCmd) throws Exception {
		logger.info("직원 등록 POST 요청");
		ModelAndView view = new ModelAndView();	
		if(memCmd.getNum() == "" || memCmd.getNum() == null ||
			memCmd.getMemberRank() == "" ||memCmd.getMemberRank() == null || 
			memCmd.getName() == "" || memCmd.getName() == null ||
			memCmd.getPhone() == "" || memCmd.getPhone() == null ||
			memCmd.getEmail() == "" || memCmd.getEmail() == null) {
			view.setViewName("member/registForm");
			view.addObject("reg", "isnull");			
			view.addObject("num", memCmd.getNum());
			view.addObject("name", memCmd.getName());
			view.addObject("memberRank", memCmd.getMemberRank());
			view.addObject("phone", memCmd.getPhone());
			view.addObject("email", memCmd.getEmail());
			return view;
		}
		if(!(Pattern.matches(patternNumber, memCmd.getNum()) ||
				Pattern.matches(patternName, memCmd.getName()) || 
				Pattern.matches(patternPhone, memCmd.getPhone()) ||
				Pattern.matches(patternEmail, memCmd.getEmail())) || memCmd.getPhone().length() < 13) {
			view.setViewName("member/registForm");
			view.addObject("reg", "nottype");			
			view.addObject("num", memCmd.getNum());
			view.addObject("name", memCmd.getName());
			view.addObject("memberRank", memCmd.getMemberRank());
			view.addObject("phone", memCmd.getPhone());
			view.addObject("email", memCmd.getEmail());
			return view;
		}
		int num = Integer.parseInt(memCmd.getNum());
		if(memberService.memberExist(num) != null) {
			view.setViewName("member/registForm");
			view.addObject("reg", "chagenum");			
			view.addObject("num", memCmd.getNum());
			view.addObject("name", memCmd.getName());
			view.addObject("memberRank", memCmd.getMemberRank());
			view.addObject("phone", memCmd.getPhone());
			view.addObject("email", memCmd.getEmail());
			return view;			
		}else {
			MemberVo member = new MemberVo(num, memCmd.getMemberRank(), memCmd.getName(), memCmd.getPhone(), memCmd.getEmail());
			memberService.memberRegist(member);	
			view.setViewName("redirect:memberLi");
			return view;			
		}
	}
	@RequestMapping(value="/memberUp", method=RequestMethod.GET)
	public ModelAndView memberUpdate(int num) throws Exception {
		logger.info("직원 정보 수정 요청");
		ModelAndView view = new ModelAndView();
		MemberVo member = memberService.memberExist(num);
		view.setViewName("member/detailForm");
		view.addObject("num", member.getNum());
		view.addObject("name", member.getName());
		view.addObject("memberRank", member.getMemberRank());
		view.addObject("phone", member.getPhone());
		view.addObject("email", member.getEmail());
		return view;
	}	
	@RequestMapping(value="/memberUp", method=RequestMethod.POST)
	public ModelAndView memberUpdate(int agoNum, MemberRegCommand member) throws Exception {
		logger.info("직원 정보 수정 요청");
		ModelAndView view = new ModelAndView();
		if(member.getNum() == "" || member.getNum() == null ||
			member.getMemberRank() == "" ||member.getMemberRank() == null || 
			member.getName() == "" || member.getName() == null ||
			member.getPhone() == "" || member.getPhone() == null ||
			member.getEmail() == "" || member.getEmail() == null) {
				view.setViewName("member/registForm");
				view.addObject("reg", "isnull");			
				view.addObject("num", member.getNum());
				view.addObject("name", member.getName());
				view.addObject("memberRank", member.getMemberRank());
				view.addObject("phone", member.getPhone());
				view.addObject("email", member.getEmail());
				return view;
			}
			if(!(Pattern.matches(patternNumber, member.getNum()) ||
					Pattern.matches(patternName, member.getName()) || 
					Pattern.matches(patternPhone, member.getPhone()) ||
					Pattern.matches(patternEmail, member.getEmail())) || member.getPhone().length() < 13) {
				view.setViewName("member/registForm");
				view.addObject("reg", "nottype");			
				view.addObject("num", member.getNum());
				view.addObject("name", member.getName());
				view.addObject("memberRank", member.getMemberRank());
				view.addObject("phone", member.getPhone());
				view.addObject("email", member.getEmail());
				return view;
			}
		int num = Integer.parseInt(member.getNum());
		if(memberService.memberExist(num) != null) {
			view.setViewName("member/detailForm");
			view.addObject("reg", "chagenum");			
			view.addObject("num", member.getNum());
			view.addObject("name", member.getName());
			view.addObject("memberRank", member.getMemberRank());
			view.addObject("phone", member.getPhone());
			view.addObject("email", member.getEmail());
			return view;			
		}else {
			MemberUpdateCommand memUpCmd = new MemberUpdateCommand(agoNum, num, 
					member.getMemberRank(), member.getName(), member.getPhone(), member.getEmail());
			memberService.memberUpdate(memUpCmd);
			view.setViewName("redirect:memberLi");
			return view;			
		}
	}
	
	@RequestMapping(value="/memberDel")
	public ModelAndView memberDelete(int num) throws Exception {
		logger.info("직원 정보 삭제 요청");
		ModelAndView view = new ModelAndView();
		memberService.memberDelete(num);
		view.setViewName("redirect:memberLi");
		return view;
	}
	
	@RequestMapping(value="/memberLi")
	public ModelAndView memberList() throws Exception {
		logger.info("직원 정보 목록 요청");
		ModelAndView view = new ModelAndView();
		view.setViewName("member/memberList");
		List<MemberVo> memberList = memberService.memberListAll();
		view.addObject("memberList", memberList);
		return view;
	}
	
	@RequestMapping(value="/search")
	public ModelAndView memberSearch(String searchWord) throws Exception {
		logger.info("직원 정보 검색 요청");
		ModelAndView view = new ModelAndView();
		if(searchWord == null) {
			view.setViewName("redirect:memberLi");			
		}
		List<MemberVo> memberList = memberService.searchMember(searchWord);
		view.addObject("memberList", memberList);
		view.addObject("searchWord", searchWord);
		view.setViewName("member/memberList");
		return view;
	}
	
	@RequestMapping(value="/downDoc")
	public ModelAndView downloadDoc(@RequestParam(required=false) String searchWord) throws Exception{
		logger.info("파일 다운로드 요청");
		ModelAndView view = new ModelAndView();
		List<MemberVo> memberList;
		if(searchWord != null) {
			memberList = memberService.searchMember(searchWord);
		}else {
			memberList = memberService.memberListAll();
		}
		for(MemberVo m : memberList) {
			System.out.println(m.toString());
		}
		Date todayTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String str = sdf.format(todayTime);
        memberService.WriteListToExcelFile("result_" + str + ".xlsx", memberList);
        view.setViewName("redirect:memberLi");
		return view;
	}
}
