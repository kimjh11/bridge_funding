package kr.goott.bridge.deTailPage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeTailPageController {
	@Autowired
	SqlSession sqlSession;
	
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
