package org.ssdut.japanese.oes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/*
 * 试卷结构实体类
 * */
@Table(name="t_exam_struct")
@Entity
@GenericGenerator(name="idGenerator", strategy="uuid")
public class ExaminationStructure {

	

	@Id
	@Column(name = "id")
	@GeneratedValue(generator="idGenerator")
	String id;//试卷id
	
	@Column(name = "time")
	String time;//组卷时间
	
	@Column(name="structure")
	String structrue;//试卷结构文件名 指向一个xml文件 保存试卷内容
	
	@Column(name="status")
	int status;//试题状态 0：为备选状态  1：本次考试使用状态
	
	
	@Column(name="name")
	String name;//试卷命名
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStructrue() {
		return structrue;
	}

	public void setStructrue(String structrue) {
		this.structrue = structrue;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
