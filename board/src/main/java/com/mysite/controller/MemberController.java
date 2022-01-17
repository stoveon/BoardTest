package com.mysite.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mysite.service.MemberServiceImple;
import com.mysite.vo.MemberRegCommand;
import com.mysite.vo.MemberVo;

@Controller("memberController")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String patternNumber = "^[0-9]*$";
	private static final String patternName = "^[가-힣a-zA-Z]*$";
	private static final String patternPhone = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
	private static final String patternEmail = "\\\\w+@\\\\w+\\\\.\\\\w+(\\\\.\\\\w+)?";
	
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
				Pattern.matches(patternEmail, memCmd.getEmail())) || memCmd.getPhone().length() < 11) {
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
		System.out.println(num);
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
		System.out.println(member.toString());
		view.setViewName("member/detailForm");
		view.addObject("num", member.getNum());
		view.addObject("name", member.getName());
		view.addObject("memberRank", member.getMemberRank());
		view.addObject("phone", member.getPhone());
		view.addObject("email", member.getEmail());
		return view;
	}	
	@RequestMapping(value="/memberUp", method=RequestMethod.POST)
	public ModelAndView memberUpdate(MemberVo member) throws Exception {
		logger.info("직원 정보 수정 요청");
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/member/boardRead?bo=");
		
		return view;
	}
	
	@RequestMapping(value="/memberDel")
	public ModelAndView memberDelete() throws Exception {
		logger.info("직원 정보 삭제 요청");
		ModelAndView view = new ModelAndView();
		
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
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public ModelAndView memberSearch() throws Exception {
		logger.info("직원 정보 검색 요청");
		ModelAndView view = new ModelAndView();
		view.setViewName("member/memberList");
		List<MemberVo> memberList = memberService.memberListAll();
		view.addObject("memberList", memberList);
		return view;
	}
}
