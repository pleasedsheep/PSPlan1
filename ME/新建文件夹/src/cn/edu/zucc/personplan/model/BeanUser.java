package cn.edu.zucc.personplan.model;

import java.util.Date;

public class BeanUser {
	public static BeanUser currentLoginUser=null;
	private  String userid;
	private String pwd;
	private Date date;
	
	public void setUser_id(String userid) {
		this.userid = userid;
	}
	
	public void setUser_pwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void setRegister_time(Date date) {
		this.date = date;
	}
	
	public String getUser_id() {
		return userid;
	}
	
	public String getUser_pwd() {
		return pwd;
	}
	
	public Date getRegister_time() {
		return date;
	}
}
