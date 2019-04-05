package kr.goott.bridge.project;

import org.apache.ibatis.annotations.Param;

public interface ProjectDAOInterface {
	
	//프로젝트 선택
	public ProjectVO selectRecord(@Param("cateCode") String cateCode, @Param("proCode") String proCode);

	//프로젝트 AS/문의사항 선택
	public String deTailAs(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
}
