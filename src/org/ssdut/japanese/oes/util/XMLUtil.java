package org.ssdut.japanese.oes.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.ssdut.japanese.oes.cons.QuestionType;
import org.ssdut.japanese.oes.entity.GeneralQuestion;
import org.ssdut.japanese.oes.entity.ImgQuestion;
import org.ssdut.japanese.oes.entity.Question;

/*
 * xml格式文件处理辅助类
 * */
public class XMLUtil {

	public static void main(String[] args){
		List<Question> list = new ArrayList<Question>();
		GeneralQuestion g = new GeneralQuestion();
		ImgQuestion img = new ImgQuestion();
		g.setId("1");
		g.setName("测试");
		g.setType("general");
		g.setUrl("url");
		g.setDescription("测试描述");
		list.add(g);
		img.setId("2");
		img.setName("测试");
		img.setType("img");
		img.setUrl("url");
		img.setResource("测试资源");
		img.setDescription("测试描述");
		list.add(img);
		Document document = XMLUtil.generateDocument(list);
		File file = new File("test.xml");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XMLUtil.saveDocument(document, file);
	}
	
	
	
	public static Document generateDocument(List<Question> questions){
		Document document = DocumentHelper.createDocument();
		
		//root element
		Element root = document.addElement("questions");
		Element generalTypeElement = root.addElement("generals");
		Element imgTypeElement = root.addElement("imgs");
		
		
		//遍历试题链表 为每个试题在xml文件中添加一个节点
		for (Question question : questions) {
			//generalQeustion 
			String type = question.getType();
			if(type.equalsIgnoreCase(QuestionType.GENERAL.toString())){
				Element general = generalTypeElement.addElement("general");
				general.addAttribute("id", question.getId());//设置试题id
				general.addElement("name").addText(question.getName());//设置文件名
				general.addElement("url").addText(question.getUrl());//设置url
				general.addElement("description").addText(question.getDescription());
			}
			//imgQuestion
			else if(type.equalsIgnoreCase(QuestionType.IMG.toString())){
				Element general = imgTypeElement.addElement("img");
				general.addAttribute("id", question.getId());//设置试题id
				general.addElement("name").addText(question.getName());//设置文件名
				general.addElement("url").addText(question.getUrl());//设置url
				general.addElement("description").addText(question.getDescription());
				general.addElement("resource");//设置指向的资源路径
			}
		}
		return document;
	}
	
	public static void saveDocument(Document document , File outputXML){
		//美化格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		try {
			XMLWriter outputWriter = new XMLWriter(new FileWriter(outputXML), 
					format);
			outputWriter.write(document);
			outputWriter.close();
			System.out.println("创建xml文件成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
