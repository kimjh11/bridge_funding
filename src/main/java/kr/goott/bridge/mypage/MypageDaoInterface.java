package kr.goott.bridge.mypage;

import java.util.ArrayList;
import kr.goott.bridge.member.MemberVO;
import kr.goott.bridge.project.ProjectVO;

public interface MypageDaoInterface {
	
	//������ (�̹���, ��ȭ��ȣ, �������, �ּ�) ���
	public int updatePro(MemberVO vo);
	
	//������ ���� ��������
	public MemberVO selectProfile(String userMail);
	
	//ī�� ���� ���
	public int updateCardInfo(MemberVO vo);
	
	//ī�� ���� ����
	public int cardDelete(String userMail);
	
	//���� ������ ������Ʈ ��������
	public ArrayList<ProjectVO> selectMyReward(String userMail);
	
	//���ƿ� ������Ʈ ��������
	public ArrayList<ProjectVO> selectLike(String userMail);
	
	//�������� ������Ʈ �� ��������
	public ArrayList<ProjectVO> selectStartingPro(String userMail);
	
	//���� ����� �� ��������
	public ArrayList<ProjectVO> selectWaitingPro(String userMail);
	
	//���ƿ� ����Ʈ ��������
	public ArrayList<MemberVO> selectLikeList(String proCode);
	
	//�ݵ��� ����Ʈ ��������
	public ArrayList<MemberVO> selectfundingList(String proCode);
}
