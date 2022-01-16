package com.mysite.vo;

import java.util.Date;

public class BoardVo {

	private int num;
	private String writer;
	private String title;
	private String content;
	private String fileName;
	private Date regdate;
	public BoardVo(String writer, String title, String content, String fileName) {
		super();
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.fileName = fileName;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "BoardVo [num=" + num + ", writer=" + writer + ", title=" + title + ", content=" + content + ", fileName="
				+ fileName + ", regdate=" + regdate + "]";
	}
}
