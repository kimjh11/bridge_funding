package kr.goott.bridge.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MypageController {
	
	//프로필 폼
	@RequestMapping("/profileForm")
	public String profileForm() {
		
		return "mypage/profile";
	}
	
	//마이페이지 폼
	@RequestMapping("/mypageForm")
	public String mypageForm() {
		
		return "mypage/mypage";
	}
	
}
