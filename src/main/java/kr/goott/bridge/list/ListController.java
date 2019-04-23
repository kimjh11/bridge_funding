package kr.goott.bridge.list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.goott.bridge.project.CategoryVO;

@Controller
public class ListController {
	@Autowired
	SqlSession sqlSession;
	//리워드홈/카테고리홈/오픈예정/기부와후원 /검색리스트출력
	@RequestMapping(value="/list")
	public ModelAndView listPage(HttpServletRequest req,
								@RequestParam("page") String page, 
								@RequestParam("cateCode") String cateCode,
								@RequestParam("keyword") String keyword
		){
		//로그인여부
		String logStatus = (String)req.getSession().getAttribute("logStatus");
		ListDaoInterface dao = sqlSession.getMapper(ListDaoInterface.class);
		List<ListVO> itemList = null;//프로젝트 목록
		List<String> likeProCode = null;//좋아요목록
		
		System.out.println("리스트 출력 실행 \npage="+page+"\ncateCode="+cateCode);

		/*if(logStatus!=null&&!logStatus.equals("N")) {//로그인 후 리스트출력시
			String userMail = (String)req.getSession().getAttribute("userMail");
			likeProCode = dao.likeChk(userMail);
			if(!likeProCode.isEmpty()) {
				for(int i=0; i<likeProCode.size(); i++) {
					System.out.println("좋아요한 프로젝트="+likeProCode.get(i));
				}
			}	
		}*/
		
		if(page.equals("reward")) {//리워드홈 (승인여부:Y, 시작일<=오늘날짜, 마감일>=오늘날짜, 카테고리별+기부후원 전체, 시작일순정렬)
			itemList = dao.allRewardList();
		}else if(page.equals("category")||page.equals("donation")) {//all,donation,...
			if(cateCode.equals("all")) {//카테고리 전체보기(기부후원제외)
				itemList = dao.allCateList();
			}else {//카테고리별
				itemList = dao.selectCateList(cateCode);
			}
		}else if(page.equals("commingSoon")){//오픈예정 (승인여부:Y, 시작일>=오늘날짜, 마감일>=오늘날짜, 승인일순정렬)
			itemList = dao.commingSoonList();
		}else if(page.equals("search")) {
			itemList = dao.keywordSearch(keyword);
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("bannerList", dao.bannerSelect(page));//페이지에 맞는 배너리스트 출력
		mav.addObject("cateList", dao.categorySelect());//카테고리 배너
		mav.addObject("itemList", itemList);//프로젝트리스트
		//mav.addObject("likeProCode", likeProCode);//좋아요한 프로젝트코드
		
		mav.addObject("page", page);//해당페이지명
		mav.addObject("cateCode", cateCode);//카테고리명 : 페이지 정렬할때
		mav.addObject("keyword", keyword);
		mav.setViewName("/list/list");
		
		return mav;
	}

	
	//리스트 정렬
	@RequestMapping(value="/listSort", method=RequestMethod.GET)
	@ResponseBody
	public List<ListVO> listSort(String sort, String page, String cateCode) {

		ListDaoInterface dao = sqlSession.getMapper(ListDaoInterface.class);
		List<ListVO> itemList;
		Map<String, String> map = new HashMap<String, String>();
		map.put("sort",sort);
		map.put("cateCode",cateCode);

		
		//System.out.println("리스트 정렬 실행 \nsort="+sort+"\ncateName="+cateCode);
		
		//최신순 : 기존 리스트 출력한것 맵핑
		if(sort.equals("date")) {
			if(page.equals("reward")) {//리워드
				itemList = dao.allRewardList();
			}else if(page.equals("category")){
				itemList = dao.allCateList();
			}else {
				itemList = dao.selectCateList(cateCode);
			}
		}
		//좋아요순,정렬순
		else{
			if(cateCode==null||cateCode.equals("")||cateCode.equals("all")) {//리워드홈,카테고리홈
				//System.out.println("리워드/카테고리홈정렬실행");
				itemList = dao.rewardListSort(sort);
			}else {//카테고리,기부후원
				//System.out.println("카테고리별 정렬실행\ncateName="+cateCode);
				itemList = dao.cateListSort(map);
			}
		}
		return itemList;
	}
	
	//카테고리 분류 리스트
	public List<CategoryVO> cateSelect(ListDaoInterface dao) {
		List<CategoryVO> cateList = dao.categorySelect();
		return cateList;
	}
	//이벤트 배너 리스트
	public List<BannerVO> bannerSelect(ListDaoInterface dao, String page){
		List<BannerVO> bannerList = dao.bannerSelect(page);
		return bannerList;
	}
	
	//카테고리 페이지 내 카테고리 배너클릭시
	@RequestMapping(value="/selectCate", method=RequestMethod.GET)
	@ResponseBody
	public List<ListVO> selectCate(String cateCode) {
		//System.out.println("카테고리리스트 정렬 실행 \ncateCode="+cateCode);
		
		ListDaoInterface dao = sqlSession.getMapper(ListDaoInterface.class);
		List<ListVO> itemList=null;
		
		if(cateCode.equals("all")) {
			itemList = dao.allCateList();
		}else {
			//System.out.println("카테고리 리스트 출력 선택");
			itemList = dao.selectCateList(cateCode);
		}
		return itemList;
	}
/*	//검색리스트 출력
	@RequestMapping(value="/search")
	public ModelAndView keywordSearch(@RequestParam("keyword") String keyword){
		ListDaoInterface dao = sqlSession.getMapper(ListDaoInterface.class);

		ModelAndView mav = new ModelAndView();
		mav.addObject("searchList", dao.keywordSearch(keyword));
		mav.addObject("keyword", keyword);
		mav.addObject("page", "search");
		mav.setViewName("/list/searchList");
		
		return mav;
	}*/
	//검색리스트 정렬
	@RequestMapping(value="/searchListSort")
	@ResponseBody
	public List<ListVO> searchListSort(String sort, String keyword){
		ListDaoInterface dao = sqlSession.getMapper(ListDaoInterface.class);
		List<ListVO> itemList;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sort", sort);
		map.put("keyword", keyword);
		
		if(sort.equals("date")) {
			itemList = dao.keywordSearch(keyword);
		}else {
			itemList = dao.keywordSort(map);
		}
		
		return itemList;
	}
	
	
/*
	//카테고리 페이지 리스트 출력
		@RequestMapping(value="/category")
		public ModelAndView catePage(HttpServletRequest req,
									@RequestParam("page") String page,
									@RequestParam("cateCode") String cateCode) 
		{
			ListDaoInterface dao = sqlSession.getMapper(ListDaoInterface.class);
			
			List<ListVO> itemList;
			
			if(cateCode==null||cateCode.equals("")||cateCode.equals("all")) {//카테고리명이 없을경우 전체리스트 select
				//(승인여부:Y, 시작일<오늘날짜, 마감일>오늘날짜, 카테고리중'기부/후원'제외, 시작일순정렬)
				itemList = dao.rewardHomeList();
			}else {//해당카테고리 상품리스트 select
				itemList = dao.cateNameList(cateCode);
			}
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("cateList", cateSelect(dao));
			mav.addObject("itemList", itemList);
			mav.addObject("page", page);
			mav.addObject("cateCode", cateCode);
			mav.setViewName("/list/list");
			
			return mav;
		}
	*/
	/*//리워드홈, 오픈예정, 기부와후원 리스트출력
	@RequestMapping(value="/list")
	public ModelAndView listPage(HttpServletRequest req,
								@RequestParam("page") String page,
								@RequestParam("cate") String cate,
								@RequestParam("sort") String sort)
	{
		ListDaoInterface dao = sqlSession.getMapper(ListDaoInterface.class);

		List<ListVO> itemList = null;//프로젝트 목록
		String cateCode = "";
		if(page.equals("리워드홈")) {
			//리워드홈 (승인여부:Y, 시작일<=오늘날짜, 마감일>=오늘날짜, 카테고리중'기부/후원'제외, 시작일순정렬)
			itemList = dao.rewardHomeList();
		}else if(page.equals("오픈예정")) {
			//오픈예정 (승인여부:Y, 시작일>=오늘날짜, 마감일>=오늘날짜, 카테고리중'기부/후원'제외, 승인일순정렬)
			itemList = dao.commingSoonList();
		}else if(page.equals("기부와후원")){
			//기부후원  (승인여부:Y, 시작일<=오늘날짜, 마감일>=오늘날짜, 카테고리중'기부/후원'만, 시작일순정렬)
			itemList = dao.donationList();
			cateCode = "donation";
		}

		ModelAndView mav = new ModelAndView();
		//리스트
		mav.addObject("bannerList", dao.bannerSelect(page));
		mav.addObject("cateList", dao.categorySelect());
		mav.addObject("itemList", itemList);
		//오픈여부
		mav.addObject("cate", cate);
		mav.addObject("sort", sort);
		mav.addObject("page", page);
		//카테고리명 : 페이지 정렬할때,
		mav.addObject("cateCode", cateCode);
		
		mav.setViewName("/list/list");
		
		return mav;
	}*/
	
	
	
	/*@RequestMapping(value="/listSort", method=RequestMethod.GET)
	@ResponseBody
	public List<ListVO> listSort(String sort, String cateCode) throws UnsupportedEncodingException {
		
		
		ListDaoInterface dao = sqlSession.getMapper(ListDaoInterface.class);
		List<ListVO> itemList;

		
		System.out.println("리스트 정렬 실행 \nsort="+sort+"\ncateName="+cateCode);
		
		//최신순 : 기존 리스트 출력한것 맵핑
		if(sort.equals("date")) {
			if(cateCode==null||cateCode.equals("")) {//리워드홈
				itemList = dao.rewardHomeList();
			}else {//카테고리,기부후원
				itemList = dao.cateNameList(cateCode);
			}
		}
		//좋아요순,정렬순
		else{
			if(cateName.equals("none")) {//리워드홈
				System.out.println("리워드홈/정렬실행");
				itemList = dao.rewardListSort(sort);
			}else {//카테고리,기부후원
				System.out.println("카테고리or기부후원/정렬실행\ncateName="+cateName);
				itemList = dao.cateListSort(sort, cateName);
			}
		}
		return itemList;
	}*/
	
}
