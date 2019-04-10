package kr.goott.bridge.list;

import java.util.List;

import kr.goott.bridge.project.CategoryVO;

public interface ListDaoInterface {
	//배너
	public BannerVO bannerSelect(int menuIndex);
	//카테고리
	public CategoryVO categorySelect();
	
	//리워드 홈 리스트
	public List<ListVO> rewardHomeList();
	//카테고리 홈 리스트
	public List<ListVO> cateHomeList();
	//오픈예정 홈 리스트
	public List<ListVO> commingSoonList();
	//기부후원  홈 리스트
	public List<ListVO> donationList();
	
	//선택한 카테고리 리스트
	public List<ListVO> selectCateList();
	
	//검색 리스트
	public ListVO searchSelect(String searchKeyWord);
	//목록 정렬(좋아요/펀딩액)
	public ListVO sortingSelect(String sortKeyWord);
}
