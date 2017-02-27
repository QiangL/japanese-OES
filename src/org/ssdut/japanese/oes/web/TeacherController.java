package org.ssdut.japanese.oes.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.ssdut.japanese.oes.cons.CommonConstant;
import org.ssdut.japanese.oes.cons.QuestionType;
import org.ssdut.japanese.oes.dao.impl.Page;
import org.ssdut.japanese.oes.entity.Bean;
import org.ssdut.japanese.oes.entity.Record;
import org.ssdut.japanese.oes.entity.Teacher;
import org.ssdut.japanese.oes.entity.User;
import org.ssdut.japanese.oes.exception.NameReuseException;
import org.ssdut.japanese.oes.exception.UserNotFoundException;
import org.ssdut.japanese.oes.service.TeacherService;
import org.ssdut.japanese.oes.util.LogRecord;

import com.google.gson.reflect.TypeToken;

@Controller
public class TeacherController extends BaseController{

	@Autowired
	TeacherService teacherService;
	@RequestMapping(value="teacher/login",method=RequestMethod.POST)
	public String login(HttpServletRequest request, 
			String userId,String password
			,ModelMap modelMap){
		Teacher t=new Teacher();
		t.setTeacherId(userId);
		t.setPassword(password);
		try {
			//密码验证
			if(teacherService.login(t)){
				//setSessionUser(request, t);
				String toUrl = (String)request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
			}
			else{
				modelMap.addAttribute("errorMsg", "密码错误，请再次确认");
				return "redirect:/login.html";
			}
		}catch (UserNotFoundException e) {
			//用户不存在
			modelMap.addAttribute("errorMsg", e.getMessage());
			return "redirect:/login.html";
		}
		return "redirect:/paperGenerate.html";
	}
	/*
	@RequestMapping(value="teacher/login",method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, 
			String userId,String password){
		ModelAndView mav = new ModelAndView();
		Teacher t = new Teacher();
		t.setTeacherId(userId);
		t.setPassword(password);
		try {
			//密码验证
			if(teacherService.login(t)){
				//setSessionUser(request, t);
				String toUrl = (String)request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
				mav.setViewName("redirect:/paperGenerate.html");
			}
			else{
				mav.addObject("errorMsg", "密码错误，请再次确认");
				mav.setViewName("redirect:/login.html");
				return mav;
			}
		} catch (UserNotFoundException e) {
			//用户不存在
			mav.addObject("errorMsg", e.getMessage());
			mav.setViewName("redirect:/login.html");
			return mav;
		}
		return mav;
	}
	*/
	/*
	 * @function 老师上传试题 暂时的解决方案是直接存放在resource文件夹下
	 * @param file：试题的压缩文件  type：试题类型
	 * 
	 * */
	@RequestMapping(value="teacher/upload",method=RequestMethod.POST)
	public ModelAndView upLoad(HttpServletRequest request ,
			@RequestParam(value="file",required=true) MultipartFile file,
			String type){
		if(type == null){
			ModelAndView mav =  new ModelAndView("redirect:/upload.html");
			mav.addObject("errorMsg", "请选择试题上传类型");
			return mav;
		}
		String temp = getResourcePath(request) + "/temp/";//临时文件夹
		String name = file.getOriginalFilename();
		String resourcePath = getResourcePath(request) + "/question/" + type + "Question";
		File dir = new File(temp );
		File tempFile = new File(temp + name);
		if(!dir.exists())
			dir.mkdirs();
		try {
			file.transferTo(tempFile);//转存到临时文件
		} catch (IOException e) {
			LogRecord.error("新建文件失败");
			e.printStackTrace();
		}
		
		File dirs = new File(resourcePath);
		if(!dirs.exists())
			dirs.mkdirs();
		//调用下层方法
		teacherService.uploadFile(temp + name, resourcePath, type);
		tempFile.delete();//删除临时文件
		//ModelAndView mav = new ModelAndView();
		return new ModelAndView("success");
	}
	
	
	/*
	 * @function 返回分页后的题型 页大小由CommonConstan类指定
	 * @param page 页号
	 * @param type 题目类型
	 * */
	@RequestMapping(value="teacher/questions",method=RequestMethod.POST)
	@ResponseBody
	public Object showQuestionG(@RequestParam(required=false)Integer page,
			String type){
		if(page == null)
			page = Integer.valueOf(1);
		Page pagedResult = null;
		switch (QuestionType.getType(type)) {
		case GENERAL:
			pagedResult = teacherService.getQuestion(QuestionType.GENERAL,page);
			break;
		case IMG:
			pagedResult = teacherService.getQuestion(QuestionType.IMG,page);
			break;
		default:
			break;
		}
		Type type2 = new TypeToken<Map<String,Object>>(){}.getType();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("questions", pagedResult.getResult());
		map.put("currentPage", pagedResult.getCurrentPageNo());
		map.put("totalPageCount", pagedResult.getTotalPageCount());
		return toJson(map, type2);
	}
	
	
	/*
	 * @function 组卷
	 * @param questions:json格式的字符串 可解析成List<String>类型  其中保存的是要组成试卷的题型id
	 * @param examName: 由教师指定的试卷名称 
	 * */
	@RequestMapping("teacher/generatePaper")
	@ResponseBody
	public Object generateTestPaper(HttpServletRequest request,
		@RequestParam(required=true) String questions,
		@RequestParam(required=true) String examName){
		Type type = new TypeToken<List<Bean>>() {}.getType();
		List<Bean> questionList = (List<Bean>) fromJson(questions, type);
		//试卷命名规则: 教师指定名字+组卷时间
		String path = getResourcePath(request) + "/exam/" + examName;
		try {
			teacherService.generateTestPaper(questionList, path);
		} catch (NameReuseException e) {
			e.printStackTrace();
			return "该试卷已存在";
		}
		return "上传成功";
	}
	
	/*
	 * @function 展示所有的考卷 分页
	 * @param pageNo 页号
	 * */
	@RequestMapping("teacher/papers")
	public ModelAndView papers(HttpServletRequest request, Integer pageNo){
		if(pageNo == null)
			pageNo = Integer.valueOf(1);
		Page page = teacherService.getPaper(pageNo);
		ModelAndView mav = new ModelAndView("choosePaper");
		mav.addObject("papers", page.getResult());
		mav.addObject("currentPageNo", page.getCurrentPageNo());
		mav.addObject("totalPageCount", page.getTotalPageCount());
		return mav;
	}
	
	/*
	 * @function 设置将要使用的考卷
	 * @param id 将要使用的考卷id
	 * */
	@RequestMapping("teacher/choose")
	@ResponseBody
	public Object choosePaper(String id){
		teacherService.setActivePaper(id);
		return "success";
	}
	
	
	/**
	 * @function 返回教师评卷页面
	 * @param teacherId 教师Id
	 * 
	 * */
	@RequestMapping("teacher/grade")
	public ModelAndView grade(String teacherId){
		ModelAndView mav = new ModelAndView("grade");
		List<String> classes = teacherService.getClassInfo();
		
		mav.addObject("classes", classes);
		return mav;
	}
	
	/**
	 * @function 返回指定班级所有学生信息
	 * @param classNumber 班级号
	 * 
	 * */
	@RequestMapping("teacher/students")
	@ResponseBody
	public Object getStudentInfo(String classNumber){
		List<User> result = teacherService.getStudentInfo(classNumber);
		return result;
	}
	
	/**
	 * @function 返回指定学生的录音信息
	 * @param userId 学生学号
	 * */
	@RequestMapping("teacher/getRecords")
	@ResponseBody
	public Object getStudentRecord(String userId){
		Type type =new  TypeToken<List<Record>>(){}.getType();
		List<Record> result = teacherService.getRecords(userId);
		return toJson(result, type);
	}
	
	/**
	 * @function 返回某个指定录音的访问url
	 * */
	@RequestMapping("teacher/getRecord")
	@ResponseBody
	public Object getRecord(String rid){
		Record record = teacherService.getRecord(rid);
		return record.getUrl();
	}
	
	/**
	 * @function 为某个录音文件打分
	 * @param recordId 录音文件id
	 * @param score 分数
	 * */
	@RequestMapping("teacher/score")
	@ResponseBody
	public Object score(String recordId,int score){
		String result = (teacherService.score(recordId, score))?"success":"failed";
		//Type type = new TypeToken<String>(){}.getType();
		//return toJson(result,type);
		return result;
	}
}
