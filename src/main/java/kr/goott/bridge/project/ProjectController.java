package kr.goott.bridge.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjectController {
	@Autowired//servlet-context�� �ִ� sqlSession��ü�� ����� �� ����
	SqlSession sqlSession;
	
	@RequestMapping("/inputProject")
	public ModelAndView inputProjectList() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("project/inputProject");
		
		return mav;
	}
	@RequestMapping("/inputStory")
	public ModelAndView inputStoryList() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("project/inputStory");
		
		return mav;
	}
	@RequestMapping("/inputItem")
	public ModelAndView inputItemList() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("project/inputItem");
		return mav;
	}
}
