package kr.goott.bridge.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MypageController {
	
	//������ ��
	@RequestMapping("/profileForm")
	public String profileForm() {
		
		return "mypage/profile";
	}
	
	//���������� ��
	@RequestMapping("/mypageForm")
	public String mypageForm() {
		
		return "mypage/mypage";
	}
	
}
