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
	
	//ȸ������ ��
	@RequestMapping("/join")
	public String join() {
	
		return "member/join";
	}
	
	//�̸��� ���� �ڵ� ������
	@RequestMapping(value="/mailSending", method=RequestMethod.POST)
	@ResponseBody
	public String UserJoin(MemberVO vo, HttpServletRequest request) {
		
		//�̸��� �����ڵ� ������                                             �̸���
		return mailsender.mailSendWithUserKey(vo.getUserMail(), request); // Ajax ���ϵǴ� ������ - key ��
	}
	
	//�̸��� ���� Ȯ��
	@RequestMapping(value="/mailSendingOk", method= RequestMethod.POST)
	@ResponseBody
	public String userJoinOk(@RequestParam("okNum") String key, HttpServletRequest request) {
		
		return mailsender.userJoinOk(key, request) ;
	}
	
	//ȸ������ �ϱ�
	@RequestMapping(value="/insertMember", method=RequestMethod.POST)
	public ModelAndView insertMember(MemberVO vo, HttpServletRequest request) {
		HttpSession  session = request.getSession(); 
		vo.setMailChk((String)session.getAttribute("key"));
		
		MemberDaoInterface dao = sqlSession.getMapper(MemberDaoInterface.class);
		int cnt = dao.insertRecord(vo);
		
		session.invalidate(); //���� �����
		
		ModelAndView mav = new ModelAndView();
		
		if(cnt>0) {
			mav.setViewName("redirect:/");
		}else {
			mav.setViewName("redirect:join");
		}
		
		return mav;
	}
	///////////////////////////////////////////////////////////////////////
	
	//�α���
	@RequestMapping("/loginOk")
	public ModelAndView loginOk(MemberVO vo, HttpServletRequest request) {
		MemberDaoInterface dao = sqlSession.getMapper(MemberDaoInterface.class);
		MemberVO vo2 = dao.loginCheck(vo);
		
		ModelAndView mav = new ModelAndView();
		if(vo2 != null) {//�α��� ����
			HttpSession session = request.getSession();
			vo2.setLogStatus("Y"); 
			
			session.setAttribute("userMail", vo2.getUserMail()); //���̵�
			session.setAttribute("userImg", vo2.getUserImg());
			System.out.println("userMail = "+ session.getAttribute("userImg"));
			session.setAttribute("userName", vo2.getUserName()); //�̸�
			session.setAttribute("logStatus", vo2.getLogStatus()); //�α��� ����
			session.setAttribute("img", vo2.getUserImg() );
			session.setAttribute("cardName", vo2.getCardName());//ī���̸�
			
			mav.setViewName("redirect:/");
		}else {
			//�α��� ����
			mav.setViewName("redirect:/");
		}
		return mav;
	}
	
	//�α׾ƿ�
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "home";
	}
	
	//�α��� �� �̿밡�� �մϴ�.
	@RequestMapping("/loginAlert")
	public String loginAlert() {
		return "member/loginAlert";
	}
	
	///////////////////////////////////////////////////////////////////////
	
	//���̵�/��й�ȣ ã�� ��
	@RequestMapping("/searchIdPwd")
	public String searchIdPwd() {
		return "member/searchIdPwd";
	}
	
	//���̵� ã��
	@RequestMapping("/idSearch")
	public ModelAndView idSearch(MemberVO vo) {
		MemberDaoInterface dao = sqlSession.getMapper(MemberDaoInterface.class);
		vo = dao.idSearch(vo.getUserMail());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("/member/searchIdPwd");
		
		return mav;
	}
	
	//��й�ȣ ã��
	//�̸��� ���� �ڵ� ������
	@RequestMapping(value="/changePwd", method=RequestMethod.POST)
	@ResponseBody
	public String changPwd(MemberVO vo, HttpServletRequest request) {
		
		//�̸��� �����ڵ� ������                                             �̸���
		return mailsender.mailSendChangePwd(vo.getUserMail(), request); // Ajax ���ϵǴ� ������ - key ��
	}
	
	//�̸��� ���� Ȯ��
	@RequestMapping(value="/changePwdOk", method= RequestMethod.POST)
	@ResponseBody
	public String changePwdOk(@RequestParam("okNum") String key, HttpServletRequest request) {
			
		return mailsender.newPwdOk(key, request) ;
	}
	
	//�� ��й�ȣ ����
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
