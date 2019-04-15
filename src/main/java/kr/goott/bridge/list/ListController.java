package kr.goott.bridge.list;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.goott.bridge.project.CategoryVO;

@Controller
public class ListController {
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping(value="/list")
	public ModelAndView listPage(HttpServletRequest req,
								@RequestParam("page") String page,
								@RequestParam("cate") String cate,
								@RequestParam("sort") String sort)
	{
		ListDaoInterface dao = sqlSession.getMapper(ListDaoInterface.class);
		
		List<ListVO> itemList = null;//프로젝트 목록
		
		if(page.equals("리워드홈")) {
			//리워드홈 (승인여부:Y, 시작일<오늘날짜, 마감일>오늘날짜, 카테고리중'기부/후원'제외, 시작일순정렬)
			itemList = dao.rewardHomeList();
		}else if(page.equals("오픈예정")) {
			//오픈예정 (승인여부:Y, 시작일>오늘날짜, 마감일>오늘날짜, 카테고리중'기부/후원'제외, 승인일순정렬)
			itemList = dao.commingSoonList();
		}else if(page.equals("기부와후원")){
			//기부후원  (승인여부:Y, 시작일<오늘날짜, 마감일>오늘날짜, 카테고리중'기부/후원'만, 시작일순정렬)
			itemList = dao.donationList();
		}

		ModelAndView mav = new ModelAndView();
		//리스트
		mav.addObject("bannerList", dao.bannerSelect(page));
		mav.addObject("cateList", dao.categorySelect());
		mav.addObject("itemList", itemList);
		//오픈여부
		mav.addObject("cate", cate);
		mav.addObject("sort", sort);
		
		mav.setViewName("/list/list");
		
		return mav;
	}

	@RequestMapping(value="/category")
	public ModelAndView catePage(HttpServletRequest req,
								@RequestParam("cateName") String cateName) 
	{
		ListDaoInterface dao = sqlSession.getMapper(ListDaoInterface.class);
		
		List<CategoryVO> cateList;
		List<ListVO> itemList;
		
		if(cateName==null||cateName.equals("")) {//카테고리명이 없을경우 전체리스트 select
			//(승인여부:Y, 시작일<오늘날짜, 마감일>오늘날짜, 카테고리중'기부/후원'제외, 시작일순정렬)
			itemList = dao.rewardHomeList();
		}else {//해당카테고리 상품리스트 select
			itemList = dao.cateNameList(cateName);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("cateList", cateSelect(dao));
		mav.addObject("itemList", itemList);
		mav.addObject("cateName", cateName);
		mav.setViewName("/list/categoryList");
		
		return mav;
	}
	//카테고리 리스트
	public List<CategoryVO> cateSelect(ListDaoInterface dao) {
		List<CategoryVO> cateList = dao.categorySelect();
		return cateList;
	}
	//배너 리스트
	public List<BannerVO> bannerSelect(ListDaoInterface dao, String page){
		List<BannerVO> bannerList = dao.bannerSelect(page);
		return bannerList;
	}
  
	
	String folder;
	public JdbcTemplate getTemplate() {
		return template;
	}
	public JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	@RequestMapping(value="/admin")
	public String admin() {
		return "/admin/main";
	}
	
	@RequestMapping(value="/bannerSubmit", method=RequestMethod.POST)
	public ModelAndView bannerUpload(HttpServletRequest req, BannerVO vo,
									@RequestParam("bannerImgFile") MultipartFile bannerImgFile) {
		ModelAndView mav = new ModelAndView();
		ListDaoInterface dao = sqlSession.getMapper(ListDaoInterface.class);
		
		//파일이 저장될 절대경로
		folder = req.getSession().getServletContext().getRealPath("/upload");
	
		String newFileName = null;
		
		if(bannerImgFile!=null) {
			String originName = bannerImgFile.getOriginalFilename();
			
			if(originName != null && !originName.equals("")) {
				//서버에 동일한 파일유무 확인 후 업로드
				File file = new File(folder, originName);
				if(file.exists()) {//동일한 파일이 있을경우
					int cnt=1;
					while(true) {
						int dot = originName.lastIndexOf(".");//마지막.위치
						String preFile = originName.substring(0, dot);//파일명
						String extFile = originName.substring(dot+1);//확장자
						File newFile = new File(folder, preFile+"_"+cnt+"."+extFile);
						if(!newFile.exists()) {
							newFileName = newFile.getName();
							break;
						}
						cnt++;
					}
				}else {//동일한 파일이 없을경우
					newFileName = originName;
				}
				//업로드
				try {
					bannerImgFile.transferTo(new File(folder, newFileName));
				}catch(Exception e) {
					System.out.println("배너 이미지 파일등록 에러"+e.getMessage());
				}
			}
		}
		vo.setBannerImg(newFileName);
		mav.addObject("cnt",dao.bannerInsert(vo));
		mav.setViewName("/admin/main");
		return mav;
	}
	/*ver)2019-04-10
	@RequestMapping("/list")
	public ModelAndView reward(@RequestParam("menuIndex") int menuIndex, 
								@RequestParam("banner") String banner,
								@RequestParam("cate") String cate,
								@RequestParam("sorting") String sorting,
								HttpServletRequest req) {
		ListDaoInterface dao = sqlSession.getMapper(ListDaoInterface.class);
		
		// 배너,카테고리,프로젝트 목록
		
		  
		List<BannerVO> bannerList = null;
		List<CategoryVO> cateList = null;
		List<ListVO> itemList = null; 
		
		if(banner.equals("open")) { //상단배너가 있는 페이지 일 경우 select
			bannerList = dao.bannerSelect(menuIndex);
		}
		if(cate.equals("open")) { //카테고리가 있는 페이지 일 경우 select
			cateList = dao.categorySelect();
		}
		
		//프로젝트 목록 select
		switch (menuIndex) {
			case 1 : //리워드홈
			case 2 : //카테고리홈 (승인여부:Y, 시작일<오늘날짜, 마감일>오늘날짜, 카테고리중'기부/후원'제외, 시작일순정렬)
				System.out.println("실행");
				itemList = dao.rewardHomeList(); break;
			case 3 : //오픈예정 (승인여부:Y, 시작일>오늘날짜, 마감일>오늘날짜, 카테고리중'기부/후원'제외, 승인일순정렬)
				itemList = dao.commingSoonList(); break;
			case 4 : //기부후원  (승인여부:Y, 시작일<오늘날짜, 마감일>오늘날짜, 카테고리중'기부/후원'만, 시작일순정렬)
				itemList = dao.donationList(); break;
		}

		//ListVO listVO;
		//for(int i=0; i<itemList.size(); i++) {
		//	listVO = itemList.get(i);
		//	listVO.toString();
		//}
	
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("banner", banner);
		mav.addObject("cate", cate);
		mav.addObject("sorting", sorting);
		
		mav.addObject("bannerList", bannerList);
		mav.addObject("cateList", cateList);
		mav.addObject("itemList", itemList);
		
		mav.setViewName("/list/list");

		return mav;
	}*/

}
