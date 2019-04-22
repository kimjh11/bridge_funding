package kr.goott.bridge.list;

import java.util.List;
import java.util.Map;

import kr.goott.bridge.project.CategoryVO;

public interface ListDaoInterface {
	//이벤트 배너
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
	public List<ListVO> rewardListSort(String sort); //리워드홈
	public List<ListVO> cateListSort(Map<String, String> map); //카테고리,기부후원

	/////////////////////////////////
	//리스트전체출력
	public List<ListVO> allRewardList();
	
	public List<ListVO> allCateList();
	
	public List<ListVO> selectCateList(String cateCode);
	
	public List<String> likeChk(String userMail);
	
	public List<ListVO> keywordSearch(String keyword);
	
	
	
}
