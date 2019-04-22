package kr.goott.bridge.admin;


import java.util.List;
import java.util.ArrayList;
import kr.goott.bridge.list.BannerVO;
import kr.goott.bridge.project.CategoryVO;


import kr.goott.bridge.project.ItemVO;
import kr.goott.bridge.project.ProjectVO;
public interface AdminDaoInterface {
	//�α���
	public AdminVO login(AdminVO vo);

	//������ ����
	public ArrayList<Project2VO> selectReward(Project2VO vo);

	//���
	public List<BannerVO> bannerSelectAll();//��ü����
	public BannerVO bannerSelect(int bannerNum);//���ڵ� �Ѱ�����
	public int bannerInsert(BannerVO vo);//���
	public int bannerOpenToggle(int bannerNum);//���¿���
	public int bannerDelete(int itemNum);//����
	public void bannerUpdate(BannerVO vo);//����

	//ī�װ�
	public List<CategoryVO> cateSelectAll();//��ü����
	public void cateInsert(CategoryVO vo);//���
	public int cateOpenToggle(int itemNum);//���¿���
	public int cateDelete(int itemNum);//����
	

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
