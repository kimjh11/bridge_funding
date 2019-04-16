package kr.goott.bridge.payment;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentController {
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping("/payment2")
	public String payment2() {
		return "ajax/payment2";
	}
	@RequestMapping("/payment3")
	public String payment3() {
		return "ajax/payment3";
	}
}
