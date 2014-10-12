package org.ssdut.japanese.oes.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/*
 * 普通题试题类 主要属性继承父类
 * */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="t_question")
@GenericGenerator(name="idGenerator", strategy="uuid")
public class GeneralQuestion extends Question{
	
	
	@Override
	public String toString() {
		return "GeneralQuestion [id=" + id + ", name=" + name + ", url=" + url
				+ ", description=" + description + ", type=" + type + "]";
	}
	
}
