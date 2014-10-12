package org.ssdut.japanese.oes.dao;

import java.util.List;

import org.ssdut.japanese.oes.cons.QuestionType;
import org.ssdut.japanese.oes.dao.impl.Page;
import org.ssdut.japanese.oes.entity.Class;
import org.ssdut.japanese.oes.entity.ExaminationStructure;
import org.ssdut.japanese.oes.entity.Question;
import org.ssdut.japanese.oes.entity.Record;
import org.ssdut.japanese.oes.entity.Teacher;
import org.ssdut.japanese.oes.entity.User;
import org.ssdut.japanese.oes.exception.WrongTypeException;


public interface TeacherDao {
	public Teacher getTeacherById(String teacherId);
	
	//导入教师信息
	public boolean importTeacher(List<Teacher> teachers);
	
	//导入试题
	public boolean saveQuestion(List<Question> ques);
	
	//获取分页的试题信息
	public Page getPageGeneralQuestions(int page);
	
	public Page getPageImgQuestions(int page);
	
	//获取分页的试卷
	public Page getPagePaper(int page);
	
	//保存试卷信息
	public void saveExamStruct(ExaminationStructure es);
	
	//根据id查询试题
	public Question getQuestionById(String id,QuestionType type) throws WrongTypeException;
	
	//设置即将使用的试卷
	public void setActivePaper(String id );
	
	//获取所有班级号
	public List<Class> getClassInfo();
	
	public List<User> getStudentsInfo(String classNumber);
	
	//获取指定学生的所有录音
	public List<Record> getRecords(String userId);
	
	public Record getRecord(String rid);
	
	//为某个录音答案评分
	public boolean grade(String recordId,int score);
}
