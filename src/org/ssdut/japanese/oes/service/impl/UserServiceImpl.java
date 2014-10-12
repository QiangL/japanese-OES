package org.ssdut.japanese.oes.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.ssdut.japanese.oes.dao.UserDao;
import org.ssdut.japanese.oes.entity.ExaminationStructure;
import org.ssdut.japanese.oes.entity.Record;
import org.ssdut.japanese.oes.entity.User;
import org.ssdut.japanese.oes.exception.UserNotFoundException;
import org.ssdut.japanese.oes.service.UserService;
import org.ssdut.japanese.oes.util.MD5;
import org.ssdut.japanese.oes.util.dateUtil;

@Service(value = "UserService")
public class UserServiceImpl  implements UserService{

	@Autowired
	UserDao userDao;
	
	
	public boolean login(User user) throws UserNotFoundException{
		User u = userDao.getUserByUserId(user.getUserId());
		if(u == null)
			throw new UserNotFoundException("用户不存在");
		try {
			String digest = MD5.md5(user.getPassword());
			//密码不匹配
			if (!MD5.isEqual(MD5.toBytes(digest), MD5.toBytes(u.getPassword()))) 
				return false;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean importUsers(List<User> users) {
		userDao.importUsers(users);
		return true;
	}

	@Override
	public boolean uploadRecord(MultipartFile file, Record record,String resourcePath) {
		File rec = new File (resourcePath);
		//保存录音
		try {
			file.transferTo(rec);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		userDao.saveRecord(record);
		return false;
	}

	@Override
	public User getUserById(String id) {
		return userDao.getUserByUserId(id);
	}

	@Override
	public ExaminationStructure getES() {
		return  userDao.getES();
	}

	
}
