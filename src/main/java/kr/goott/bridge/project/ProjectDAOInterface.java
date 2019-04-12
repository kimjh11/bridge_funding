package kr.goott.bridge.project;

import java.util.List;

public interface ProjectDAOInterface {
	
	public List<CategoryVO> selectCate();
	
	public int inPro(ProjectVO vo);
	
	public ProjectVO selectPro(String userMail);
	public ProjectVO selectPro2(String userMail,String proCode);
	public ProjectVO selectPro3(String userMail,int proNum);
	
	public int savePro(ProjectVO vo);
	public int saveStory(ProjectVO vo);
	public int saveItem(ItemVO vo);
	public int saveProOk(ProjectVO vo);
	
	public List<ItemVO> selectItem(String userMail,String proCode);
	
	public int delItem(ItemVO vo);
	
	public int changePro(ProjectVO vo);
	
	public int delPro3();
}
