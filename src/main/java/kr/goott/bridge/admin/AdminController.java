package kr.goott.bridge.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.goott.bridge.admin.AdminDaoInterface;
import kr.goott.bridge.list.BannerVO;
import kr.goott.bridge.project.CategoryVO;

import kr.goott.bridge.project.ItemVO;
import kr.goott.bridge.project.ProjectVO;

@Controller
public class AdminController {
	@Autowired
	SqlSession sqlSession;
	
	//트랜잭션 선언
	@Autowired
	PlatformTransactionManager transactionManager;
	
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
	

	

	/////////////////////////////////////////
	//어드민 로그인
	@RequestMapping("/adminloginOk")
	public ModelAndView adminloginOk(AdminVO vo, HttpServletRequest request) {
		
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		AdminVO vo2 = dao.login(vo);
		
		ModelAndView mav = new ModelAndView(); 
		if(vo2 != null) {
			HttpSession session = request.getSession();
			vo2.setAdminloginCheck("Y");
			
			session.setAttribute("adminloginCheck", vo2.getAdminloginCheck()); //로그인 여부
			session.setAttribute("adminId", vo2.getAdminId());
			
			mav.setViewName("redirect:adminReward");
		}else {
			mav.setViewName("redirect:/admin");
		}
		
		return mav;
	}
	
	//어드민 로그아웃
	@RequestMapping("/adminlogout")
	public String adminlogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "admin/main";
	}
	
	//리워드관리 페이지 - project2 리스트를 출력 / 페이징
	@RequestMapping(value="/adminReward")
	public ModelAndView adminReward(Project2VO vo, HttpServletRequest req) {
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		
		//현재 페이지
		String numStr = req.getParameter("num");
		if(numStr == null){
			vo.setNum(1);
		}else{
			vo.setNum(Integer.parseInt(numStr));
		}
			
		//총 레코드 수
		vo.setTotalRecord(dao.totalRecord());
		
		//페이징 구하기
		ArrayList<Project2VO> list = dao.selectReward(vo.getNum(), vo.getOnePageRecord(), vo.getLastRecord(), vo.getTotalPage(), 
													  (vo.getNum()*vo.getOnePageRecord()));
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("vo", vo);
		mav.addObject("list", list);
		mav.setViewName("/admin/reward");
		
		System.out.println("마지막페이지="+vo.getLastRecord());
		System.out.println("토탈레코드="+vo.getTotalRecord());
		System.out.println("시작페이지="+vo.getStartPage());
		System.out.println("검토여부="+vo.getWriteFinish());
		
		return mav;
	}
	
	//리워드관리 페이지 - detail페이지 미리보기 
	@RequestMapping(value="/adminDetailPage")
	public ModelAndView adminDetailPage(Project2VO vo, Item2VO itemvo){
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		
		List<Project2VO> vo2 =dao.selectRecord(vo.getCateCode(),vo.getProCode()); //스토리,펀딩안내
		List<Item2VO> list = dao.SelectItemList(itemvo.getProCode()); //상품
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo2", vo2.get(0));
		mav.addObject("listItem", list);
		mav.setViewName("admin/deTailPageReView"); // 상세페이지 - 미리보기
		
		return mav;
	}
	
	//리워드관리 페이지 - deTailGuideReView  - 교환/반품
	@RequestMapping(value="/deTailGuideReView")
	public ModelAndView deTailGuideReView(Project2VO vo) {
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		vo = dao.selectChangeReturn(vo.getCateCode(), vo.getProCode());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("admin_ajax/deTailGuideReView");
		
		return mav;
	}
	
	//리워드관리 페이지 - deTailOpenReView - 회사정보
	@RequestMapping(value="/deTailOpenReView")
	public ModelAndView deTailOpenReView(Project2VO vo) {
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		vo = dao.selectCompanyInfo(vo.getCateCode(),vo.getProCode());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("admin_ajax/deTailOpenReView");
		
		return mav;
	}
	
	//프로젝트 승인하기 - pro2 update (날짜계산)
	@RequestMapping(value="/projectOk")
	public ModelAndView projectOk(Project2VO vo) {
		//트랜잭션
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		ModelAndView mav = new ModelAndView();
		
		try {
			String proCode = vo.getProCode();
			int proDate = vo.getProDate();
			
			AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
			//                                        proOpen                  
			int cnt  = dao.updatePro2(proCode, (vo.getProWait()+1), proDate);
			
			if(cnt>0){
				mav.addObject("cateCode", vo.getCateCode());//카테고리 코드
				mav.addObject("proCode", vo.getProCode());// 프로젝트 코드
				mav.setViewName("redirect:insertPro");
			}else {
				mav.setViewName("redirect:adminDetailPage");
			}
			
			//정상실행되었을때
			transactionManager.commit(status);
		
		}catch(Exception e){
			//쿼리문에서 에러발생시
			transactionManager.rollback(status);
		}
		return mav;
	}
	
	//프로젝트 승인하기 - pro insert (카테고리 이름 조인)
	@RequestMapping(value="/insertPro")
	public ModelAndView insertPro(ProjectVO vo) {
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		ModelAndView mav = new ModelAndView();
		
		try {
			AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
			
			int cnt = dao.insertPro(vo);
			
			if(cnt>0) {
				mav.addObject("proCode", vo.getProCode());
				mav.setViewName("redirect:itemInsert");
			}else {
				mav.setViewName("redirect:adminDetailPage");
			}
			
			//정상실행되었을때
			transactionManager.commit(status);
		}catch(Exception e) {
			//쿼리문에서 에러발생시
			transactionManager.rollback(status);
		}
		return mav;
	}
	
	//프로젝트 승인하기 - item2->item insert
 	@RequestMapping("/itemInsert")
 	public ModelAndView itemInsert(ItemVO vo) {
 		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		ModelAndView mav = new ModelAndView();	
		try {
			AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
			int cnt = dao.itemInsert(vo);
			
			if(cnt>0) {
				mav.addObject("proCode", vo.getProCode());
				mav.setViewName("redirect:item2Delete");
			}else {
				mav.setViewName("redirect:adminDetailPage");
			}
			//정상실행되었을때
			transactionManager.commit(status);
		}catch (Exception e) {
			//쿼리문에서 에러발생시
			transactionManager.rollback(status);
		}
 		return mav;
 	}
	
 	//프로젝트 승인하기 - item2 삭제
 	@RequestMapping("/item2Delete")
 	public ModelAndView item2Delete(Item2VO vo) {
 		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		ModelAndView mav = new ModelAndView();	
 		
		try {
			AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
			int cnt = dao.item2Delete(vo);
			
			if(cnt>0) {
				mav.addObject("proCode", vo.getProCode());
				mav.setViewName("redirect:pro2Delete");
			}else {
				mav.setViewName("redirect:adminDetailPage");
			}
			
			//정상실행되었을때
			transactionManager.commit(status);
		}catch (Exception e) {
			//쿼리문에서 에러발생시
			transactionManager.rollback(status);
		}
		return mav;
 	}
	
 	//프로젝트 승인하기 - pro2 삭제
 	 	@RequestMapping("/pro2Delete")
 	 	public ModelAndView pro2Delete(Project2VO vo) {
 	 		TransactionDefinition definition = new DefaultTransactionDefinition();
 			TransactionStatus status = transactionManager.getTransaction(definition);
 			ModelAndView mav = new ModelAndView();	
 	 		
 			try {
 				AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
 				int cnt = dao.pro2Delete(vo);
 				
 				if(cnt>0) {
 					
 					mav.setViewName("redirect:adminReward");
 				}else {
 					mav.setViewName("redirect:adminDetailPage");
 				}
 				
 				//정상실행되었을때
 				transactionManager.commit(status);
 			}catch (Exception e) {
 				//쿼리문에서 에러발생시
 				transactionManager.rollback(status);
 			}
 			
 	 		return mav;
 	 	}
 	 	
	/////////////////////////////////////////

	
	///////////////////////////// 배너 관리 //////////////////////////////////////
		
	//배너관리 페이지
	@RequestMapping(value="/adminBanner")
	public ModelAndView adminBanner() {
		ModelAndView mav = new ModelAndView();
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		
		mav.addObject("bannerList", dao.bannerSelectAll());
		mav.setViewName("/admin/banner");
		
		return mav;
	}
	
	//배너 리스트 등록
	@RequestMapping(value="/bannerSubmit", method=RequestMethod.POST)
	public ModelAndView bannerUpload(HttpServletRequest req, BannerVO vo,
									@RequestParam("bannerImgFile") MultipartFile bannerImgFile) {
		ModelAndView mav = new ModelAndView();
		//파일이 저장될 절대경로
		folder = req.getSession().getServletContext().getRealPath("/img/banner");
	
		//파일업로드 + 업로드된 파일명세팅
		vo.setBannerImg(fileUpload(bannerImgFile, folder));
		
		//데이터 추가
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		dao.bannerInsert(vo);
		
		mav.setViewName("redirect:adminBanner");
		return mav;
	}
	
	//배너 수정
	@RequestMapping(value="/bannerUpdate", method=RequestMethod.POST)
	public ModelAndView bannerUpdate(BannerVO vo) {
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		
		BannerVO originVO = dao.bannerSelect(vo.getBannerNum());
		
		if(vo.getBannerImg()==null || vo.getBannerImg().equals("")) {
			vo.setBannerImg(originVO.getBannerImg());
		}if(vo.getBannerTitle()==null || vo.getBannerTitle().equals("")) {
			vo.setBannerTitle(originVO.getBannerTitle());
		}if(vo.getBannerSubTitle()==null || vo.getBannerSubTitle().equals("")) {
			vo.setBannerSubTitle(originVO.getBannerSubTitle());
		}if(vo.getBannerTitle()==null || vo.getBannerTitle().equals("")) {
			vo.setBannerTitle(originVO.getBannerTitle());
		}if(vo.getBannerLink()==null || vo.getBannerLink().equals("")) {
			vo.setBannerLink(originVO.getBannerLink());
		}
		System.out.println(vo.toString());
		dao.bannerUpdate(vo);
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:adminBanner");

		return mav;
	}

	/////////////////////////// 카테고리 ////////////////////////////////
	
	//카테고리 관리페이지
	@RequestMapping(value="/adminCategory")
	public ModelAndView adminCategory() {
		ModelAndView mav = new ModelAndView();
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		
		mav.addObject("cateList", dao.cateSelectAll());
		mav.setViewName("/admin/category");
		
		return mav;
	}
	
	//카테고리 등록
	@RequestMapping(value="/cateSubmit", method=RequestMethod.POST)
	public ModelAndView cateUpdate(HttpServletRequest req, CategoryVO vo,
									@RequestParam("cateImgFile") MultipartFile cateImgFile) {
		//이미지파일이 저장될 절대경로
		folder = req.getSession().getServletContext().getRealPath("/img/category");
		//파일업로드 + 업로드된 파일명세팅
		vo.setCateImg(fileUpload(cateImgFile, folder));
		
		//데이터 추가
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		dao.cateInsert(vo);
		
		//카테고리 관리페이지 이동
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:adminCategory");
		
		return mav;
	}
	
	
	
	//////////////////////// 공통 사용 /////////////////////////////////////
	
	//파일중복확인 후 추가
	public String fileUpload(MultipartFile fileName, String folder) {
		String newFileName = null;
		String originName = fileName.getOriginalFilename();
		
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
				fileName.transferTo(new File(folder, newFileName));
			}catch(Exception e) {
				System.out.println("이미지 파일등록 에러"+e.getMessage());
			}
		}
		return newFileName;
	}
	
	//오픈여부 수정
	@RequestMapping(value="/openToggle", method=RequestMethod.GET)
	@ResponseBody
	public String openToggle(String itemNum, String table) {
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		//System.out.println(table);
		//System.out.println(itemNum);
		
		int cnt=0;
		
		if(table.equals("category")) {
			cnt = dao.cateOpenToggle(Integer.parseInt(itemNum));
		}else if(table.equals("banner")) {
			cnt = dao.bannerOpenToggle(Integer.parseInt(itemNum));
		}

		if(cnt>0) {
			return "ok";
		}else {
			return "no";
		}
	}
	
	//데이터 삭제
	@RequestMapping(value="/adminDeleteData", method=RequestMethod.GET)
	@ResponseBody
	public void delData(@RequestParam("page") String page, @RequestParam("itemNum") int itemNum) {
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);

		int cnt = 0;
		if(page.equals("category")) {
			cnt = dao.cateDelete(itemNum);
		}if(page.equals("banner")) {
			cnt = dao.bannerDelete(itemNum);
		}
		
		
	
	}
	
	
	
}
