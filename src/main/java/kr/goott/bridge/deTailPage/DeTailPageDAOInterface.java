package kr.goott.bridge.deTailPage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.goott.bridge.deTailPage.ReplyVO;
import kr.goott.bridge.deTailPage.SptVO;
import kr.goott.bridge.project.ItemVO;
import kr.goott.bridge.project.ProjectVO;

public interface DeTailPageDAOInterface {
	
	//프로젝트 선택
	public ProjectVO selectRecord(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	public ProjectVO selectRecord2(@Param("cateCode") String cateCode, @Param("proCode") String proCode);

	//프로젝트 날짜
	public ProjectVO selectDate(@Param("cateCode") String cateCode, @Param("proCode") String proCode);

	//프로젝트 AS/문의사항 선택
	public String deTailAs(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	//프로젝트 AS/문의사항 선택
	public String deTailAs2(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	
	//댓글 꺼내기
	public List<ReplyVO> replyOpen(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	
	//댓글 입력하기
	public int replyWrite(ReplyVO vo);
	
	//아이템 꺼내오기
	public List<ItemVO> itemList(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	
	//서포터즈 꺼내기
	public List<SptVO> selectSpt(@Param("proCode") String proCode);
	
	//현황판 데이터 입력하기
	public ProjectVO selectStatus(@Param("proCode") String proCode);
	
	//결제한 서포터즈 구하기
	public String payCount(@Param("proCode") String proCode);
	
	//좋아요 +1
	public int likeUp(@Param("proCode") String proCode);
	
	//좋아요 -1
	public int likeDown(@Param("proCode") String proCode);
	
	//좋아요테이블 추가
	public void likeInsert(@Param("proCode") String proCode, @Param("userMail") String userMail);
	
	//좋아요테이블 삭제
	public void likeDelete(@Param("porCode") String proCode, @Param("userMail") String userMail);
	
	//좋아요 했는지 검색
	public String likeSelect(@Param("proCode") String proCode, @Param("userMail") String userMail);


}
