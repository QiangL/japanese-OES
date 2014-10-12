package org.ssdut.japanese.oes.service;

import java.util.List;

import org.ssdut.japanese.oes.dao.impl.Page;
import org.ssdut.japanese.oes.entity.Teacher;
import org.ssdut.japanese.oes.entity.User;
import org.ssdut.japanese.oes.exception.UserNotFoundException;

public interface AdminService {

	//管理员登陆验证
	public boolean login(String name , String password);
	
	//批量存储
	public void saveUserInBatch(List<User> users);
	
	public void saveTeacherInBatch(List<Teacher> teachers);
	
	//获得分页的学生信息
	public Page getPagedUser(int pageNo);
	
	public Page getPagedTeacher(int pageNo);
	
	//更新学生信息
	public void updateUser(User user) throws UserNotFoundException;
	
	public void updateTeacher(Teacher teacher) throws UserNotFoundException;
	
	//根据主键删除
	public void deleteUser(String id);
	
	public void deleteTeacher(String id);
	
}
