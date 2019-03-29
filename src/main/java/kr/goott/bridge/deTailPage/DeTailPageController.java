package kr.goott.bridge.deTailPage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeTailPageController {
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping("/deTailPage")
	public String deTailPage() {
		return "deTail/deTailPage";
	}
}
