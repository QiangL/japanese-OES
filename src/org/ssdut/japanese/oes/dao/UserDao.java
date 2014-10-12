package org.ssdut.japanese.oes.dao;

import java.util.List;

import org.ssdut.japanese.oes.entity.ExaminationStructure;
import org.ssdut.japanese.oes.entity.Record;
import org.ssdut.japanese.oes.entity.User;


public interface UserDao {

	//根据学号获取用户
	public User getUserByUserId(String userId);
	
	//从excel文件导入用户  文件中不包含密码 须设置初始密码
	public boolean importUsers(List<User> users);
	
	//保存自己的录音
	public boolean saveRecord(Record info);
	
	//获取当前已激活的试卷信息
	public ExaminationStructure getES();
}
