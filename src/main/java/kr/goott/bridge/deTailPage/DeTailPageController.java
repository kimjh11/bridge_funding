package kr.goott.bridge.deTailPage;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.goott.bridge.project.ProjectDAOInterface;
import kr.goott.bridge.project.ProjectVO;

@Controller
public class DeTailPageController {
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping("/deTailPage")
	public ModelAndView deTailPage(ProjectVO vo) {
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);	
		vo = dao.selectRecord(vo.getCateCode(), vo.getProCode());
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("deTail/deTailPage");
		
		return mav;
	}
	@RequestMapping("/deTailGuide")
	public ModelAndView deTailGuide(ProjectVO vo) {
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		String refund = dao.deTailAs(vo.getCateCode(), vo.getProCode());
		ModelAndView mav = new ModelAndView();
		mav.addObject("refund", refund);
		mav.setViewName("ajax/deTailGuide");
		return mav;
	}
	@RequestMapping("/deTailOpen")
	public ModelAndView deTailOpen(ProjectVO vo) {
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);	
		vo = dao.selectRecord(vo.getCateCode(), vo.getProCode());
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("ajax/deTailOpen");
		
		return mav;
	}
	@RequestMapping("/deTailReply")
	public String deTailReply() {
		return "ajax/deTailReply";
	}
	@RequestMapping("/deTailAtm")
	public String deTailAtm() {
		return "ajax/deTailAtm";
	}
	@RequestMapping("/deTailSpter")
	public String deTailSpter() {
		return "ajax/deTailSpter";
	}
}
