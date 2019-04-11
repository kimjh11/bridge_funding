package kr.goott.bridge.list;

import java.util.List;

import kr.goott.bridge.project.CategoryVO;

public interface ListDaoInterface {
	//배너
	//public List<BannerVO> bannerSelect(int menuIndex);
	public List<BannerVO> bannerSelect(String page);
	
	//카테고리 배너 리스트
	public List<CategoryVO> categorySelect();
	
	//리워드&카테고리 홈 리스트
	public List<ListVO> rewardHomeList();
	//오픈예정 홈 리스트
	public List<ListVO> commingSoonList();
	//기부후원  홈 리스트
	public List<ListVO> donationList();

	
	//카테고리별 아이템 리스트
	public List<ListVO> cateNameList(String cateName);
	//검색 리스트
	public ListVO searchSelect(String searchKeyWord);
	//목록 정렬(좋아요/펀딩액)
	public ListVO sortingSelect(String sortKeyWord);
}
