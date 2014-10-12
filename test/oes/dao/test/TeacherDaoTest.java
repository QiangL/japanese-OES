package oes.dao.test;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.ssdut.japanese.oes.dao.impl.TeacherDaoImpl;
import org.ssdut.japanese.oes.entity.GeneralQuestion;
import org.ssdut.japanese.oes.entity.ImgQuestion;
import org.ssdut.japanese.oes.entity.Question;
public class TeacherDaoTest {

	
	static TeacherDaoImpl teacherDao;
	static String path ;
	static ApplicationContext ctx ;
	
	@BeforeClass
	public static void Init(){

		path = "org/ssdut/japanese/profile/*.xml";
		ctx =new  ClassPathXmlApplicationContext(path);
		teacherDao = (TeacherDaoImpl) ctx.getBean("TeacherDao");
	}
	@Test
	public void saveQuestionTest(){
		List<Question> ques = new ArrayList<Question>();
		for(int i = 0 ; i < 10; i++){
			GeneralQuestion que = new GeneralQuestion();
			que.setName("test"+i);
			que.setType("general");
			que.setUrl("测试url");
			ques.add(que);
		}
		
		teacherDao.saveQuestion(ques);
	}
	
	@Test
	public void inheritanceTest(){
		ImgQuestion img = new ImgQuestion();
		img.setName("img");
		img.setResource("资源");
		img.setType("img");
		img.setUrl("URL");
		img.setDescription("deis");
		teacherDao.saveQuestion(img);
	}
	
	@Test
	public void getTest(){
		//System.out.println((GeneralQuestion)teacherDao.getQuestionById("8a9f79d44854bdd2014854bdd52a0001"));
		//System.out.println((ImgQuestion)teacherDao.getQuestionById("8a9f79d44854dd56014854dd58660001"));
	}

	@Test
	public void activeTest(){
		teacherDao.setActivePaper("402881fd48ee51490148ee6f56bd0002");
	}
}
