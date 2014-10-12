package oes.dao.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ssdut.japanese.oes.dao.AdminDao;

public class AdminDaoTest {

	static AdminDao adminDao;
	static String path ;
	static ApplicationContext ctx ;
	
	@BeforeClass
	public static void Init(){

		path = "org/ssdut/japanese/profile/*.xml";
		ctx =new  ClassPathXmlApplicationContext(path);
		adminDao = (AdminDao) ctx.getBean("AdminDao");
	}
	
	@Test
	public void deleteUserTest(){
		adminDao.deleteUser("201293222");
	}
}
