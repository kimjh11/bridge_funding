package kr.goott.bridge.admin;

import java.util.List;

import kr.goott.bridge.list.BannerVO;
import kr.goott.bridge.project.CategoryVO;

public interface AdminDaoInterface {
	//배너 리스트
	public List<BannerVO> bannerSelectAll();
	//배너 등록
	public int bannerInsert(BannerVO vo);
	//공개여부 설정
	public int bannerOpenToggle(int bannerNum);
	public int cateOpenToggle(int itemNum);
	//카테고리 리스트
	public List<CategoryVO> cateSelectAll();
	//카테고리 등록
	public void cateInsert(CategoryVO vo);
}
