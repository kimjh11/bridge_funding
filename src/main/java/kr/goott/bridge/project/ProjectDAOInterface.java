package kr.goott.bridge.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.goott.bridge.deTailPage.ReplyVO;
import kr.goott.bridge.deTailPage.SptVO;

public interface ProjectDAOInterface {
	
	//프로젝트 선택
	public ProjectVO selectRecord(@Param("cateCode") String cateCode, @Param("proCode") String proCode);

	//프로젝트 날짜
	public ProjectVO selectDate(@Param("cateCode") String cateCode, @Param("proCode") String proCode);

	//프로젝트 AS/문의사항 선택
	public String deTailAs(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	
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
}
