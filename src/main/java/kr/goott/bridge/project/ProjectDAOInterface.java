package kr.goott.bridge.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.goott.bridge.deTailPage.ReplyVO;

public interface ProjectDAOInterface {
	
	//프로젝트 선택
	public ProjectVO selectRecord(@Param("cateCode") String cateCode, @Param("proCode") String proCode);

	//프로젝트 AS/문의사항 선택
	public String deTailAs(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	
	//댓글 꺼내기
	public List<ReplyVO> replyOpen(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	
}
