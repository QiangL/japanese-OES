package org.ssdut.japanese.oes.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.ssdut.japanese.oes.entity.ExaminationStructure;
import org.ssdut.japanese.oes.entity.Record;
import org.ssdut.japanese.oes.entity.User;
import org.ssdut.japanese.oes.exception.UserNotFoundException;

public interface UserService {

	public boolean login(User user) throws UserNotFoundException;
	
	//从excel导入用户
	public boolean importUsers(List<User> users);
	
	public boolean uploadRecord(MultipartFile file,Record record,String resourcePath);
	
	public User getUserById(String id);
	
	//获取考试试卷信息
	public ExaminationStructure getES ();
	
}
