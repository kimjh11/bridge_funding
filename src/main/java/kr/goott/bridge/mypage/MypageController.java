package kr.goott.bridge.mypage;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.goott.bridge.member.MemberVO;

@Controller
public class MypageController {
	String folder;
	
	@Autowired
	SqlSession sqlSession;
	
	//프로필 폼
	/*@RequestMapping("/profileForm")
	public String profileForm() {
		
		return "mypage/profile";
	}*/
	
	//프로필 (이미지, 전화번호, 생년월일, 주소) 등록 /수정
		@RequestMapping(value="/updateProfile", method=RequestMethod.POST)
		public ModelAndView updateProfile(@RequestParam("userImg") MultipartFile imgname, 
										  @RequestParam("userTel") String userTel,
										  @RequestParam("zipcode") String zipcode,
										  @RequestParam("addrSearch") String addrSearch,
										  @RequestParam("addr") String addr,
										  @RequestParam("addrdetail") String addrdetail,
										  @RequestParam("userMail") String userMail,
										  HttpServletRequest request) {
			
			ModelAndView mav = new ModelAndView();
			
			//업로드할 절대 경로
			folder = request.getSession().getServletContext().getRealPath("/upload");
			System.out.println("folder="+folder);
			
			//업로드
			//매개변수 구하기
			String iname = imgname.getName();
			
			//업로드할 파일명 구하기
			String img = imgname.getOriginalFilename();
			
			//업로드하기
			try {
				if(img != null) {
					//실제 업로드 하는 부분
					imgname.transferTo(new File(folder, img));
				}
			}catch(Exception e) {
				System.out.println("업로드 에러"+e.getMessage());
			}
			
			MemberVO vo = new MemberVO();
			vo.setUserImg(img);
			
			HttpSession session = request.getSession();
			session.setAttribute("img", vo.getUserImg());
			
			vo.setUserTel(userTel);
			vo.setBirth(request.getParameter("birth1")+"/"+request.getParameter("birth2")+"/"+request.getParameter("birth3"));
			vo.setZipcode(zipcode);
			vo.setAddrSearch(addrSearch);
			vo.setAddr(addr);
			vo.setAddrdetail(addrdetail);
			vo.setUserMail(userMail);
			
			MypageDaoInterface dao =  sqlSession.getMapper(MypageDaoInterface.class);
			int cnt = dao.updatePro(vo);
			
			if(cnt>0) {
				mav.setViewName("redirect:mypageForm");
			}else {
				mav.setViewName("redirect:profileForm");
			}
			return mav;
		}
	
	//프로필 폼에 저장한 정보 가져오기
	@RequestMapping("/profileFormUpdate")
	public ModelAndView profileFormUpdate(MemberVO vo, HttpServletRequest request) {
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		
		vo = dao.selectProfile(vo.getUserMail());
		
		//vo.setBirtharr(vo.getBirth().split("/"));
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("vo", vo);
		mav.setViewName("/mypage/profile");
			
		return mav;
	}
	
	
	//마이페이지 폼
	@RequestMapping("/mypageForm")
	public String mypageForm() {
		
		return "mypage/mypage";
	}
	 
}
