package kr.goott.bridge.project;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import kr.goott.bridge.deTailPage.ReplyVO;
import kr.goott.bridge.deTailPage.SptVO;


public interface ProjectDAOInterface {
	

	public List<CategoryVO> selectCate();
	
	public int inPro(ProjectVO vo);
	
	public ProjectVO selectPro(String userMail);
	public ProjectVO selectPro2(String userMail,String proCode);
	public ProjectVO selectPro3(String userMail,int proNum);
	
	public int savePro(ProjectVO vo);
	public int saveStory(ProjectVO vo);
	public int saveItem(ItemVO vo);
	public int saveProOk(ProjectVO vo);
	
	public List<ItemVO> selectItem(String userMail,String proCode);
	
	public int delItem(ItemVO vo);
	
	public int changePro(ProjectVO vo);
	
	public int delPro3();
	
	
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
