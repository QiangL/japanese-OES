package org.ssdut.japanese.oes.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;



import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ssdut.japanese.oes.cons.*;
import org.apache.commons.lang3.StringUtils;
import org.ssdut.japanese.oes.entity.Admin;
import org.ssdut.japanese.oes.entity.Teacher;
import org.ssdut.japanese.oes.entity.User;


public class ForumFilter implements Filter {
	private static final String FILTERED_REQUEST = "@@session_context_filtered_request";
	// ① 不需要登录即可访问的URI资源
	private static final String[] INHERENT_ESCAPE_URIS = { "index.html","about.html","login.html",
		"register.html","teacher/login","user/login","admin.html","admin/login"
			 };
	//Teacher 才可以访问的资源，这里写的笨了
	private static final String[] Teacher_URIS={"teacher/paperGenerate","teacher/uploadFile","teacher/upload","success",
		"teacher/questions","teacher/generatePaper","teacher/papers","teacher/choosePaper","teacher/choose",
		"teacher/grade","teacher/students","teacher/getRecords","teacher/getRecord","teacher/score"};
	//User 才可以访问的资源
	private static final String[] User_URIS={"begin","user/record","user/examBegin","user/getQuestion"};
	
	private static final String[] Adamin_URLS={"admin/import","admin/students","admin/teachers","admin/update/student",
		"admin/update/teacher","admin/delete/teacher","admin/delete/student","admin/getImport"};
	// ② 执行过滤
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		// ②-1 保证该过滤器在一次请求中只被调用一次
		if (request != null && request.getAttribute(FILTERED_REQUEST) != null) {
			chain.doFilter(request, response);
		} 
		//拦截html文件和使用Springmvc映射的路径，而不拦截其他文件（js、css等）
		else if(httpRequest.getRequestURL().indexOf(".html")!=-1||httpRequest.getRequestURL().indexOf(".")==-1){
			// ②-2 设置过滤标识，防止一次请求多次过滤
			request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
			User userContext = getSessionUser(httpRequest);
			Teacher teacherContext=getSessionTeacher(httpRequest);
			Admin adminContext=getSessionAdmin(httpRequest);
			// ②-3 用户未登录, 且当前URI资源需要登录才能访问 //以前的注释了，不用看
			String errorMsg="请登录后操作";
			String contextPath=httpRequest.getContextPath()+"/login.html?errorMsg=";
			//没登陆
			if(userContext==null && teacherContext==null&&adminContext==null){
				if(!isURILogin(httpRequest.getRequestURI(), httpRequest,"All")){
					httpResponse.sendRedirect(contextPath+URLEncoder.encode(errorMsg, "utf-8"));
					return;
				}
			}
			//登陆不同角色
			else if(userContext!=null && teacherContext!=null||userContext!=null&&adminContext!=null||
					adminContext!=null&&teacherContext!=null){
				httpResponse.sendRedirect(contextPath+URLEncoder.encode("同时登录多个账号，请重新登录","utf-8"));
				setSessionTeacher(httpRequest, null);
				setSessionUser(httpRequest, null);
				setSessionAdmin(httpRequest, null);
				return;
			}
			else if(userContext!=null){
				if(!isURILogin(httpRequest.getRequestURI(), httpRequest,"User")){
					httpResponse.sendRedirect(contextPath+URLEncoder.encode(errorMsg, "utf-8"));
					return;
				}
			}else if(teacherContext!=null){
				if(!isURILogin(httpRequest.getRequestURI(), httpRequest, "Teacher")){
					httpResponse.sendRedirect(contextPath+URLEncoder.encode(errorMsg, "utf-8"));
					return;
				}
			}else if(adminContext!=null){
				if(!isURILogin(httpRequest.getRequestURI(), httpRequest, "Admin")){
					httpResponse.sendRedirect(contextPath+URLEncoder.encode(errorMsg, "utf-8"));
					return;
				}
			}
			chain.doFilter(request, response);
			/*
			
			if (userContext == null&& !isURILogin(httpRequest.getRequestURI(), httpRequest,"User")
					&&teacherContext==null&&!isURILogin(httpRequest, httpRequest, httpRequest)) {
				//System.out.println(userContext);
				String toUrl = httpRequest.getRequestURL().toString();
				if (!StringUtils.isEmpty(httpRequest.getQueryString())) {
					toUrl += "?" + httpRequest.getQueryString();
				}
				// ②-4将用户的请求URL保存在session中，用于登录成功之后，跳到目标URL
				httpRequest.getSession().setAttribute(CommonConstant.LOGIN_TO_URL, toUrl);

				// ②-5转发到登录页面
				request.getRequestDispatcher("/login.html").forward(request,
						response);
				return;
			}
			chain.doFilter(request, response);
			*/
		}else{
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
   /**
    * 当前URI资源是否可以访问访问
    * @param requestURI
    * @param request
    * @param type 用户类型 Teacher 还是 User 还是 All 还是admin
    * @return true 表示可以访问
    */
	private boolean isURILogin(String requestURI, HttpServletRequest request,String type) {
		if (request.getContextPath().equalsIgnoreCase(requestURI)
				|| (request.getContextPath() + "/").equalsIgnoreCase(requestURI))
			return true;
		if(type.equals("Teacher")){
			for (String uri : Teacher_URIS) {
				if (requestURI != null && requestURI.indexOf(uri) >= 0) {
					return true;
				}
			}
		}else if(type.equals("User")){
			for (String uri : User_URIS) {
				if (requestURI != null && requestURI.indexOf(uri) >= 0) {
					return true;
				}
			}
		}else if(type.equals("Admin")){
			for (String uri : Adamin_URLS) {
				if (requestURI != null && requestURI.indexOf(uri) >= 0) {
					return true;
				}
			}
		}
		for (String uri : INHERENT_ESCAPE_URIS) {
			if (requestURI != null && requestURI.indexOf(uri) >= 0) {
				return true;
			}
		}
		return false;
	}

	protected User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
	}
	protected Teacher getSessionTeacher(HttpServletRequest request){
		return (Teacher) request.getSession().getAttribute(CommonConstant.TEACHER_CONTEXT);
	}
	protected void setSessionUser(HttpServletRequest request,User user) {
		getSession(request).setAttribute(CommonConstant.USER_CONTEXT, user);
	}
	protected void setSessionTeacher(HttpServletRequest request,Teacher user) {
		getSession(request).setAttribute(CommonConstant.TEACHER_CONTEXT, user);
	}
	protected HttpSession getSession(HttpServletRequest request) {
		return request.getSession();		
	}
	protected void setSessionAdmin(HttpServletRequest request,Admin user) {
		getSession(request).setAttribute(CommonConstant.ADMIN_CONTEXT, user);
	}
	protected Admin getSessionAdmin(HttpServletRequest request){
		return (Admin) request.getSession().getAttribute(CommonConstant.ADMIN_CONTEXT);
	}
	@Override
	public void destroy() {

	}
}
