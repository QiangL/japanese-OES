package org.ssdut.japanese.oes.dao;

import java.util.List;

import org.ssdut.japanese.oes.dao.impl.Page;
import org.ssdut.japanese.oes.entity.Admin;
import org.ssdut.japanese.oes.entity.Teacher;
import org.ssdut.japanese.oes.entity.User;
import org.ssdut.japanese.oes.exception.UserNotFoundException;
/*
 * @author yijun
 * 管理员模块数据库操作接口
 * */
public interface AdminDao {

	public Admin getAdminByName(String name);
	
	public void saveUserInBatch(List<User> users);//批量添加学生
	
	public void deleteUser(String id);
	
	public void saveTeacherInBatch(List<Teacher> teachers);//批量添加老师
	
	public void deleteTeacher(String id);
	
	
	//分页查询
	public Page pageQuery(String hql, int pageNo, int pageSize,
			Object... values);
	
	
	//更新学生信息
	public void updateUser(User user) throws UserNotFoundException;
	
	//更新教师信息
	public void updateTeacher(Teacher teacher) throws UserNotFoundException;
	

}
