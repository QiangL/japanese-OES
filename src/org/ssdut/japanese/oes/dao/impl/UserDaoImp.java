package org.ssdut.japanese.oes.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.ssdut.japanese.oes.dao.UserDao;
import org.ssdut.japanese.oes.entity.Class;
import org.ssdut.japanese.oes.entity.ExaminationStructure;
import org.ssdut.japanese.oes.entity.Record;
import org.ssdut.japanese.oes.entity.User;
import org.ssdut.japanese.oes.util.ExcelUtil;
import org.ssdut.japanese.oes.util.MD5;

@Repository(value = "UserDao")
public class UserDaoImp extends BaseDao<User> implements UserDao {

	static final String GET_USER_BY_ID = "from User u where u.userId = ?";
	
	@Override
	public User getUserByUserId(String userId) {
		
		List<User> result = find(GET_USER_BY_ID,userId);
		if(result.size() == 0)
			return null;
		return result.get(0);
	}

	@Override
	public boolean importUsers(List<User> users) {
		Session session = getSession();
		for (int i = 0; i < users.size(); i++) {
			User entity = users.get(i);
			//if (getUserByUserId(entity.getUserId()) != null)
		
			String password;
			try {
				password = MD5.md5(entity.getUserId());
				entity.setPassword(password);//初始密码设置为学号
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String classNumber = entity.getClassNumber();
			handleClass(classNumber);
			session.save(entity);
			if(i%20 == 0){
				session.flush();
				session.clear();
			}
		}
		return true;
	}
	
	//检测该班级是否在数据库中已存在 如不存在则保存
	private void handleClass(String classNumber){
		String HQL = "from Class c where c.name = ?";
		List list = getHibernateTemplate().find(HQL,classNumber);
		if(list.size() == 0){
			Class clazz = new Class();
			clazz.setName(classNumber);
			getHibernateTemplate().save(clazz);
		}
	}

	public static void main(String[] args) throws FileNotFoundException{
		String path = "/org/ssdut/japanese/profile/*.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(path);
		
		UserDao userDao = (UserDao) ctx.getBean("UserDao");
		File file = new File("学生导入.xls");
		List<User> users = ExcelUtil.ParseFromExcel(new FileInputStream(file), User.class);
		userDao.importUsers(users);
	}

	@Override
	public boolean saveRecord(Record record) {
		getHibernateTemplate().save(record);
		return true;
	}

	@Override
	public ExaminationStructure getES() {
		String HQL = "from ExaminationStructure es where es.status = ?";
		ExaminationStructure es = (ExaminationStructure) getHibernateTemplate().find(HQL,1).get(0);
		return es;
	}
	
}
