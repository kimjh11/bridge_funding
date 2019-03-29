package kr.goott.bridge.member;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	
	@Autowired
	//private JavaMailSender mailSender;
	
	//회원가입 폼
	@RequestMapping("/join")
	public String join() {
	
		return "member/join";
	}
	
	//mailSending 코드
	public String mailSending(HttpServletRequest request) {
		//자신의 해당 gmail 아이디
		String setfrom = "kimjunho@gmail.com";
		String tomail = request.getParameter("");//받는사람메일
		String title = request.getParameter("title"); //제목
		String content = request.getParameter("content");//내용
	
		try {
			//MimeMessage message = mailSender.createMimeMessage();
			
		}catch(Exception e) {
			System.out.println("이메일 보내기 에러"+e.getMessage());
		}
		
		return "redirect:/member/join";
	}
}
