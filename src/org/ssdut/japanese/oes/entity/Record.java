package org.ssdut.japanese.oes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_record")
@GenericGenerator(name="systemUUID",strategy="uuid")
public class Record {

	

	@Column(name="url")
	private String url;

	@Id
	@GeneratedValue(generator="systemUUID")
	@Column(name="id")
	private String id;

	@Column(name="userId")
	private String userId;

	@Column(name="questionId")
	private String questionId;
	
	@Column(name = "status")
	private int status = 0;//录音的状态  0代表未评分  1代表已评分
	
	@Column(name = "score")
	private int score = -1;//评分 初始化为-1
	
	//questionName不进行持久化
	@Transient
	private String questionName;
	
	public int getStatus(){
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
