package org.ssdut.japanese.oes.dao.impl;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.ssdut.japanese.oes.cons.CommonConstant;
import org.ssdut.japanese.oes.cons.QuestionType;
import org.ssdut.japanese.oes.dao.TeacherDao;
import org.ssdut.japanese.oes.entity.Class;
import org.ssdut.japanese.oes.entity.ExaminationStructure;
import org.ssdut.japanese.oes.entity.ImgQuestion;
import org.ssdut.japanese.oes.entity.Question;
import org.ssdut.japanese.oes.entity.Record;
import org.ssdut.japanese.oes.entity.Teacher;
import org.ssdut.japanese.oes.entity.User;
import org.ssdut.japanese.oes.exception.WrongTypeException;
import org.ssdut.japanese.oes.util.MD5;

@Repository(value = "TeacherDao")
public class TeacherDaoImpl extends BaseDao<Teacher> implements TeacherDao {

	private final String GET_TEACHER_BY_NAME = "from Teacher t where t.teacherId = ?";
	
	@Override
	public Teacher getTeacherById(String teacherId) {
		
		List<Teacher> result = find(GET_TEACHER_BY_NAME,teacherId);
		if(result.size() == 0)
			return null;
		return result.get(0);
	}

	@Override
	public boolean importTeacher(List<Teacher> teachers) {
		for (int i = 0; i < teachers.size(); i++) {
			Teacher entity = teachers.get(i);
			String password;
			try {
				password = MD5.md5(entity.getTeacherId());
				entity.setPassword(password);//初始密码设置为学号
				save(entity);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}			
		}
		return true;
	}

	/*
	 * 将课程信息持久化
	 * */
	@Override
	public boolean saveQuestion(List<Question> ques) {
		Session session = getSession();
		for(int i = 0 ; i < ques.size();i++){
			session.save(ques.get(i));
			if(i % 20 == 0){//每保存20个就将内存中的缓存写入数据库 防止内存溢出
				session.flush();
				session.clear();
			}
		}
		return true;
	}

	/*
	 * 获取分页的普通题
	 * */
	@Override
	public Page getPageGeneralQuestions(int pageNo) {
		Page page =  pagedQuery("from GeneralQuestion g where g.type='general'", 
				pageNo, CommonConstant.PAGE_SIZE);
		return page;
	}

	@Override
	public void saveExamStruct(ExaminationStructure es) {
		getHibernateTemplate().save(es);
	}

	@Override
	public Question getQuestionById(String id,QuestionType type) throws WrongTypeException {
		String classType = null;
		switch (type) {
		case GENERAL:
			classType = "GeneralQuestion";
			break;
		case IMG:
			classType = "ImgQuestion";
			break;
		default:
			throw new WrongTypeException("wrong question type");
		}
		String hql = "from " + classType +" q where q.id = ?";
		Question question = (Question) getHibernateTemplate().
				find(hql,id).get(0);
		return question;
	}
	
	public void saveQuestion(Question question){
		getHibernateTemplate().save(question);
	}

	@Override
	public Page getPageImgQuestions(int pageNo) {
		Page page =  pagedQuery("from ImgQuestion i where i.type='img'", 
				pageNo, CommonConstant.PAGE_SIZE);
		return page;
	}

	@Override
	public Page getPagePaper(int pageNo) {
		Page page = pagedQuery("from ExaminationStructure es", pageNo, 
				CommonConstant.PAGE_SIZE);
		return page;
	}

	@Override
	public void setActivePaper(String id) {
		//先将激活状态的试卷状态置0
		String HQL1 = "from ExaminationStructure es where es.status = ?";
		List<ExaminationStructure> list = getHibernateTemplate().find(HQL1,1);
		if(list.size() != 0){
			for (ExaminationStructure es : list) {
				es.setStatus(0);
				getHibernateTemplate().update(es);
			}
		}
		
		//将即将使用的试卷状态置1
		String HQL = "from ExaminationStructure es where es.id = ?";
		ExaminationStructure es = (ExaminationStructure) 
				getHibernateTemplate().find(HQL,id).get(0);
		es.setStatus(1);//设为激活状态
		getHibernateTemplate().update(es);
	}

	@Override
	public List<Class> getClassInfo() {
		String HQL = "from Class";
		List<Class> result = getHibernateTemplate().find(HQL);
		return result;
	}

	@Override
	public List<User> getStudentsInfo(String classNumber) {
		String HQL = "from User u where u.classNumber = ?";
		List<User> result = getHibernateTemplate().find(HQL,classNumber);
		return result;
	}

	@Override
	public List<Record> getRecords(String userId) {
		String HQL = "from Record r  where r.userId = ?";
		List<Record> result = getHibernateTemplate().find(HQL,userId);
		//没有用多表关联
		for (Record record : result) {
			Question q = (Question) getHibernateTemplate().
					find("from GeneralQuestion g where g.id =?",record.getQuestionId()).get(0);
			record.setQuestionName(q.getDescription());
		}
		return result;
	}

	@Override
	public Record getRecord(String rid) {
		String HQL = "from Record r where r.id = ?";
		Record record = (Record) getHibernateTemplate().find(HQL,rid).get(0);
		return record;
	}

	@Override
	public boolean grade(String recordId, int score) {
		String HQL = "from Record r where r.id = ?";
		Record record = (Record) getHibernateTemplate().find(HQL,recordId).get(0);
		if(record == null)
			return false;
		record.setScore(score);
		record.setStatus(1);
		getHibernateTemplate().update(record);
		return true;
	}
}
