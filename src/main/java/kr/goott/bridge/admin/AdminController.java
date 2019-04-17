package kr.goott.bridge.admin;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.goott.bridge.admin.AdminDaoInterface;
import kr.goott.bridge.list.BannerVO;
import kr.goott.bridge.project.CategoryVO;

@Controller
public class AdminController {
	@Autowired
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
	
	//��ʰ��� ������
	@RequestMapping(value="/adminBanner")
	public ModelAndView adminBanner() {
		ModelAndView mav = new ModelAndView();
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		
		mav.addObject("bannerList", dao.bannerSelectAll());
		mav.setViewName("/admin/banner");
		
		return mav;
	}
	
	//��� ����Ʈ ���
	@RequestMapping(value="/bannerSubmit", method=RequestMethod.POST)
	public ModelAndView bannerUpload(HttpServletRequest req, BannerVO vo,
									@RequestParam("bannerImgFile") MultipartFile bannerImgFile) {
		ModelAndView mav = new ModelAndView();
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		
		//������ ����� ������
		folder = req.getSession().getServletContext().getRealPath("/img/banner");
	
		String newFileName = null;
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
		vo.setBannerImg(newFileName);
		dao.bannerInsert(vo);
		mav.setViewName("redirect:adminBanner");
		return mav;
	}
	
	//�����ߺ�Ȯ�� �� �߰�
	public String fileUpload(MultipartFile fileName, String folder) {
		String newFileName = null;
		String originName = fileName.getOriginalFilename();
		
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
				fileName.transferTo(new File(folder, newFileName));
			}catch(Exception e) {
				System.out.println("�̹��� ���ϵ�� ����"+e.getMessage());
			}
		}
		return newFileName;
	}
	
	//���¿��� ����
	@RequestMapping(value="/openToggle", method=RequestMethod.GET)
	@ResponseBody
	public String openToggle(String itemNum, String table) {
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		System.out.println(table);
		System.out.println(itemNum);
		
		int cnt=0;
		
		if(table.equals("category")) {
			cnt = dao.cateOpenToggle(Integer.parseInt(itemNum));
		}else if(table.equals("banner")) {
			cnt = dao.bannerOpenToggle(Integer.parseInt(itemNum));
		}

		if(cnt>0) {
			return "ok";
		}else {
			return "no";
		}
	}
	
	//ī�װ� ����������
	@RequestMapping(value="/adminCategory")
	public ModelAndView adminCategory() {
		ModelAndView mav = new ModelAndView();
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		
		mav.addObject("cateList", dao.cateSelectAll());
		mav.setViewName("/admin/category");
		
		return mav;
	}
	
	@RequestMapping(value="/cateSubmit", method=RequestMethod.POST)
	public ModelAndView cateUpdate(HttpServletRequest req, CategoryVO vo,
									@RequestParam("cateImgFile") MultipartFile cateImgFile) {
		//�̹��������� ����� ������
		folder = req.getSession().getServletContext().getRealPath("/img/category");
		//���Ͼ��ε� + ���ε�� ���ϸ���
		vo.setCateImg(fileUpload(cateImgFile, folder));
		
		//������ �߰�
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		dao.cateInsert(vo);
		
		//ī�װ� ���������� �̵�
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:adminCategory");
		
		return mav;
	}
	
	
}
