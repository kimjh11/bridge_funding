package kr.goott.bridge.admin;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.goott.bridge.list.BannerVO;
import kr.goott.bridge.list.ListDaoInterface;

@Controller
public class AdminController {
	SqlSession sqlSession;
	
	String folder;
	public JdbcTemplate getTemplate() {
		return template;
	}
	public JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	@RequestMapping(value="/admin")
	public String admin() {
		return "/admin/main";
	}
	
	/////////////////////////////////////////
	//��������� ������
	@RequestMapping("/adminReward")
	public ModelAndView adminReward(Project2VO vo) {
		System.out.println("������");
		ModelAndView mav = new ModelAndView();
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		
		System.out.println("���� ������="+toString());
		
		ArrayList<Project2VO> list = dao.selectReward(vo);
		
		System.out.println("���� ������="+toString());
		
		mav.addObject("list", list);
		mav.setViewName("/admin/reward");
		
		return mav;
	}
	/////////////////////////////////////////
	
	@RequestMapping(value="/adminBanner")
	public String adminBanner() {
		return "/admin/banner";
	}
	
	@RequestMapping(value="/bannerSubmit", method=RequestMethod.POST)
	public ModelAndView bannerUpload(HttpServletRequest req, BannerVO vo,
									@RequestParam("bannerImgFile") MultipartFile bannerImgFile) {
		ModelAndView mav = new ModelAndView();
		ListDaoInterface dao = sqlSession.getMapper(ListDaoInterface.class);
		
		//������ ����� ������
		folder = req.getSession().getServletContext().getRealPath("/upload");
	
		String newFileName = null;
		
		if(bannerImgFile!=null) {
			String originName = bannerImgFile.getOriginalFilename();
			
			if(originName != null && !originName.equals("")) {
				//������ ������ �������� Ȯ�� �� ���ε�
				File file = new File(folder, originName);
				if(file.exists()) {//������ ������ �������
					int cnt=1;
					while(true) {
						int dot = originName.lastIndexOf(".");//������.��ġ
						String preFile = originName.substring(0, dot);//���ϸ�
						String extFile = originName.substring(dot+1);//Ȯ����
						File newFile = new File(folder, preFile+"_"+cnt+"."+extFile);
						if(!newFile.exists()) {
							newFileName = newFile.getName();
							break;
						}
						cnt++;
					}
				}else {//������ ������ �������
					newFileName = originName;
				}
				//���ε�
				try {
					bannerImgFile.transferTo(new File(folder, newFileName));
				}catch(Exception e) {
					System.out.println("��� �̹��� ���ϵ�� ����"+e.getMessage());
				}
			}
		}
		vo.setBannerImg(newFileName);
		mav.addObject("cnt",dao.bannerInsert(vo));
		mav.setViewName("/admin/main");
		return mav;
	}
}
