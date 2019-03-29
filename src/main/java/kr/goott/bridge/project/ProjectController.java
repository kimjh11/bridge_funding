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
	@Autowired//servlet-context에 있는 sqlSession객체를 사용할 수 있음
	SqlSession sqlSession;
	
	@RequestMapping("/inputProject")
	public ModelAndView list() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("project/inputProject");
		
		return mav;
	}
	
}
