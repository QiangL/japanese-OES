package org.ssdut.japanese.oes.dao.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.ssdut.japanese.oes.dao.AdminDao;
import org.ssdut.japanese.oes.dao.TeacherDao;
import org.ssdut.japanese.oes.dao.UserDao;
import org.ssdut.japanese.oes.entity.Admin;
import org.ssdut.japanese.oes.entity.Teacher;
import org.ssdut.japanese.oes.entity.User;
import org.ssdut.japanese.oes.exception.UserNotFoundException;

@Repository(value = "AdminDao")
public class AdminDaoImlp extends BaseDao<Admin> implements AdminDao {

	@Autowired
	UserDao userDao;
	
	@Autowired
	TeacherDao teacherDao;
	
	@Override
	public Admin getAdminByName(String name) {
		final String HQL = "from Admin where Admin.name = ?";
		return (Admin) find(HQL,name).get(0);
	}

	@Override
	public void saveUserInBatch(List<User> users) {
		userDao.importUsers(users);
	}

	@Override
	public void deleteUser(String id) {
		User user = new User();
		user.setUserId(id);
		getHibernateTemplate().delete(user);
	}

	@Override
	public void saveTeacherInBatch(List<Teacher> teachers) {
		teacherDao.importTeacher(teachers);
	}

	@Override
	public void deleteTeacher(String id) {
		Teacher teacher = new Teacher();
		teacher.setTeacherId(id);
		getHibernateTemplate().delete(teacher);
	}


	@Override
	public Page pageQuery(String hql, int pageNo, int pageSize,
			Object... values) {
		return this.pagedQuery(hql, pageNo, pageSize, values);
	}

	@Override
	public void updateUser(User user) throws UserNotFoundException {
		User userDB = (User) getHibernateTemplate().
				find("from User u where u.userId = ?",user.getUserId()).get(0);
		if(userDB == null)
			throw new UserNotFoundException("用户不存在");
		try {
			getHibernateTemplate().update(
					this.updatePartial(User.class, userDB, user));
		} catch (DataAccessException | IllegalArgumentException
				| IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateTeacher(Teacher teacher) throws UserNotFoundException {
		Teacher tDB = (Teacher) getHibernateTemplate().
				find("from Teacher t where t.teacherId = ?",teacher.getTeacherId()).get(0);
		if(tDB == null)
			throw new UserNotFoundException("用户不存在");
		try {
			getHibernateTemplate().update(
					this.updatePartial(Teacher.class, tDB, teacher));
		} catch (DataAccessException | IllegalArgumentException
				| IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/*
	 * @function 部分字段更新时 其余字段保持原有数据 避免更新空值
	 * @param objectDB 数据库原有数据
	 * @param objectN 要更新的数据
	 * @param clazz 实体类类型
	 * 
	 * @return T 合并之后可用于更新的实体类
	 */
	private <T> T updatePartial(Class<T> clazz , T objectDB, T objectN) throws IllegalArgumentException, IllegalAccessException{
		Field[] fields = clazz.getDeclaredFields();
		for(int i = 0 ; i < fields.length; i++){
			fields[i].setAccessible(true);
			Object value = fields[i].get(objectN);
			if(value != null)//更新的字段
			{
				fields[i].set(objectDB, value);
			}
			fields[i].setAccessible(false);
		}
		return objectDB;
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException{
		AdminDaoImlp adminDaoImlp = new AdminDaoImlp();
		User userDB = new User();
		userDB.setUserName("yijun");
		userDB.setUserId("201293222");
		userDB.setPassword("123456");
		userDB.setClassNumber("1208");
		
		User userN = new User();
		userN.setPassword("1111111");
		System.out.println( adminDaoImlp.updatePartial(User.class, userDB, userN));
	}
}
