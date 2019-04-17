package kr.goott.bridge.deTailPage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.goott.bridge.deTailPage.ReplyVO;
import kr.goott.bridge.deTailPage.SptVO;
import kr.goott.bridge.project.ItemVO;
import kr.goott.bridge.project.ProjectVO;

public interface DeTailPageDAOInterface {
	
	//������Ʈ ����
	public ProjectVO selectRecord(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	public ProjectVO selectRecord2(@Param("cateCode") String cateCode, @Param("proCode") String proCode);

	//������Ʈ ��¥
	public ProjectVO selectDate(@Param("cateCode") String cateCode, @Param("proCode") String proCode);

	//������Ʈ AS/���ǻ��� ����
	public String deTailAs(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	//������Ʈ AS/���ǻ��� ����
	public String deTailAs2(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
	
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
	
	//������ �������� ���ϱ�
	public String payCount(@Param("proCode") String proCode);
	
	//���ƿ� +1
	public int likeUp(@Param("proCode") String proCode);
	
	//���ƿ� -1
	public int likeDown(@Param("proCode") String proCode);
	
	//���ƿ����̺� �߰�
	public void likeInsert(@Param("proCode") String proCode, @Param("userMail") String userMail);
	
	//���ƿ����̺� ����
	public void likeDelete(@Param("porCode") String proCode, @Param("userMail") String userMail);
	
	//���ƿ� �ߴ��� �˻�
	public String likeSelect(@Param("proCode") String proCode, @Param("userMail") String userMail);


}
