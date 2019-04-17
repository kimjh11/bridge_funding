package kr.goott.bridge.admin;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.goott.bridge.admin.AdminDaoInterface;
import kr.goott.bridge.list.BannerVO;
import kr.goott.bridge.project.CategoryVO;

@Controller
public class AdminController {
	@Autowired
	SqlSession sqlSession;
	
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
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		
		//파일이 저장될 절대경로
		folder = req.getSession().getServletContext().getRealPath("/img/banner");
	
		String newFileName = null;
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
		vo.setBannerImg(newFileName);
		dao.bannerInsert(vo);
		mav.setViewName("redirect:adminBanner");
		return mav;
	}
	
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
		System.out.println(table);
		System.out.println(itemNum);
		
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
	
	//카테고리 관리페이지
	@RequestMapping(value="/adminCategory")
	public ModelAndView adminCategory() {
		ModelAndView mav = new ModelAndView();
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		
		mav.addObject("cateList", dao.cateSelectAll());
		mav.setViewName("/admin/category");
		
		return mav;
	}
	
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
	
	
}
