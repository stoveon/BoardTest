package com.mysite.vo;

public class MemberRegCommand {
	private String num;
	private String memberRank;
	private String name;
	private String phone;
	private String email;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
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
		return "MemberRegCommand [num=" + num + ", memberRank=" + memberRank + ", name=" + name
				+ ", phone=" + phone + ", email=" + email + "]";
	}
	
}
