package com.yogafire.model.dto;

import java.sql.Timestamp;

public class User {
	private String userId;
	private String password;
	private String name;
	private String birthday;
	private String email;
	private String phone;
	private String nickname;
	private Timestamp createTime; // ?????
	private String deleteYn;
	private int height;
	private int weight;
	private int sex;
	private int age;
	
	
	public User() {
	}
	
	public User(String userId, String password, String name, String birthday, String email, String phone,
			String nickname, Timestamp createTime, String deleteYn, int height, int weight, int sex, int age) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.nickname = nickname;
		this.createTime = createTime;
		this.deleteYn = deleteYn;
		this.height = height;
		this.weight = weight;
		this.sex = sex;
		this.age = age;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", birthday=" + birthday
				+ ", email=" + email + ", phone=" + phone + ", nickname=" + nickname + ", createTime=" + createTime
				+ ", deleteYn=" + deleteYn + ", height=" + height + ", weight=" + weight + ", sex=" + sex + ", age="
				+ age + "]";
	}
	
	
}
