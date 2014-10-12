package org.ssdut.japanese.oes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/*
 * 
 * 带有图片资源的题目实体类
 * */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "t_question")
@GenericGenerator(name="idGenerator", strategy="uuid")
public class ImgQuestion extends Question{

	@Column(name = "resource")
	private String resource;
	
	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
	
	@Override
	public String toString() {
		return "ImgQuestion [resource=" + resource + ", id=" + id + ", name="
				+ name + ", url=" + url + ", description=" + description
				+ ", type=" + type + "]";
	}
}
