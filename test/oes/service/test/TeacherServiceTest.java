package oes.service.test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ssdut.japanese.oes.cons.QuestionType;
import org.ssdut.japanese.oes.entity.Record;
import org.ssdut.japanese.oes.entity.User;
import org.ssdut.japanese.oes.service.TeacherService;

public class TeacherServiceTest {

	static TeacherService teacherService;
	static String path ;
	static ApplicationContext ctx ;
	
	@BeforeClass
	public static void Init(){

		path = "org/ssdut/japanese/profile/*.xml";
		ctx =new  ClassPathXmlApplicationContext(path);
		teacherService = (TeacherService) ctx.getBean("TeacherService");
	}
	
	@Test
	public void uploadTest(){
		String file = "f:/test.zip";
		String dest = "f:/test";
		teacherService.uploadFile(file, dest, "general");
	}
	
	@Test
	public void getTest(){
	
		List<User> result = teacherService.getStudentInfo("软日1208");
		System.out.println(result);
	}
	
	@Test
	public void getRecordsTest(){
		List<Record> resList = teacherService.getRecords("201293222");
		System.out.println(resList);
	}
}
