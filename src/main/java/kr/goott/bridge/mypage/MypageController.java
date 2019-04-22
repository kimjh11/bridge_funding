package kr.goott.bridge.mypage;
 
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.goott.bridge.member.MemberDaoInterface;
import kr.goott.bridge.member.MemberVO;
import kr.goott.bridge.project.ProjectVO;
 
@Controller
public class MypageController {
	String folder;
	
	@Autowired
	SqlSession sqlSession;
	
	//프로필 (이미지, 전화번호, 생년월일, 주소) 등록 /수정
		@RequestMapping(value="/updateProfile", method= RequestMethod.POST)
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
			
			if(img == null || img.equals("")) {
				vo.setUserImg(null);
			}else {
				vo.setUserImg(img);
			}
			
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
				mav.addObject("img", vo.getUserImg());
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
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		session.setAttribute("img", vo.getUserImg()); //세션 저장
		
		mav.addObject("vo", vo);
		mav.setViewName("/mypage/profile");
			
		return mav;
	}
	
	//간편결제 등록/수정
	@RequestMapping(value="/cardInfo", method=RequestMethod.POST)
	public ModelAndView cardInfo(@RequestParam("cardDate") String cardDate,
								 @RequestParam("cardName") String cardName,
								 @RequestParam("cardPwd") String cardPwd,
								 @RequestParam("userMail") String userMail,
								 HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();

		MemberVO vo = new MemberVO();
		
		vo.setCardNum(request.getParameter("cardNum1")+"-"+request.getParameter("cardNum2")+"-"+request.getParameter("cardNum3")+"-"+request.getParameter("cardNum4"));
		vo.setCardDate(cardDate);
		vo.setCardName(cardName);
		vo.setCardPwd(cardPwd);
		vo.setUserMail(userMail);
		
		HttpSession session = request.getSession();
		session.setAttribute("cardName", vo.getCardName()); //카드이름
		
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		int cnt = dao.updateCardInfo(vo);
		
		if(cnt>0) {
			mav.setViewName("redirect:mypageForm");
		}else {
			mav.setViewName("redirect:mypageForm");
		}
		return mav;	
	}
	
	//간편결제 정보 삭제(update)
	@RequestMapping(value="/cardDelete", method= RequestMethod.POST)
	@ResponseBody
	public ModelAndView cardDelete(@RequestParam("userMail") String userMail, MemberVO vo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		
		int cnt = dao.cardDelete(vo.getUserMail());
		
		HttpSession session = request.getSession();
		session.removeAttribute("cardName"); //카드이름 삭제
			
		if(cnt>0) {
			mav.setViewName("redirect:mypageForm");
		}else {
			mav.setViewName("redirect:mypageForm");
		}
		return mav;
	}
	
	//마이페이지 폼
	@RequestMapping("/mypageForm")
	public String mypageForm() {
		
		return "mypage/mypage";
	}
	
	//나의 리워드 프로젝트
	@RequestMapping(value="/selectMyReward", method=RequestMethod.POST)
	@ResponseBody
	public ArrayList<ProjectVO> selectMyReward(@RequestParam("userMail") String userMail, ProjectVO vo){
		ArrayList<ProjectVO> list = new ArrayList<ProjectVO>();
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		
		list = dao.selectMyReward(userMail);
		
		return list;
	}
	
	//좋아요 프로젝트
	@RequestMapping(value="/selectLike", method= RequestMethod.POST)
	@ResponseBody
	public ArrayList<ProjectVO> selectLike(@RequestParam("userMail") String userMail, ProjectVO vo){
		 ArrayList<ProjectVO> list  = new ArrayList<ProjectVO>();
		 MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		 
		 list = dao.selectLike(userMail);
		
		 return list;
	}
	
	//진행중인 프로젝트 값 가져오기
	@RequestMapping(value ="/selectStartingPro", method= RequestMethod.POST)
	@ResponseBody
	public ArrayList<ProjectVO> selectStartingPro(@RequestParam("userMail") String userMail, ProjectVO vo) {
		ArrayList<ProjectVO> list = new ArrayList<ProjectVO>();
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		
		list = dao.selectStartingPro(vo.getUserMail());
		
		return list;
	}
	
	//승인 대기중 값 가져오기
	@RequestMapping(value="/selectWaitingPro", method=RequestMethod.POST)
	@ResponseBody
	public ArrayList<ProjectVO> selectWAitingPro(@RequestParam("userMail") String userMail, ProjectVO vo, HttpServletRequest request) {
		ArrayList<ProjectVO> list = new ArrayList<ProjectVO>();
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		
		list = dao.selectWaitingPro(userMail);
	
		return list;
	}
	
	//좋아요 리스트
	@RequestMapping(value="/likeList", method= RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ArrayList<MemberVO> likeList(@RequestParam ("proCode") String proCode){
		
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		
		ArrayList<MemberVO> list = dao.selectLikeList(proCode);
		
		System.out.println("proCode="+proCode);
		System.out.println("size="+list.size());
		
		return list;
	}
	
	//펀딩한 리스트
	@RequestMapping(value="/fundingList", method= RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ArrayList<MemberVO> fundingList(@RequestParam("proCode") String proCode){
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		
		ArrayList<MemberVO> list = dao.selectfundingList(proCode);
		
		return list;
	}
}
