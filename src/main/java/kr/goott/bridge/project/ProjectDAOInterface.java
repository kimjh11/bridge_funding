package kr.goott.bridge.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.goott.bridge.deTailPage.ReplyVO;

public interface ProjectDAOInterface {
	
	//������Ʈ ����
	public ProjectVO selectRecord(@Param("cateCode") String cateCode, @Param("proCode") String proCode);

	//������Ʈ AS/���ǻ��� ����
	public String deTailAs(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	
	//��� ������
	public List<ReplyVO> replyOpen(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	
}
