package org.ssdut.japanese.oes.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.ssdut.japanese.oes.dao.impl.Page;
import org.ssdut.japanese.oes.entity.Admin;
import org.ssdut.japanese.oes.entity.Teacher;
import org.ssdut.japanese.oes.entity.User;
import org.ssdut.japanese.oes.exception.UserNotFoundException;
import org.ssdut.japanese.oes.service.AdminService;
import org.ssdut.japanese.oes.util.ExcelUtil;

/*
 * 管理员功能controller模块
 * */
@Controller
public class AdminController extends BaseController{

	@Autowired
	private AdminService adminService;
	
	/* @function 管理员登陆
	 * @param name:管理员账号   password:密码
	 * 
	 * 管理员账户密码硬编码在web.xml文件中
	 * */
	@RequestMapping("admin/login")
	public String login(HttpServletRequest request,String name , String password
			,ModelMap modelMap){
		ServletContext ctx = getSession(request).getServletContext();
		String admin = ctx.getInitParameter("admin");
		String pass = ctx.getInitParameter("password");
		if(admin.equals(name) && pass.equals(password)){
			Admin a=new Admin();
			a.setName(admin);
			setSessionAdmin(request,a);
			return "import";
		}
		else{
			modelMap.addAttribute("errorMsg", "用户名或密码错误");
			return "redirect:/login.html";
		}
	}
	
	@RequestMapping("admin/getImport")
	public ModelAndView getImport(){
		return new ModelAndView("import");
	}
	
	
	/*
	 * @function 用xls文件批量导入信息
	 * @param type: 有两个取值  1.type="student"表示导入学生    2.type="teacher"表示导入教师
	 * @param file: 上传的xls文件
	 * */
	@RequestMapping("admin/import")
	public ModelAndView importTeachers(HttpServletRequest request,
			@RequestParam(required=true)String type,
			@RequestParam(required=true) MultipartFile file){
	
		List<?> info = null;
		if( type.trim().length() == 0 )
			return null;//失败
		String path = getResourcePath(request) + "/temp/";
		File dirs = new File(path);
		if(!dirs.exists())
			dirs.mkdirs();
		File tempFile = new File(path+file.getOriginalFilename());
		
		try {
			tempFile.createNewFile();
			file.transferTo(tempFile);
			if(type.equalsIgnoreCase("student")){
				info = ExcelUtil.ParseFromExcel(new FileInputStream(tempFile), User.class);
				adminService.saveUserInBatch((List<User>) info);
			}else{ 
				info = ExcelUtil.ParseFromExcel(new FileInputStream(tempFile), Teacher.class);
				adminService.saveTeacherInBatch((List<Teacher>) info);
			}
			tempFile.delete();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/*
	 * @function 分页查看学生信息
	 * @param page: 页号
	 * */
	@RequestMapping("admin/students")
	public ModelAndView showStudents(@RequestParam(required=false) Integer page){
		if(page == null)
			page=Integer.valueOf(1);
		Page pagedUser = adminService.getPagedUser(page);
		Integer pageCount = (int) pagedUser.getTotalPageCount();
		List<User> list = pagedUser.getResult();
		ModelAndView mav = new ModelAndView("manage");
		mav.addObject("students",list);
		mav.addObject("pageCount", pageCount);
		mav.addObject("currentPage", page.toString());
		return mav;
	}
	
	
	/*
	 * @function 分页查看教师信息
	 * @param page：页号
	 * */
	@RequestMapping("admin/teachers")
	public ModelAndView showTeachers(@RequestParam(required=false) Integer page){
		if(page == null)
			page=Integer.valueOf(1);
		Page pagedTeacher = adminService.getPagedTeacher(page);
		ModelAndView mav = new ModelAndView("manageTea");
		mav.addObject("teachers", pagedTeacher.getResult());
		mav.addObject("pageCount", pagedTeacher.getTotalPageCount());
		mav.addObject("currentPage", pagedTeacher.getCurrentPageNo());
		return mav;
	}
	
	/*
	 * @function 修改学生信息
	 * 
	 * */
	@RequestMapping("admin/update/student")
	@ResponseBody
	public Object updateUser(User user){
		if(user == null)
			return "fail";
		try {
			adminService.updateUser(user);
		} catch (UserNotFoundException e) {
			return "学生号不正确";
		}
		return "success";
	}
	
	/*
	 * @function 修改教师信息
	 * 
	 * */
	@RequestMapping("admin/update/teacher")
	@ResponseBody
	public Object updateTeacher(Teacher teacher){
		if(teacher == null)
			return "fail";
		try {
			adminService.updateTeacher(teacher);
		} catch (UserNotFoundException e) {
			return "教工号不正确";
		};
		return "success";
	}
	
	
	/*
	 * @function 删除教师
	 * @param id 教师职工号-主键
	 * */
	@RequestMapping("admin/delete/teacher")
	@ResponseBody
	public Object deleteTeacher(@RequestParam(required=true)String id){
		if(id.trim().length() == 0)
			return "fail";
		adminService.deleteTeacher(id);
		return "success";
	}
	
	
	/*
	 * @function 删除学生
	 * @param id 学号号-主键
	 * */
	@RequestMapping("admin/delete/student")
	@ResponseBody
	public Object deleteUser(@RequestParam(required=true)String id){
		if(id.trim().length() == 0)
			return "fail";
		adminService.deleteUser(id);
		return "success";
	}
}




