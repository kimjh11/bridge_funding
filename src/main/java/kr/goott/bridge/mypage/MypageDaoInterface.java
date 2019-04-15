package kr.goott.bridge.mypage;

import kr.goott.bridge.member.MemberVO;

public interface MypageDaoInterface {
	
	//프로필 (이미지, 전화번호, 생년월일, 주소) 등록
	public int updatePro(MemberVO vo);
	
	//프로필 정보 가져오기
	public MemberVO selectProfile(String userMail);
	
	
}
