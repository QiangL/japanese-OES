package org.ssdut.japanese.oes.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.ssdut.japanese.oes.cons.CommonConstant;
import org.ssdut.japanese.oes.cons.QuestionType;
import org.ssdut.japanese.oes.entity.GeneralQuestion;
import org.ssdut.japanese.oes.entity.ImgQuestion;
import org.ssdut.japanese.oes.entity.Question;

/*
 * 解压缩工具类
 * @param zipfile:压缩文件路径     destDir:解压目的文件夹路径  type:试题类型
 * 仅适用于试题压缩包的解压 每个文件的解压命名规则是 日期+序号+后缀
 * */
public class decompression {

	public static List<Question> unZip(String zipfile, String destDir,QuestionType type) {
		 
	       destDir = destDir.endsWith( "\\" ) ? destDir : destDir + "\\" ;
	       byte b[] = new byte [1024];
	       int length;
	       int No = 0;
	       List<Question> queInfo = new ArrayList<Question>();
	       ZipFile zipFile = null;
	       try {
	           zipFile = new ZipFile( new File(zipfile),"GBK");
	           Enumeration enumeration = zipFile.getEntries();
	           ZipEntry zipEntry = null ;
	 
	           while (enumeration.hasMoreElements()) {
	              zipEntry = (ZipEntry) enumeration.nextElement();
	             
	              String loadFileName = destDir + zipEntry.getName();
	              if (zipEntry.isDirectory()) {	
	            	  
	        	      File loadFile = new File(loadFileName);
	                  loadFile.mkdirs();
	              } else {
	            	  //保证压缩文件中的目录结构  并且根据命名规则生成解压文件的文件名
	            	  String time = dateUtil.getCurrentTime();
	            	  String fileName = time + "-" + (No++) + 
	            			  zipEntry.getName().substring(zipEntry.getName().lastIndexOf("."));
	            	  
	            	  //路径名加上文件名 
	            	  loadFileName = loadFileName.substring(0,loadFileName.lastIndexOf("/")+1) + fileName;
	        	      File loadFile = new File(loadFileName);
	                  if (!loadFile.getParentFile().exists())
	                     loadFile.getParentFile().mkdirs();
	                  OutputStream outputStream = new FileOutputStream(loadFile);
	                  InputStream inputStream = zipFile.getInputStream(zipEntry);
	                  
	                  while ((length = inputStream.read(b)) > 0)
	                     outputStream.write(b, 0, length);
	                  
	                  outputStream.flush();
	                  outputStream.close();
	                  inputStream.close();
	                  
	                  //将试题信息保存在返回值中
	                  String url = null;
	                  String title = zipfile.substring(
	  							zipfile.lastIndexOf("/")+1,zipfile.lastIndexOf("."));
	                  switch (type) {
	  				case GENERAL:
	  					url = CommonConstant.QUESTION_URL + "generalQuestion/";
	  					GeneralQuestion question = new GeneralQuestion();
	  					question.setName(fileName);
	  					question.setType(type.toString());
	  					question.setUrl(url+fileName);
	  					question.setDescription(zipEntry.getName());//将文件原名保存在试题描述中
	  					question.setUpDate(time);
	  					question.setTitle(title);
	  					queInfo.add(question);
	  					break;
	  				case IMG:
	  					url = CommonConstant.QUESTION_URL + "imgQuestion/";
	  					ImgQuestion imgQuestion = new ImgQuestion();
	  					imgQuestion.setName(fileName);;
	  					imgQuestion.setType(type.toString());
	  					imgQuestion.setUrl(url);
	  					imgQuestion.setDescription(zipEntry.getName());
	  					imgQuestion.setUpDate(time);
	  					imgQuestion.setTitle(title);
	  					queInfo.add(imgQuestion);
	  				}
	              }
	           }
	           System. out .println( " 文件解压成功 " );
	       } catch (IOException e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	       }
	       try {zipFile.close();} catch (IOException e1){ e1.printStackTrace();}
	 return queInfo;
	    }

	public static void main(String[] args){
		decompression.unZip("test.zip", "f:/test",QuestionType.GENERAL);
	}
}
