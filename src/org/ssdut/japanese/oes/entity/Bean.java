package org.ssdut.japanese.oes.entity;

/*
 * 用于前后端通信的Java Bean 便于json串的转化
 * id：试题id
 * type：试题类型
 * */
public class Bean {


	

	private String id;
	
	private String type;
	

	@Override
	public String toString() {
		return "Bean [id=" + id + ", type=" + type + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
