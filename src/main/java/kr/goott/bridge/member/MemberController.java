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
	
	//ȸ������ ��
	@RequestMapping("/join")
	public String join() {
	
		return "member/join";
	}
	
	//mailSending �ڵ�
	public String mailSending(HttpServletRequest request) {
		//�ڽ��� �ش� gmail ���̵�
		String setfrom = "kimjunho@gmail.com";
		String tomail = request.getParameter("");//�޴»������
		String title = request.getParameter("title"); //����
		String content = request.getParameter("content");//����
	
		try {
			//MimeMessage message = mailSender.createMimeMessage();
			
		}catch(Exception e) {
			System.out.println("�̸��� ������ ����"+e.getMessage());
		}
		
		return "redirect:/member/join";
	}
}
