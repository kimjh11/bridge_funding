package kr.goott.bridge.payment;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.goott.bridge.member.MemberVO;
import kr.goott.bridge.project.ItemVO;

public interface PaymentDAOInterface {
	
	//�ݵ��ϱ� ���� �̴� ������
	public List<ItemVO> selectItem(@Param("proCode") String proCode);
	
	//��� �������� �̾��ֱ�
	public String endDay(@Param("proCode") String proCode);

	//��� ���۳� �̾��ֱ�
	public String startDelivery(@Param("proCode") String proCode);
	
	//���� ����� �̱�
	public MemberVO baseInformation(@Param("userMail") String userMail);
	
	//�������� �Է�
	public int payConfirm(HashMap<String, Integer> map);
	
	//������ �������� ����
	public void sold(@Param("itemNum")int itemNum, @Param("selectCount")String selectCount);
	
	//������ �Ǹž� ���
	public int proNowUp(@Param("itemPrice")int itemPrice, @Param("proCode")String proCode, @Param("selectCount")String selectCount );
	
}
