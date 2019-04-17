package kr.goott.bridge.member;

import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private MemberDaoInterface memberDao;
	
	//이메일 난수 만드는 메서드
	private String init() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;
		
		do {
			num = ran.nextInt(75)+48;
			if((num>=48 && num <=57) || (num >=65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char)num);
			}else {
				continue;
			}
		}while(sb.length() < size);
		if(lowerCheck) {
			return sb.toString().toLowerCase();
		}
		return sb.toString();
	}
	
	//난수를 이용한 키 생성
	private boolean lowerCheck;
	private int size;
	
	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		return init();
	}
	
	//회원강비 발송 이메일 (인증키 발송)
	public String mailSendWithUserKey(String userMail,HttpServletRequest request) {
		String key = getKey(false, 20);
		memberDao = sqlSession.getMapper(MemberDaoInterface.class);
		
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr="<h2>안녕하세요. Bridge Funding 입니다.</h2><br/>"
					+"<p>인증키를 입력한 후 인증 확인을 해주세요:)</p><br/>"
					+"<p>인증키 : "+key+"</P>"; 		
		try {
			mail.setSubject("[본인인증] MS: Bridge Funding 인증 메일입니다.","UTF-8");
			mail.setText(htmlStr,"UTF-8","html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(userMail));
			mailSender.send(mail);
			
			HttpSession session = request.getSession();
			session.setAttribute("key", key);// key 세션에 저장됨
			
		}catch (Exception e) {
			System.out.println("발송 이메일 에러"+e.getMessage());
		}
		return key;
	}
	
	//이메일 인증 확인 
	public String userJoinOk(String key, HttpServletRequest request) {
			String resultCnt = "fail";
			
			 HttpSession session = request.getSession();
			 
			 if(session.getAttribute("key").equals(key)) {
				 resultCnt = "success";
			 }
			
			return resultCnt;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//새 비밀번호 변경 발송 이메일 (인증키 발송)
	public String mailSendChangePwd(String userMail,HttpServletRequest request) {
		String key = getKey(false, 20);
		memberDao = sqlSession.getMapper(MemberDaoInterface.class);
			
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr="<h2>안녕하세요. Bridge Funding 입니다.</h2><br/>"
						+"<p>인증키를 입력한 후 비밀번호를 변경해 주세요 :)</p><br/>"
						+"<p>인증키 : "+key+"</P>"; 		
		try {
			mail.setSubject("[본인인증] MS: Bridge Funding 비밀번호 변경 인증 메일입니다.","UTF-8");
			mail.setText(htmlStr,"UTF-8","html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(userMail));
			mailSender.send(mail);
				
			HttpSession session = request.getSession();
			session.setAttribute("key", key);// key 세션에 저장됨
				
		}catch (Exception e) {
			System.out.println("새 비밀번호 발송 이메일 에러"+e.getMessage());
		}
		return key;
	}
	
	//이메일 인증 확인(새 비밀번호)
	public String newPwdOk(String key, HttpServletRequest request) {
		String resultCnt = "fail";
		
		HttpSession session = request.getSession();
				 
		if(session.getAttribute("key").equals(key)) {
			resultCnt = "success";
		}
				
		return resultCnt; 
	}
}
