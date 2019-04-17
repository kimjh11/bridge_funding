package kr.goott.bridge.admin;

import java.util.List;

import kr.goott.bridge.list.BannerVO;
import kr.goott.bridge.project.CategoryVO;

public interface AdminDaoInterface {
	//��� ����Ʈ
	public List<BannerVO> bannerSelectAll();
	//��� ���
	public int bannerInsert(BannerVO vo);
	//�������� ����
	public int bannerOpenToggle(int bannerNum);
	public int cateOpenToggle(int itemNum);
	//ī�װ� ����Ʈ
	public List<CategoryVO> cateSelectAll();
	//ī�װ� ���
	public void cateInsert(CategoryVO vo);
}
