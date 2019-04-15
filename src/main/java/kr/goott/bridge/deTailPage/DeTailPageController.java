package kr.goott.bridge.deTailPage;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ListFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import kr.goott.bridge.project.ItemVO;
import kr.goott.bridge.project.ProjectDAOInterface;
import kr.goott.bridge.project.ProjectVO;

@Controller
public class DeTailPageController {
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping("/deTailPage")
	public ModelAndView deTailPage(ProjectVO vo, HttpServletRequest request) {
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		HttpSession sion = request.getSession();
		sion.setAttribute("proCode", vo.getProCode());
		vo = dao.selectRecord(vo.getCateCode(), vo.getProCode());
		List<ItemVO> list = dao.itemList(vo.getCateCode(), vo.getProCode());
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("cnt", list.size());
		mav.addObject("list", list);
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
	public ModelAndView deTailReply(ProjectVO vo, HttpServletRequest request) {
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		List<ReplyVO> list = dao.replyOpen(vo.getCateCode(), vo.getProCode());
		vo = dao.selectDate(vo.getCateCode(), vo.getProCode());
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		mav.addObject("count",list.size());
		mav.addObject("list", list);
		mav.addObject("proOpen",session.getAttribute("proOpen"));
		mav.addObject("proEnd",session.getAttribute("proEnd"));
		mav.addObject("userMail",session.getAttribute("userMail"));
		mav.addObject("userName",session.getAttribute("userName"));
		mav.addObject("userImg",session.getAttribute("userImg"));
		mav.addObject("logSataus", session.getAttribute("logStatus"));
		mav.addObject("ProCode",vo.getProCode());
		mav.addObject("CateCode",vo.getCateCode());
		mav.addObject("proOpen",vo.getProOpen());
		mav.addObject("proEnd",vo.getProEnd());
		
		
		mav.setViewName("ajax/deTailReply");
		return mav;
	}
	@RequestMapping("/deTailAtm")
	public ModelAndView deTailAtm(HttpServletRequest req, ProjectVO vo) {
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		HttpSession sion = req.getSession();
		
		vo = dao.selectStatus((String)sion.getAttribute("proCode"));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",vo);
		mav.setViewName("ajax/deTailAtm");
		return mav;
	}
	@RequestMapping("/deTailSpter")
	public ModelAndView deTailSpter(HttpServletRequest req, ProjectVO vo) {
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		List<SptVO> list = dao.selectSpt(vo.getProCode());
		ModelAndView mav = new ModelAndView();
		System.out.println(list);
		mav.addObject("list" , list);
		mav.addObject("cnt",list.size());
		mav.setViewName("ajax/deTailSpter");
		
		return mav;
	}
	@RequestMapping(value="/replyWrite", method= RequestMethod.POST)
	@ResponseBody
	public ModelAndView replyWrite(HttpServletRequest request, ReplyVO rvo) {
		rvo.setUserIp(request.getRemoteAddr());
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		int cnt = dao.replyWrite(rvo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("proCode", rvo.getProCode());
		mav.addObject("cateCode", rvo.getCateCode());
		
		mav.addObject("cnt", cnt);
		mav.setViewName("ajax/replyWrite");
		
		return mav;
	}
}
