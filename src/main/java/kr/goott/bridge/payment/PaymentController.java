package kr.goott.bridge.payment;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.goott.bridge.member.MemberVO;
import kr.goott.bridge.project.ItemVO;
import kr.goott.bridge.project.ProjectVO;

@Controller
public class PaymentController {
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping("/payment")
	public ModelAndView payment(HttpServletRequest req, ItemVO vo, ProjectVO pvo) {
		HttpSession sion = req.getSession();
		sion.setAttribute("proCode", vo.getProCode());
		sion.setAttribute("cateCode", pvo.getCateCode());
		System.out.println("first = "+pvo.getCateCode());
		
		PaymentDAOInterface dao = sqlSession.getMapper(PaymentDAOInterface.class);
		List<ItemVO> list = dao.selectItem(vo.getProCode());
		String proEnd = dao.endDay(vo.getProCode());
		ModelAndView mav = new ModelAndView();
		mav.addObject("list",list);
		mav.addObject("proEnd", proEnd);
		mav.setViewName("payment/payment");
		return mav;
	}

	
	@RequestMapping("/payment1")
	public ModelAndView payment1(HttpServletRequest req, ItemVO vo) {
		HttpSession sion = req.getSession();
		PaymentDAOInterface dao = sqlSession.getMapper(PaymentDAOInterface.class);
		List<ItemVO> list = dao.selectItem((String)sion.getAttribute("proCode"));
		String proEnd = dao.endDay((String)sion.getAttribute("proCode"));
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("proEnd", proEnd);
		mav.setViewName("ajax/payment1");
		return mav;
	}
	@RequestMapping("/payment2")
	public ModelAndView payment2(ItemVO vo, HttpServletRequest req) {
		System.out.println("Rank = "+req.getParameter("selectRank"));
		System.out.println("itemNum = "+req.getParameter("itemNum"));
		System.out.println("select = "+req.getParameter("itemOption"));
		System.out.println("Cnt = "+req.getParameter("selectCount"));
		System.out.println("Name = "+req.getParameter("itemName"));
		System.out.println("Content = "+req.getParameter("itemContent"));
		System.out.println("Price = "+req.getParameter("itemPrice"));
		HttpSession sion = req.getSession();
		System.out.println("page2 = "+sion.getAttribute("cateCode"));
		int itemPrice = 0;
		try {
			itemPrice = Integer.valueOf((String)req.getParameter("itemPrice"));			
			vo.setItemPrice(itemPrice);
			vo.setItemOption(req.getParameter("itemOption"));
			vo.setSelectCount(req.getParameter("selectCount"));
			vo.setSelectRank(req.getParameter("selectRank"));
			vo.setItemName(req.getParameter("itemName"));
			vo.setItemContent(req.getParameter("itemContent"));
			vo.setProCode((String)sion.getAttribute("proCode"));
			
		}catch(NumberFormatException e){
		}catch(Exception e) {}
		
		sion.setAttribute("vo", vo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("itemPrice", itemPrice);
		mav.addObject("selectCount", req.getParameter("selectCount"));
		System.out.println("流立 itmePrice= "+itemPrice);
		System.out.println("流立 = "+req.getParameter("selectCount"));
		mav.setViewName("ajax/payment2");
		return mav;
	}
	@RequestMapping("/payment3")
	public ModelAndView payment3(HttpServletRequest req, ItemVO vo) {
		HttpSession sion = req.getSession();
		PaymentDAOInterface dao = sqlSession.getMapper(PaymentDAOInterface.class);
		String start = dao.startDelivery((String)sion.getAttribute("proCode"));
		System.out.println("page3 = "+sion.getAttribute("cateCode"));
		MemberVO membervo = dao.baseInformation((String)sion.getAttribute("userMail"));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("start", start);
		mav.addObject("membervo",membervo);
		mav.setViewName("ajax/payment3");
		
		return mav;
	}
	
	@RequestMapping("/complete")
	public ModelAndView pay(HttpServletRequest req, ItemVO ivo , MemberVO mvo) {
		HttpSession sion = req.getSession();
		PaymentDAOInterface dao = sqlSession.getMapper(PaymentDAOInterface.class);
		/*System.out.println("proCode = "+sion.getAttribute("proCode"));
		System.out.println("cateCode = "+sion.getAttribute("cateCode"));
		System.out.println("itemNum = "+ivo.getItemNum());
		System.out.println("itemName = "+ivo.getItemName());
		System.out.println("itemContent = "+ivo.getItemContent());
		System.out.println("itemOption = "+ivo.getItemOption());
		System.out.println("userMail = "+ivo.getUserMail());
		System.out.println("cardNum = "+mvo.getCardNum());
		System.out.println("cardName = "+mvo.getCardName());
		System.out.println("simpleName = "+req.getParameter("simpleName"));
		System.out.println("simpleNum = "+req.getParameter("simpleNum"));*/
		System.out.println("itemPrice = "+ivo.getItemPrice());
		System.out.println("selectCount = "+ivo.getSelectCount());
		dao.proNowUp(ivo.getItemPrice(), (String)sion.getAttribute("proCode"), ivo.getSelectCount());
		
		String cardName = "";
		String cardNum = "";
		if(mvo.getCardName()==null) {
			try {
				cardName = req.getParameter("simpleName").toString();
				cardNum = req.getParameter("simpleNum").toString();
			}catch(NullPointerException ne) {
				System.out.println(ne.getMessage());
			}
		}else {
			cardName = mvo.getCardName();
			cardNum = mvo.getCardNum();
		}
		String[] idList = {"proCode","itemName","itemNum","itemPrice","itemContent","itemOption","selectCount",
							"userMail","cardName","cardNum"};
		Object[] valList = {(String)sion.getAttribute("proCode"),ivo.getItemName(), ivo.getItemNum(), ivo.getItemPrice(), ivo.getItemContent(),ivo.getItemOption(),
							ivo.getSelectCount(),ivo.getUserMail(),cardName, cardNum };
		HashMap map = new HashMap<String, Integer >();
		for(int i=0;i<=idList.length-1;i++) {
			map.put(idList[i], valList[i]);				
		}
		int chk = dao.payConfirm(map);
		dao.sold(ivo.getItemNum(), ivo.getSelectCount());
		//dao.proNowUp(ivo.getItemPrice(), ivo.getSelectCount(), (String)sion.getAttribute("proCode"));
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("ajax/complete");
		mav.addObject("proCode",sion.getAttribute("proCode"));
		mav.addObject("cateCode",sion.getAttribute("cateCode"));
		
		mav.addObject("chk", chk);
		return mav;
	}
}
