package kr.goott.bridge.mypage;

import kr.goott.bridge.member.MemberVO;

public interface MypageDaoInterface {
	
	//������ (�̹���, ��ȭ��ȣ, �������, �ּ�) ���
	public int updatePro(MemberVO vo);
	
	//������ ���� ��������
	public MemberVO selectProfile(String userMail);
	
	
}
