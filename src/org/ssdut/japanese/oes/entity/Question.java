package org.ssdut.japanese.oes.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/*
 * 试题的抽象基类
 * */
@MappedSuperclass
public abstract class Question {

	

	@Id
	@Column(name="id")
	@GeneratedValue(generator="idGenerator")
	protected String id;

	@Column(name="name")
	protected String name;
	
	@Column(name = "url")
	protected String url;//音频文件的资源访问路径
	
	@Column(name = "description")
	protected String description;
	
	@Column(name="type")
	protected String type;//试题的类型
	
	@Column(name = "uploadDate")
	protected String upDate;//上传时间
	
	@Column(name = "title")
	protected String title;//本题所属title 由上传时压缩包的文件名指定
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	

	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getUpDate() {
		return upDate;
	}

	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
