package kr.goott.bridge.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.goott.bridge.deTailPage.ReplyVO;
import kr.goott.bridge.deTailPage.SptVO;

public interface ProjectDAOInterface {
	
	//������Ʈ ����
	public ProjectVO selectRecord(@Param("cateCode") String cateCode, @Param("proCode") String proCode);

	//������Ʈ ��¥
	public ProjectVO selectDate(@Param("cateCode") String cateCode, @Param("proCode") String proCode);

	//������Ʈ AS/���ǻ��� ����
	public String deTailAs(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	
	//��� ������
	public List<ReplyVO> replyOpen(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	
	//��� �Է��ϱ�
	public int replyWrite(ReplyVO vo);
	
	//������ ��������
	public List<ItemVO> itemList(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	
	//�������� ������
	public List<SptVO> selectSpt(@Param("proCode") String proCode);
	
	//��Ȳ�� ������ �Է��ϱ�
	public ProjectVO selectStatus(@Param("proCode") String proCode);
}
