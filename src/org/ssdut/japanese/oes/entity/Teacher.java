package org.ssdut.japanese.oes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
//启用hibernate动态更新功能，只更新修改过的字段
@org.hibernate.annotations.Entity(dynamicUpdate=true)
@Table(name = "t_teacher")
public class Teacher{


	@Column(name = "password")
	private String password;

	@Id
	@Column(name = "teacherId")//教工号作主键
	private String teacherId;

	@Column(name = "teacherName")
	private String teacherName;

	public String getPassword() {
		return password;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	

	@Override
	public String toString() {
		return "Teacher [password=" + password + ", teacherId=" + teacherId
				+ ", teacherName=" + teacherName + "]";
	}
}
