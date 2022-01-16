package com.mysite.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

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
	public String boardInsert(@ModelAttribute BoardVo board, MultipartFile multiFile) {
		logger.info("게시글 등록 POST 요청");
		System.out.println(board.toString());
		return "board/boardList";
	}

}
