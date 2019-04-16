package kr.goott.bridge.admin;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.goott.bridge.list.BannerVO;
import kr.goott.bridge.list.ListDaoInterface;

@Controller
public class AdminController {
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
	
	/////////////////////////////////////////
	//리워드관리 페이지
	@RequestMapping("/adminReward")
	public ModelAndView adminReward(Project2VO vo) {
		System.out.println("들어오나");
		ModelAndView mav = new ModelAndView();
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		
		System.out.println("어드민 리워드="+toString());
		
		ArrayList<Project2VO> list = dao.selectReward(vo);
		
		System.out.println("어드민 리워드="+toString());
		
		mav.addObject("list", list);
		mav.setViewName("/admin/reward");
		
		return mav;
	}
	/////////////////////////////////////////
	
	@RequestMapping(value="/adminBanner")
	public String adminBanner() {
		return "/admin/banner";
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
}
