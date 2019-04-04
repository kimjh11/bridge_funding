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
	
	//�̸��� ���� ����� �޼���
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
	
	//������ �̿��� Ű ����
	private boolean lowerCheck;
	private int size;
	
	public String getKey(boolean lowerCheck, int size) {
		this.lowerCheck = lowerCheck;
		this.size = size;
		return init();
	}
	
	//ȸ������ �߼� �̸���(����Ű �߼�)
	public String mailSendWithUserKey(String userMail,HttpServletRequest request) {
		String key = getKey(false, 20);
		memberDao = sqlSession.getMapper(MemberDaoInterface.class);
		
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr="<h2>�ȳ��ϼ���. Bridge Funding �Դϴ�.</h2><br/>"
					+"<p>����Ű�� �Է��� �� ���� Ȯ���� ���ּ��� :)</p><br/>"
					+"<p>����Ű : "+key+"</P>"; 		
		try {
			mail.setSubject("[��������] MS: Bridge Funding ���� �����Դϴ�.","UTF-8");
			mail.setText(htmlStr,"UTF-8","html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(userMail));
			mailSender.send(mail);
			
			HttpSession session = request.getSession();
			session.setAttribute("key", key);// key ���Ͽ� �����
			
		}catch (Exception e) {
			System.out.println("�߼� �̸��� ����"+e.getMessage());
		}
		return key;
	}
	
	//�̸��� ���� Ȯ�� 
	public String userJoinOk(String key, HttpServletRequest request) {
			String resultCnt = "fail";
			
			 HttpSession session = request.getSession();
			 
			 if(session.getAttribute("key").equals(key)) {
				 resultCnt = "success";
			 }
			
			return resultCnt;
	}
}
