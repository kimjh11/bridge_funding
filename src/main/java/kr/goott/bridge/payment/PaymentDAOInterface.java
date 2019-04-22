package kr.goott.bridge.payment;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.goott.bridge.member.MemberVO;
import kr.goott.bridge.project.ItemVO;

public interface PaymentDAOInterface {
	
	//펀딩하기 들어갈때 뽑는 데이터
	public List<ItemVO> selectItem(@Param("proCode") String proCode);
	
	//배송 마지막날 뽑아주기
	public String endDay(@Param("proCode") String proCode);

	//배송 시작날 뽑아주기
	public String startDelivery(@Param("proCode") String proCode);
	
	//기존 배송지 뽑기
	public MemberVO baseInformation(@Param("userMail") String userMail);
	
	//결제정보 입력
	public int payConfirm(HashMap<String, Integer> map);
	
	//결제시 남은수량 차감
	public void sold(@Param("itemNum")int itemNum, @Param("selectCount")String selectCount);
	
	//결제시 판매액 상승
	public int proNowUp(@Param("itemPrice")int itemPrice, @Param("proCode")String proCode, @Param("selectCount")String selectCount );
	
}
