package kr.goott.bridge.admin;

import java.util.ArrayList;
import java.util.List;

import kr.goott.bridge.project.ItemVO;
import kr.goott.bridge.project.ProjectVO;

public interface AdminDaoInterface {
	//로그인
	public AdminVO login(AdminVO vo);
	
	//리워드 관리 - 미리보기 페이지(스토리, 펀딩안내) 
	public List<Project2VO> selectRecord(String cateCode, String proCode);
	
	//리워드 관리 - 미리보기 페이지(상품)
	public List<Item2VO> SelectItemList(String proCode);
	
	//리워드관리 페이지 - deTailGuideReView  - 교환/반품
	public Project2VO selectChangeReturn(String cateCode, String proCode);
	
	//리워드관리 페이지 - deTailOpenReView - 회사정보
	public Project2VO selectCompanyInfo(String cateCode, String proCode);
	
	//페이징 관련해서 테스트
	//총  레코드 수
	public int totalRecord();
	//리워드 관리                                                                 현재페이지     1page에 표시할 레코드 수    마지막 레코드 수         총 페이지 수
	public ArrayList<Project2VO> selectReward(int num,  int onePageRecord, int lastRecord, int totalPage, int look);
	
	//5단계 - 승인하기 누르면  project, item table
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
