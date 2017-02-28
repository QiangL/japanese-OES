package org.ssdut.japanese.oes.web;



import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ssdut.japanese.oes.cons.CommonConstant;
import org.ssdut.japanese.oes.entity.Teacher;
import org.ssdut.japanese.oes.entity.User;

import com.google.gson.Gson;


/**
 * 
 * @see
 * @since
 */
public class BaseController {
	protected static final String ERROR_MSG_KEY = "errorMsg";
	
	private static final Gson gson = new Gson();

	/**
	 * 获取Session
	 * 
	 * @param request
	 * @return
	 */
	protected HttpSession getSession(HttpServletRequest request) {
		return request.getSession();		
	}
   
	
	/*
	 * 获取资源路径
	 * 
	 * @param request
	 * @return 真实的资源保存路径
	 * */
	protected String getResourcePath(HttpServletRequest request){
		return getSession(request).getServletContext().getRealPath("/resource");
	}


	/*
	 * 将用户对象保存到session中
	 * 
	 * @param request user
	 * */
	protected void setSessionUser(HttpServletRequest request,User user) {
		getSession(request).setAttribute(CommonConstant.USER_CONTEXT, user);
	}
	
	/*
	 * 从session中获取User对象
	 * 
	 * @return User
	 * */
	protected User getSessionUser(HttpServletRequest request){
		return (User) getSession(request).getAttribute(CommonConstant.USER_CONTEXT);
	}
	
	protected void setSessionTeacher(HttpServletRequest request,Teacher user) {
		getSession(request).setAttribute(CommonConstant.TEACHER_CONTEXT, user);
	}
	protected Teacher getSessionTeacher(HttpServletRequest request){
		return (Teacher) getSession(request).getAttribute(CommonConstant.TEACHER_CONTEXT);
	}
	
	
	public String toJson(Object object){
		return gson.toJson(object);
	}
	
	public String toJson(Object object,Type type){
		String string = null;
		string = new String(gson.toJson(object).getBytes());
		return string;
	}
	
	public Object fromJson(String json,Type type){
		return gson.fromJson(json, type);
	}
}
