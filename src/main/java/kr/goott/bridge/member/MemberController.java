package kr.goott.bridge.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	
	@Autowired
	private MailSendService mailsender;
	@Autowired
	SqlSession sqlSession;
	
	//회원가입 폼
	@RequestMapping("/join")
	public String join() {
	
		return "member/join";
	}
	
	//이메일 인증 코드 보내기
	@RequestMapping(value="/mailSending", method=RequestMethod.POST)
	@ResponseBody
	public String UserJoin(MemberVO vo, HttpServletRequest request) {
		
		//이메일 인증코드 보내기                                             이메일
		return mailsender.mailSendWithUserKey(vo.getUserMail(), request); // Ajax 리턴되는 데이터 - key 값
	}
	
	//이메일 인증 확인
	@RequestMapping(value="/mailSendingOk", method= RequestMethod.POST)
	@ResponseBody
	public String userJoinOk(@RequestParam("okNum") String key, HttpServletRequest request) {
		
		return mailsender.userJoinOk(key, request) ;
	}
	
	//회원가입 하기
	@RequestMapping(value="/insertMember", method=RequestMethod.POST)
	public ModelAndView insertMember(MemberVO vo, HttpServletRequest request) {
		HttpSession  session = request.getSession(); 
		vo.setMailChk((String)session.getAttribute("key"));
		
		MemberDaoInterface dao = sqlSession.getMapper(MemberDaoInterface.class);
		int cnt = dao.insertRecord(vo);
		
		session.invalidate(); //세션 지우기
		
		ModelAndView mav = new ModelAndView();
		
		if(cnt>0) {
			mav.setViewName("redirect:/");
		}else {
			mav.setViewName("redirect:join");
		}
		
		return mav;
	}
	///////////////////////////////////////////////////////////////////////
	
	//로그인
	@RequestMapping("/loginOk")
	public ModelAndView loginOk(MemberVO vo, HttpServletRequest request) {
		MemberDaoInterface dao = sqlSession.getMapper(MemberDaoInterface.class);
		MemberVO vo2 = dao.loginCheck(vo);
		
		ModelAndView mav = new ModelAndView();
		if(vo2 != null) {//로그인 성공
			HttpSession session = request.getSession();
			vo2.setLogStatus("Y"); 
			
			session.setAttribute("userMail", vo2.getUserMail()); //아이디
			session.setAttribute("userImg", vo2.getUserImg());
			System.out.println("userMail = "+ session.getAttribute("userImg"));
			session.setAttribute("userName", vo2.getUserName()); //이름
			session.setAttribute("logStatus", vo2.getLogStatus()); //로그인 여부
			session.setAttribute("img", vo2.getUserImg() );
			session.setAttribute("cardName", vo2.getCardName());//카드이름
			
			mav.setViewName("redirect:/");
		}else {
			//로그인 실패
			mav.setViewName("redirect:/");
		}
		return mav;
	}
	
	//로그아웃
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "home";
	}
	
	//로그인 후 이용가능 합니다.
	@RequestMapping("/loginAlert")
	public String loginAlert() {
		return "member/loginAlert";
	}
	
	///////////////////////////////////////////////////////////////////////
	
	//아이디/비밀번호 찾기 폼
	@RequestMapping("/searchIdPwd")
	public String searchIdPwd() {
		return "member/searchIdPwd";
	}
	
	//아이디 찾기
	@RequestMapping("/idSearch")
	public ModelAndView idSearch(MemberVO vo) {
		MemberDaoInterface dao = sqlSession.getMapper(MemberDaoInterface.class);
		vo = dao.idSearch(vo.getUserMail());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("/member/searchIdPwd");
		
		return mav;
	}
	
	//비밀번호 찾기
	//이메일 인증 코드 보내기
	@RequestMapping(value="/changePwd", method=RequestMethod.POST)
	@ResponseBody
	public String changPwd(MemberVO vo, HttpServletRequest request) {
		
		//이메일 인증코드 보내기                                             이메일
		return mailsender.mailSendChangePwd(vo.getUserMail(), request); // Ajax 리턴되는 데이터 - key 값
	}
	
	//이메일 인증 확인
	@RequestMapping(value="/changePwdOk", method= RequestMethod.POST)
	@ResponseBody
	public String changePwdOk(@RequestParam("okNum") String key, HttpServletRequest request) {
			
		return mailsender.newPwdOk(key, request) ;
	}
	
	//새 비밀번호 변경
	@RequestMapping(value="/newPwd", method=RequestMethod.POST)
	public ModelAndView newPwd(MemberVO vo) {
		MemberDaoInterface dao = sqlSession.getMapper(MemberDaoInterface.class);
		int cnt = dao.updatePwd(vo);
		
		ModelAndView mav = new ModelAndView();
		
		if(cnt>0) {
			mav.setViewName("redirect:/");
		}else {
			mav.setViewName("redirect:searchIdPwd");
		}
		return mav; 
	}
}
