package org.ssdut.japanese.oes.service.impl;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssdut.japanese.oes.cons.QuestionType;
import org.ssdut.japanese.oes.dao.TeacherDao;
import org.ssdut.japanese.oes.dao.impl.Page;
import org.ssdut.japanese.oes.entity.Bean;
import org.ssdut.japanese.oes.entity.ExaminationStructure;
import org.ssdut.japanese.oes.entity.Question;
import org.ssdut.japanese.oes.entity.Record;
import org.ssdut.japanese.oes.entity.Teacher;
import org.ssdut.japanese.oes.entity.Class;
import org.ssdut.japanese.oes.entity.User;
import org.ssdut.japanese.oes.exception.NameReuseException;
import org.ssdut.japanese.oes.exception.UserNotFoundException;
import org.ssdut.japanese.oes.exception.WrongTypeException;
import org.ssdut.japanese.oes.service.TeacherService;
import org.ssdut.japanese.oes.util.MD5;
import org.ssdut.japanese.oes.util.XMLUtil;
import org.ssdut.japanese.oes.util.dateUtil;
import org.ssdut.japanese.oes.util.decompression;

@Service(value = "TeacherService")
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	TeacherDao teacherDao;
	
	@Override
	public boolean login(Teacher teacher) throws UserNotFoundException {
		Teacher t = teacherDao.getTeacherById(teacher.getTeacherId());
		if(t == null)
			throw new UserNotFoundException("用户不存在");
		try {
			String digest = MD5.md5(teacher.getPassword());
			//密码不匹配
			if (!MD5.isEqual(MD5.toBytes(digest), MD5.toBytes(t.getPassword()))) 
				return false;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return true;
	}

	
	/*
	 * @param fileName:压缩文件全路径名称  resoucePath:资源保存路径    type:试题类型
	 * 
	 * */
	@Override
	public boolean uploadFile(String fileName, String resourcePath, String type) {
		
		QuestionType t = QuestionType.getType(type);
		if(t == null)
			try {
				throw new WrongTypeException("上传试题类型错误");
			} catch (WrongTypeException e) {
				e.printStackTrace();
			}
		List<Question> quesInfo = decompression.unZip(fileName, resourcePath + "/",t);
		
		//将试题信息持久化
		return teacherDao.saveQuestion(quesInfo);	
	}


	/*
	 * @function 获取分页的各类型的题目
	 * @param page 页号
	 * @return Map 包括各种题型的数据  当前页号：currentPage 总页数：pageCount
	 * */
	@Override
	public Page getQuestion(QuestionType type ,int pageNo) {
		
		//普通试题
		
		Page page = null; 
		switch (type) {
		case GENERAL:
			page = teacherDao.getPageGeneralQuestions(pageNo);//普通试题
			break;
		case IMG:
			page = teacherDao.getPageImgQuestions(pageNo);
		default:
			break;
		}
		
		return page;
	}


	@Override
	public void generateTestPaper(List<Bean> questionList,String name) throws NameReuseException {
		List<Question> list = new ArrayList<Question>();
		//从数据库获取指定的试题信息
		for (Bean bean : questionList) {
			try {
				Question questionDB = teacherDao.getQuestionById(
						bean.getId(),QuestionType.getType(bean.getType()));
				list.add(questionDB);
			} catch (WrongTypeException e) {
				e.printStackTrace();
			}
		}
		String paperName = name.substring(name.lastIndexOf("/") + 1);
		String generatingTime = dateUtil.getCurrentTime();
		name +=  ".xml";
		File file = new File(name);
		if (file.exists()) {
			throw new NameReuseException("该试卷已存在");
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//生成试题结构的xml文件
		Document document = XMLUtil.generateDocument(list);
		XMLUtil.saveDocument(document, file);
		
		//把试卷信息持久化到数据库
		ExaminationStructure es = new ExaminationStructure();
		es.setStructrue(name);//文件路径
		es.setTime(generatingTime);
		es.setName(paperName);
		es.setStatus(0);
		teacherDao.saveExamStruct(es);
	}


	@Override
	public Page getPaper(int pageNo) {
		return teacherDao.getPagePaper(pageNo); 
	}


	@Override
	public void setActivePaper(String id) {
		teacherDao.setActivePaper(id);
	}


	@Override
	public List<String> getClassInfo() {
		List<Class> list = teacherDao.getClassInfo();
		List<String> result = new ArrayList<String>();
		for (Class clazz : list) {
			result.add(clazz.getName());
		}
		return result;
	}


	@Override
	public List<User> getStudentInfo(String classNumber) {
		List<User> list = teacherDao.getStudentsInfo(classNumber);
		return list;
	}


	@Override
	public List<Record> getRecords(String userId) {
		return teacherDao.getRecords(userId);
	}


	@Override
	public Record getRecord(String rid) {
		return teacherDao.getRecord(rid);
	}


	@Override
	public boolean score(String recordId, int score) {
		return teacherDao.grade(recordId, score);
	}
 }
