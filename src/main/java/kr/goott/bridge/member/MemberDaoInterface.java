package kr.goott.bridge.member;

public interface MemberDaoInterface {
	//ȸ������
	public int insertRecord(MemberVO vo);
	
	//�α���
	public MemberVO loginCheck(MemberVO vo);
	
	//���̵� ã��
	public MemberVO idSearch(String userMail);
	
	//�� ��й�ȣ ���� 
	public int updatePwd(MemberVO vo);
}
