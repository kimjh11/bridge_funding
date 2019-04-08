package kr.goott.bridge;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HeaderController {
	
	@RequestMapping("/reward")
	public String reward() {
		return "list/reward";
	}
	
	@RequestMapping("/category")
	public String category() {
		return "list/category";
	}
	
	@RequestMapping("/commingsoon")
	public String commingSoon() {
		return "list/commingSoon";
	}
	
	@RequestMapping("/donation")
	public String donation() {
		return "list/donation";
	}
	
}
