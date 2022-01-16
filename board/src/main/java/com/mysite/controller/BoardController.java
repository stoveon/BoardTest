package com.mysite.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mysite.service.BoardServiceImple;
import com.mysite.vo.BoardVo;

@Controller("boardController")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardServiceImple boardService;
	
	@RequestMapping(value="boardIn", method=RequestMethod.GET)
	public String boardInsert() {
		logger.info("게시글 등록 GET 요청");
		return "board/boardForm";
	}
	
	@RequestMapping(value="boardIn", method=RequestMethod.POST)
	public String boardInsert(MultipartHttpServletRequest request) {
		logger.info("게시글 등록 POST 요청");
		BoardVo board = new BoardVo(request.getParameter("title"),request.getParameter("writer"),request.getParameter("content"),request.getFile("fileName").getOriginalFilename().replace(" ", ""));
		System.out.println(board.toString());
		return "board/boardList";
	}
	
	@RequestMapping(value="/board")
	public ModelAndView boardRead(MultipartHttpServletRequest request) {
		logger.info("게시글 상세 페이지 요청");
		ModelAndView view = new ModelAndView();
		view.setViewName("board/boardRead");
		
		return view;
	}
	
	@RequestMapping(value="/boardUp")
	public ModelAndView boardUpdate() {
		logger.info("게시글 수정 요청");
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/board/boardRead?bo=");
		
		return view;
	}

}
