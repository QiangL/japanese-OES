package org.ssdut.japanese.oes.cons;

public enum QuestionType {
	GENERAL,IMG;
	
	//从字符串获取枚举类型
	public static QuestionType getType(String type){
		String typeUpper = type.toUpperCase();
		if(QuestionType.GENERAL.toString().equals(typeUpper))
			return GENERAL;
		else if(QuestionType.IMG.toString().equals(typeUpper))
			return IMG;
		else 
			return null;	
	}
}
