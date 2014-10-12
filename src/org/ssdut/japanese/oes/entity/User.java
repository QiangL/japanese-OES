package org.ssdut.japanese.oes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//启用hibernate动态更新功能，只更新修改过的字段
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Table(name = "t_user")
public class User {

	@Column(name = "password")
	private String password;

	@Id
	@Column(name = "userId")//学号做主键
	private String userId;

	@Column(name = "userName")
	private String userName;
	
	@Column(name = "classNumber")
	private String classNumber;

	public String getPassword() {
		return password;
	}

	public String getUserId() {
		return userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}
	@Override
	public String toString() {
		return "User [password=" + password + ", userId=" + userId
				+ ", userName=" + userName + ", classNumber=" + classNumber
				+ "]";
	}
}
