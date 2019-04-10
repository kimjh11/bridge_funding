package kr.goott.bridge.list;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.goott.bridge.project.CategoryVO;

@Controller
public class ListController {
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping("/list")
	public ModelAndView reward(@RequestParam("menuIndex") int menuIndex, 
								@RequestParam("banner") String banner,
								@RequestParam("cate") String cate,
								@RequestParam("sorting") String sorting,
								HttpServletRequest req) {
		
		CategoryVO cateVO = new CategoryVO();
		BannerVO banVO = new BannerVO();
		
		List<ListVO> list = null;//목록을 담을 List객체
		
		ListDaoInterface dao = sqlSession.getMapper(ListDaoInterface.class);
		
		if(banner.equals("open")) dao.bannerSelect(menuIndex);
		if(cate.equals("open")) dao.categorySelect();
		
		switch (menuIndex) {
			case 1 : //리워드홈
				list = dao.rewardHomeList(); break;
			case 2 : //카테고리홈
				list = dao.cateHomeList(); break;
			case 3 : //오픈예정
				list = dao.commingSoonList(); break;
			case 4 : //기부후원
				list = dao.donationList(); break;
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("/list/list");

		
		return mav;
	}
}
