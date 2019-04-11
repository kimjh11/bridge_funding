package kr.goott.bridge.deTailPage;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeTailPageController {
	
	@RequestMapping("/deTailPage")
	public String deTailPage() {
		return "deTail/deTailPage";
	}
	@RequestMapping("/deTailGuide")
	public String deTailGuide() {
		return "ajax/deTailGuide";
	}
	@RequestMapping("/deTailOpen")
	public String deTailOpen() {
		return "ajax/deTailOpen";
	}
	@RequestMapping("/deTailReply")
	public String deTailReply() {
		return "ajax/deTailReply";
	}
	@RequestMapping("/deTailAtm")
	public String deTailAtm() {
		return "ajax/deTailAtm";
	}
	@RequestMapping("/deTailSpter")
	public String deTailSpter() {
		return "ajax/deTailSpter";
	}
}
