package kr.goott.bridge.guide;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class guideController {
	@RequestMapping("/guideHome")
	public String guideHome() {
		return "guide/guideHome";
	}
	
	
	
}
