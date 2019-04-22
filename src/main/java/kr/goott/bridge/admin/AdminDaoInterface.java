package kr.goott.bridge.admin;

import java.util.ArrayList;
import java.util.List;

import kr.goott.bridge.project.ItemVO;
import kr.goott.bridge.project.ProjectVO;

public interface AdminDaoInterface {
	//�α���
	public AdminVO login(AdminVO vo);
	
	//������ ���� - �̸����� ������(���丮, �ݵ��ȳ�) 
	public List<Project2VO> selectRecord(String cateCode, String proCode);
	
	//������ ���� - �̸����� ������(��ǰ)
	public List<Item2VO> SelectItemList(String proCode);
	
	//��������� ������ - deTailGuideReView  - ��ȯ/��ǰ
	public Project2VO selectChangeReturn(String cateCode, String proCode);
	
	//��������� ������ - deTailOpenReView - ȸ������
	public Project2VO selectCompanyInfo(String cateCode, String proCode);
	
	//����¡ �����ؼ� �׽�Ʈ
	//��  ���ڵ� ��
	public int totalRecord();
	//������ ����                                                                 ����������     1page�� ǥ���� ���ڵ� ��    ������ ���ڵ� ��         �� ������ ��
	public ArrayList<Project2VO> selectReward(int num,  int onePageRecord, int lastRecord, int totalPage, int look);
	
	//5�ܰ� - �����ϱ� ������  project, item table
	//project2 update                    (vo.getProWait()+1)                                       
	public int updatePro2(String proCode, int open,  int proDate);
	
	//project insert
	public int insertPro(ProjectVO vo);
	
	//item2 -> item insert
	public int itemInsert(ItemVO vo);
	
	//item2 delete
	public int item2Delete(Item2VO vo);
	
	//project2 delete
	public int pro2Delete(Project2VO vo);
}
