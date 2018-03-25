package com.emazdoor.attendanceapp;

public class SignUpPOJO {

	private String name;
	private String subject;
	private String class_;
	private String department;
	private String password;

	public SignUpPOJO() {
	}

	public SignUpPOJO(String name, String subject, String class_,
			String department, String password) {
		super();
		this.name = name;
		this.subject = subject;
		this.class_ = class_;
		this.department = department;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getClass_() {
		return class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
