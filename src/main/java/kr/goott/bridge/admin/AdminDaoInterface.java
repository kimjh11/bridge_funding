package kr.goott.bridge.admin;


import java.util.List;
import java.util.ArrayList;
import kr.goott.bridge.list.BannerVO;
import kr.goott.bridge.project.CategoryVO;

public interface AdminDaoInterface {
	
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
	



}
