package org.ssdut.japanese.oes.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.ssdut.japanese.oes.cons.CommonConstant;
import org.ssdut.japanese.oes.entity.ExaminationStructure;
import org.ssdut.japanese.oes.entity.Record;
import org.ssdut.japanese.oes.entity.User;
import org.ssdut.japanese.oes.exception.UserNotFoundException;
import org.ssdut.japanese.oes.service.UserService;
import org.ssdut.japanese.oes.util.dateUtil;

/*
 * @author yijun 
 * 
 * 学生功能controller模块
 * */
@Controller
public class UserController extends BaseController{

	@Autowired
	UserService userService;
	
	
	/*
	 * @function 登陆验证
	 * @param user 验证信息 spring自动填充
	 * @param type 登陆角色 有两个可选值 ："student"和 "teacher"
	 * */
	@RequestMapping(value = "user/login" , method=RequestMethod.POST)
	public String login(HttpServletRequest request,
			HttpServletResponse response,User user,String type
			,ModelMap modelMap){
		if(type == null){
			modelMap.addAttribute("errorMsg", "请选择登陆角色");
			return "error";
		}
		
		//重定向到教师验证
		if(type.equals("teacher")){
			return "forward:/teacher/login";
		}
		else if(type.equals("student")){
			try {
				//密码验证
				if(userService.login(user)){
					setSessionUser(request, user);
					user = userService.getUserById(user.getUserId());
					ExaminationStructure es = userService.getES();
					modelMap.addAttribute("user", user);
					modelMap.addAttribute("es", es);
					return  "begin";
				}
				else{
					modelMap.addAttribute("errorMsg", "密码错误，请再次确认");
					return "redirect:/login.html";
				}
			} catch (UserNotFoundException e) {
				//用户不存在
				modelMap.addAttribute("errorMsg", e.getMessage());
				return "redirect:/login.html";
			}
			
		}
		modelMap.addAttribute("errorMsg", "登陆身份无效");
		return "redirect:/login.html";
		
	}

	
	/**
	 * @function 学生上传录音
	 * @param file  上传的录音文件
	 * @param userId 用户id
	 * @param qId 题目id
	 * @param eId 试卷id
	 * 
	 * 学生录音文件命名规则：学号+上传时间
	 * */
	@RequestMapping("user/record")
	public ModelAndView uploadRecord(HttpServletRequest request,
			MultipartFile file,String userId,String qId){
		
		String dirPath = getResourcePath(request) + "/answer/" + userId;
		File dir = new File(dirPath);
		if(!dir.exists())
			dir.mkdirs();
		//学生的上传录音文件命名规则是： 题目id+当前时间   每个用户的录音答案保存在以自己的学号命名的文件夹下
		String fileName = qId +"-" +dateUtil.getCurrentTime() + ".wmv";
		Record rcd = new Record();
		rcd.setUrl(CommonConstant.RECORD_RUL +userId+"/"+ fileName);
		rcd.setUserId(userId);
		rcd.setQuestionId(qId);
		
		fileName = dirPath + "/" + fileName;
		userService.uploadRecord(file, rcd, fileName);

		return null;
	}
	
	@RequestMapping("user/examBegin")
	public ModelAndView ExamBegin(HttpServletRequest request, String examId){
		User user = userService.getUserById(getSessionUser(request).getUserId());
		ExaminationStructure es = userService.getES();
		ModelAndView mav = new ModelAndView("exam");
		mav.addObject("es", es);
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping("user/getQuestion")
	public void getQustion(String qId, HttpServletResponse response){
		
	}
}
