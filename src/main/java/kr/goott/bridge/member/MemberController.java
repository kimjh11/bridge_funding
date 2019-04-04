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
		
		
		System.out.println(vo.getUserMail());
		System.out.println(vo.getUserName());
		System.out.println(vo.getUserPwd());
		System.out.println(vo.getMailChk());
		
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
	
	//���̵�/��й�ȣ ã�� ��
	@RequestMapping("/searchIdPwd")
	public String searchIdPwd() {
		return "member/searchIdPwd";
	}
}
