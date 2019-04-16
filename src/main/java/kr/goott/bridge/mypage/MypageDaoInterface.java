package kr.goott.bridge.mypage;

import java.util.ArrayList;
import kr.goott.bridge.member.MemberVO;
import kr.goott.bridge.project.ProjectVO;

public interface MypageDaoInterface {
	
	//프로필 (이미지, 전화번호, 생년월일, 주소) 등록
	public int updatePro(MemberVO vo);
	
	//프로필 정보 가져오기
	public MemberVO selectProfile(String userMail);
	
	//카드 정보 등록
	public int updateCardInfo(MemberVO vo);
	
	//카드 정보 삭제
	public int cardDelete(String userMail);
	
	//나의 리워드 프로젝트 가져오기
	public ArrayList<ProjectVO> selectMyReward(String userMail);
	
	//좋아요 프로젝트 가져오기
	public ArrayList<ProjectVO> selectLike(String userMail);
	
	//진행중인 프로젝트 값 가져오기
	public ArrayList<ProjectVO> selectStartingPro(String userMail);
	
	//승인 대기중 값 가져오기
	public ArrayList<ProjectVO> selectWaitingPro(String userMail);
	
	//좋아요 리스트 가져오기
	public ArrayList<MemberVO> selectLikeList(String proCode);
	
	//펀딩한 리스트 가져오기
	public ArrayList<MemberVO> selectfundingList(String proCode);
}
