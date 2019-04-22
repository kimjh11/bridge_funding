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
		DeTailPageDAOInterface dao = sqlSession.getMapper(DeTailPageDAOInterface.class);
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
		DeTailPageDAOInterface dao = sqlSession.getMapper(DeTailPageDAOInterface.class);
		String refund = dao.deTailAs(vo.getCateCode(), vo.getProCode());
		ModelAndView mav = new ModelAndView();
		mav.addObject("refund", refund);
		mav.setViewName("ajax/deTailGuide");
		return mav;
	}
	@RequestMapping("/previewGuide")
	public ModelAndView deTailGuide2(ProjectVO vo) {
		DeTailPageDAOInterface dao = sqlSession.getMapper(DeTailPageDAOInterface.class);
		String refund = dao.deTailAs2(vo.getCateCode(), vo.getProCode());
		ModelAndView mav = new ModelAndView();
		mav.addObject("refund", refund);
		mav.setViewName("ajax/previewGuide");
		return mav;
	}
	
	@RequestMapping("/deTailOpen")
	public ModelAndView deTailOpen(ProjectVO vo) {
		DeTailPageDAOInterface dao = sqlSession.getMapper(DeTailPageDAOInterface.class);	
		vo = dao.selectRecord(vo.getCateCode(), vo.getProCode());
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("ajax/deTailOpen");
		
		return mav;
	}
	@RequestMapping("/previewOpen")
	public ModelAndView deTailOpen2(ProjectVO vo) {
		DeTailPageDAOInterface dao = sqlSession.getMapper(DeTailPageDAOInterface.class);	
		vo = dao.selectRecord2(vo.getCateCode(), vo.getProCode());
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("ajax/previewOpen");
		
		return mav;
	}
	@RequestMapping("/deTailReply")
	public ModelAndView deTailReply(ProjectVO vo, HttpServletRequest request) {
		DeTailPageDAOInterface dao = sqlSession.getMapper(DeTailPageDAOInterface.class);
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
		DeTailPageDAOInterface dao = sqlSession.getMapper(DeTailPageDAOInterface.class);
		HttpSession sion = req.getSession();
		vo = dao.selectStatus((String)sion.getAttribute("proCode"));
		String cnt = dao.payCount((String)sion.getAttribute("proCode"));
		String userMail = (String)sion.getAttribute("userMail");
		System.out.println("usermail = "+sion.getAttribute("proCode"));
		String chk = "";
		if(userMail != null && !(userMail.equals(""))) {
			System.out.println(userMail);
			chk = dao.likeSelect((String)sion.getAttribute("proCode"), (String)sion.getAttribute("userMail"));
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",vo);
		mav.addObject("cnt", cnt);
		mav.addObject("proCode",(String)sion.getAttribute("proCode"));
		mav.addObject("chk", chk);
		
		mav.setViewName("ajax/deTailAtm");
		return mav;
	}
	@RequestMapping("/deTailSpter")
	public ModelAndView deTailSpter(HttpServletRequest req, ProjectVO vo) {
		DeTailPageDAOInterface dao = sqlSession.getMapper(DeTailPageDAOInterface.class);
		List<SptVO> list = dao.selectSpt(vo.getProCode());
		ModelAndView mav = new ModelAndView();
		mav.addObject("list" , list);
		mav.addObject("cnt",list.size());
		mav.setViewName("ajax/deTailSpter");
		
		return mav;
	}
	@RequestMapping(value="/replyWrite", method= RequestMethod.POST)
	public ModelAndView replyWrite(HttpServletRequest request, ReplyVO rvo) {
		rvo.setUserIp(request.getRemoteAddr());
		DeTailPageDAOInterface dao = sqlSession.getMapper(DeTailPageDAOInterface.class);
		int cnt = dao.replyWrite(rvo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("proCode", rvo.getProCode());
		mav.addObject("cateCode", rvo.getCateCode());
		
		mav.addObject("cnt", cnt);
		mav.setViewName("ajax/replyWrite");
		
		return mav;
	}
	
	@RequestMapping("/likeUp")
	public ModelAndView likeUp(HttpServletRequest req, ProjectVO vo) {
		DeTailPageDAOInterface dao = sqlSession.getMapper(DeTailPageDAOInterface.class);
		HttpSession sion = req.getSession();
		String proCode = (String)sion.getAttribute("proCode");
		String userMail = (String)sion.getAttribute("userMail");
		dao.likeUp(proCode);
		System.out.println(sion.getAttribute("proCode"));
		System.out.println("logStatus = "+sion.getAttribute("userMail"));
		dao.likeInsert(proCode,userMail);
		
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("logStatus", sion.getAttribute("logStatus"));
		mav.setViewName("ajax/deTailAtm");
		
		return mav;
	}
	
	@RequestMapping("/likeDown")
	public String likeDown(HttpServletRequest req) {
		DeTailPageDAOInterface dao = sqlSession.getMapper(DeTailPageDAOInterface.class);
		HttpSession sion = req.getSession();
		dao.likeDown((String)sion.getAttribute("proCode"), (String)sion.getAttribute("userMail"));
		dao.likeDelete((String)sion.getAttribute("proCode") , (String)sion.getAttribute("userMail"));
		
		return "ajax/deTailAtm";
	}
	@RequestMapping("/payment")
	public String payment() {
		return "payment/payment";
	}

}
