package org.ssdut.japanese.oes.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.ssdut.japanese.oes.entity.User;

public class ExcelUtil {

	
	static Map<String, String> columnNaming = new HashMap<String, String>();
	
	static{
		//学生导入表 列名和实体类属性名的映射
		columnNaming.put("姓名", "userName");
		columnNaming.put("学号", "userId");
		columnNaming.put("班级", "classNumber");
		
		//教师导入表 
		columnNaming.put("教工号", "teacherId");
		columnNaming.put("教师名", "teacherName");
	}
	
	/*
	 * 
	 * @function 读取excel文件返回对应的实体类列表
	 * 
	 * */
	public static <T> List<T> ParseFromExcel(FileInputStream in , Class<T> clazz){
		List<T> entities = new ArrayList<T>();
		
		jxl.Workbook readwb = null;
		
		try {
			readwb = Workbook.getWorkbook(in);
			in.close();
		} catch (BiffException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Sheet sheet = readwb.getSheet(0);
		
		int columnNum = sheet.getColumns();
		
		int rowNum = sheet.getRows();
	
		//第一行
		Cell[] row1 = sheet.getRow(0);
		
		Field[] fields = clazz.getDeclaredFields();
		//将所有的列以实体类的属性名为键存入map
		Map<String, Cell[]>	cells = new HashMap<String, Cell[]>();	
		for (int i = 0; i < columnNum; i++) {
			String fieldName = columnNaming.get(row1[i].getContents());
			cells.put(fieldName, sheet.getColumn(i));
		}
		
		for(int i = 1; i < rowNum; i++){
			try {
				T entity = clazz.newInstance();
				for (int j = 0; j < fields.length; j++) {
					Field field = fields[j];
					String fieldName = field.getName();
					Cell[] column = cells.get(fieldName);
					if(column == null)//表中不存在的属性名  执行下次循环
						continue;
					String content = column[i].getContents();
					field.setAccessible(true);
					field.set(entity, content);
					field.setAccessible(false);
				}
				entities.add(entity);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return entities;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("学生导入.xls");
		List<User> users = ExcelUtil.ParseFromExcel(new FileInputStream(file), User.class);
		
		System.out.println(users);
	}
}
