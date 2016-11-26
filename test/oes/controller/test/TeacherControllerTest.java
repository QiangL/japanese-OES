package oes.controller.test;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ssdut.japanese.oes.web.TeacherController;

public class TeacherControllerTest {
	static TeacherController teacherController;
	static String path ;
	static ApplicationContext ctx ;
	
	@BeforeClass
	public static void Init(){

		path = "org/ssdut/japanese/profile/*.xml";
		ctx =new  ClassPathXmlApplicationContext(path);
		teacherController = (TeacherController) ctx.getBean(TeacherController.class);
	}
	
	
}
