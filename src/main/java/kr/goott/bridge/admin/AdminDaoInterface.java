package kr.goott.bridge.admin;


import java.util.List;
import java.util.ArrayList;
import kr.goott.bridge.list.BannerVO;
import kr.goott.bridge.project.CategoryVO;

public interface AdminDaoInterface {
	
	//리워드 관리
	public ArrayList<Project2VO> selectReward(Project2VO vo);

	//배너
	public List<BannerVO> bannerSelectAll();//전체선택
	public BannerVO bannerSelect(int bannerNum);//레코드 한개선택
	public int bannerInsert(BannerVO vo);//등록
	public int bannerOpenToggle(int bannerNum);//오픈여부
	public int bannerDelete(int itemNum);//삭제
	public void bannerUpdate(BannerVO vo);//수정

	//카테고리
	public List<CategoryVO> cateSelectAll();//전체선택
	public void cateInsert(CategoryVO vo);//등록
	public int cateOpenToggle(int itemNum);//오픈여부
	public int cateDelete(int itemNum);//삭제
	



}
