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
	
	//������ (�̹���, ��ȭ��ȣ, �������, �ּ�) ��� /����
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
			
			//���ε��� ���� ���
			folder = request.getSession().getServletContext().getRealPath("/upload");
			System.out.println("folder="+folder);
			
			//���ε�
			//�Ű����� ���ϱ�
			String iname = imgname.getName();
			
			//���ε��� ���ϸ� ���ϱ�
			String img = imgname.getOriginalFilename();
			
			//���ε��ϱ�
			try {
				if(img != null) {
					//���� ���ε� �ϴ� �κ�
					imgname.transferTo(new File(folder, img));
					
				}
			}catch(Exception e) {
				System.out.println("���ε� ����"+e.getMessage());
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
	
	//������ ���� ������ ���� ��������
	@RequestMapping("/profileFormUpdate")
	public ModelAndView profileFormUpdate(MemberVO vo, HttpServletRequest request) {
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		
		vo = dao.selectProfile(vo.getUserMail());
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		session.setAttribute("img", vo.getUserImg()); //���� ����
		
		mav.addObject("vo", vo);
		mav.setViewName("/mypage/profile");
			
		return mav;
	}
	
	//������� ���/����
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
		session.setAttribute("cardName", vo.getCardName()); //ī���̸�
		
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		int cnt = dao.updateCardInfo(vo);
		
		if(cnt>0) {
			mav.setViewName("redirect:mypageForm");
		}else {
			mav.setViewName("redirect:mypageForm");
		}
		return mav;	
	}
	
	//������� ���� ����(update)
	@RequestMapping(value="/cardDelete", method= RequestMethod.POST)
	@ResponseBody
	public ModelAndView cardDelete(@RequestParam("userMail") String userMail, MemberVO vo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		
		int cnt = dao.cardDelete(vo.getUserMail());
		
		HttpSession session = request.getSession();
		session.removeAttribute("cardName"); //ī���̸� ����
			
		if(cnt>0) {
			mav.setViewName("redirect:mypageForm");
		}else {
			mav.setViewName("redirect:mypageForm");
		}
		return mav;
	}
	
	//���������� ��
	@RequestMapping("/mypageForm")
	public String mypageForm() {
		
		return "mypage/mypage";
	}
	
	//���� ������ ������Ʈ
	@RequestMapping(value="/selectMyReward", method=RequestMethod.POST)
	@ResponseBody
	public ArrayList<ProjectVO> selectMyReward(@RequestParam("userMail") String userMail, ProjectVO vo){
		ArrayList<ProjectVO> list = new ArrayList<ProjectVO>();
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		
		list = dao.selectMyReward(userMail);
		
		return list;
	}
	
	//���ƿ� ������Ʈ
	@RequestMapping(value="/selectLike", method= RequestMethod.POST)
	@ResponseBody
	public ArrayList<ProjectVO> selectLike(@RequestParam("userMail") String userMail, ProjectVO vo){
		 ArrayList<ProjectVO> list  = new ArrayList<ProjectVO>();
		 MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		 
		 list = dao.selectLike(userMail);
		
		 return list;
	}
	
	//�������� ������Ʈ �� ��������
	@RequestMapping(value ="/selectStartingPro", method= RequestMethod.POST)
	@ResponseBody
	public ArrayList<ProjectVO> selectStartingPro(@RequestParam("userMail") String userMail, ProjectVO vo) {
		ArrayList<ProjectVO> list = new ArrayList<ProjectVO>();
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		
		list = dao.selectStartingPro(vo.getUserMail());
		
		return list;
	}
	
	//���� ����� �� ��������
	@RequestMapping(value="/selectWaitingPro", method=RequestMethod.POST)
	@ResponseBody
	public ArrayList<ProjectVO> selectWAitingPro(@RequestParam("userMail") String userMail, ProjectVO vo, HttpServletRequest request) {
		ArrayList<ProjectVO> list = new ArrayList<ProjectVO>();
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		
		list = dao.selectWaitingPro(userMail);
	
		return list;
	}
	
	//���ƿ� ����Ʈ
	@RequestMapping(value="/likeList", method= RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ArrayList<MemberVO> likeList(@RequestParam ("proCode") String proCode){
		
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		
		ArrayList<MemberVO> list = dao.selectLikeList(proCode);
		
		System.out.println("proCode="+proCode);
		System.out.println("size="+list.size());
		
		return list;
	}
	
	//�ݵ��� ����Ʈ
	@RequestMapping(value="/fundingList", method= RequestMethod.POST, produces="application/json")
	@ResponseBody
	public ArrayList<MemberVO> fundingList(@RequestParam("proCode") String proCode){
		MypageDaoInterface dao = sqlSession.getMapper(MypageDaoInterface.class);
		
		ArrayList<MemberVO> list = dao.selectfundingList(proCode);
		
		return list;
	}
}
