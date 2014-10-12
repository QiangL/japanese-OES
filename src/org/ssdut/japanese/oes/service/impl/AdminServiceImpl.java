package org.ssdut.japanese.oes.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssdut.japanese.oes.cons.CommonConstant;
import org.ssdut.japanese.oes.dao.AdminDao;
import org.ssdut.japanese.oes.dao.impl.Page;
import org.ssdut.japanese.oes.entity.Admin;
import org.ssdut.japanese.oes.entity.Teacher;
import org.ssdut.japanese.oes.entity.User;
import org.ssdut.japanese.oes.exception.UserNotFoundException;
import org.ssdut.japanese.oes.service.AdminService;
import org.ssdut.japanese.oes.util.MD5;

@Service(value="AdminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;
	
	@Override
	public void saveUserInBatch(List<User> users) {
		adminDao.saveUserInBatch(users);
	}

	@Override
	public void saveTeacherInBatch(List<Teacher> teachers) {
		adminDao.saveTeacherInBatch(teachers);
	}

	@Override
	public Page getPagedUser(int pageNo) {
		final String hql = "from User";
		Page page = adminDao.pageQuery(hql, pageNo, CommonConstant.PAGE_SIZE);
		return page;
	}

	@Override
	public Page getPagedTeacher(int pageNo) {
		final String hql = "from Teacher";
		Page page = adminDao.pageQuery(hql, pageNo, CommonConstant.PAGE_SIZE);
		return page;
	}

	@Override
	public boolean login(String name, String password) {
		Admin admin = adminDao.getAdminByName(name);
		if(admin == null)//用户名不存在
			return false;
		try {
			String passMD5 = MD5.md5(password);
			//密码正确
			if(MD5.isEqual(MD5.toBytes(admin.getPassword()) ,MD5.toBytes(passMD5)))
				return true;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//密码不正确
		return false;
	}

	@Override
	public void updateUser(User user) throws UserNotFoundException {
		adminDao.updateUser(user);
	}

	@Override
	public void updateTeacher(Teacher teacher) throws UserNotFoundException {
		adminDao.updateTeacher(teacher);
	}

	@Override
	public void deleteUser(String id) {
		adminDao.deleteUser(id);
	}

	@Override
	public void deleteTeacher(String id) {
		adminDao.deleteTeacher(id);
	}

	

}
