package com.mysite.vo;

public class MemberUpdateCommand {
	private int agoNum;
	private int num;
	private String memberRank;
	private String name;
	private String phone;
	private String email;
	public MemberUpdateCommand(){}
	public MemberUpdateCommand(int agoNum, int num, String memberRank, String name, String phone, String email) {
		super();
		this.agoNum = agoNum;
		this.num = num;
		this.memberRank = memberRank;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	public int getAgoNum() {
		return agoNum;
	}
	public void setAgoNum(int agoNum) {
		this.agoNum = agoNum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getMemberRank() {
		return memberRank;
	}
	public void setMemberRank(String memberRank) {
		this.memberRank = memberRank;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "MemberUpdateCommand [agoNum=" + agoNum + ", num=" + num + ", memberRank=" + memberRank + ", name="
				+ name + ", phone=" + phone + ", email=" + email + "]";
	}
	
}
