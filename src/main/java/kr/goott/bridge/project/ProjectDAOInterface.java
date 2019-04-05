package kr.goott.bridge.project;

import org.apache.ibatis.annotations.Param;

public interface ProjectDAOInterface {
	
	//������Ʈ ����
	public ProjectVO selectRecord(@Param("cateCode") String cateCode, @Param("proCode") String proCode);

	//������Ʈ AS/���ǻ��� ����
	public String deTailAs(@Param("cateCode") String cateCode, @Param("proCode") String proCode);
}
