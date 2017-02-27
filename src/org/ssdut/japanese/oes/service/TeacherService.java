package org.ssdut.japanese.oes.service;

import java.util.List;
import java.util.Map;

import org.ssdut.japanese.oes.cons.QuestionType;
import org.ssdut.japanese.oes.dao.impl.Page;
import org.ssdut.japanese.oes.entity.Bean;
import org.ssdut.japanese.oes.entity.Record;
import org.ssdut.japanese.oes.entity.Teacher;
import org.ssdut.japanese.oes.entity.User;
import org.ssdut.japanese.oes.exception.NameReuseException;
import org.ssdut.japanese.oes.exception.UserNotFoundException;

public interface TeacherService {

	public boolean login(Teacher teacher) throws UserNotFoundException;
	
	//处理上传的试题压缩文件 
	public boolean uploadFile(String fileName,String resourcePath,String type);
	
	//获取分页的各类题型
	public Page getQuestion(QuestionType type,int page);
	
	//获取分页的试卷信息
	public Page getPaper(int pageNo);
	
	//组卷
	public void generateTestPaper(List<Bean> questionList,String name) throws NameReuseException;
	
	//设置即将使用的试卷
	public void setActivePaper(String id);
	
	//获取班级信息
	public List<String> getClassInfo();
	
	//获取指定班级所有学生的学号
	public List<User> getStudentInfo(String classNumber);
	
	//获取指定学生的录音信息
	public List<Record> getRecords(String userId);
	
	public Record getRecord(String rid);
	
	//为某个录音打分
	public boolean score(String recordId,int score);
}
