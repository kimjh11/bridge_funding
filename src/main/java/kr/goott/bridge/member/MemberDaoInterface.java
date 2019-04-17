package kr.goott.bridge.member;

public interface MemberDaoInterface {
	//회원가입
	public int insertRecord(MemberVO vo);
	
	//로그인
	public MemberVO loginCheck(MemberVO vo);
	
	//아이디 찾기
	public MemberVO idSearch(String userMail);
	
	//새 비밀번호 변경 
	public int updatePwd(MemberVO vo);
}
