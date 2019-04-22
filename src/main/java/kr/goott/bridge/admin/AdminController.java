package kr.goott.bridge.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.goott.bridge.list.BannerVO;
import kr.goott.bridge.list.ListDaoInterface;
import kr.goott.bridge.project.ItemVO;
import kr.goott.bridge.project.ProjectVO;

@Controller
public class AdminController {
	@Autowired
	SqlSession sqlSession;
	
	//Ʈ����� ����
	@Autowired
	PlatformTransactionManager transactionManager;
	
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
	//���� �α���
	@RequestMapping("/adminloginOk")
	public ModelAndView adminloginOk(AdminVO vo, HttpServletRequest request) {
		
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		AdminVO vo2 = dao.login(vo);
		
		ModelAndView mav = new ModelAndView(); 
		if(vo2 != null) {
			HttpSession session = request.getSession();
			vo2.setAdminloginCheck("Y");
			
			session.setAttribute("adminloginCheck", vo2.getAdminloginCheck()); //�α��� ����
			session.setAttribute("adminId", vo2.getAdminId());
			
			mav.setViewName("redirect:adminReward");
		}else {
			mav.setViewName("redirect:/admin");
		}
		
		return mav;
	}
	
	//���� �α׾ƿ�
	@RequestMapping("/adminlogout")
	public String adminlogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "admin/main";
	}
	
	//��������� ������ - project2 ����Ʈ�� ��� / ����¡
	@RequestMapping(value="/adminReward")
	public ModelAndView adminReward(Project2VO vo, HttpServletRequest req) {
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		
		//���� ������
		String numStr = req.getParameter("num");
		if(numStr == null){
			vo.setNum(1);
		}else{
			vo.setNum(Integer.parseInt(numStr));
		}
			
		//�� ���ڵ� ��
		vo.setTotalRecord(dao.totalRecord());
		
		//����¡ ���ϱ�
		ArrayList<Project2VO> list = dao.selectReward(vo.getNum(), vo.getOnePageRecord(), vo.getLastRecord(), vo.getTotalPage(), 
													  (vo.getNum()*vo.getOnePageRecord()));
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("vo", vo);
		mav.addObject("list", list);
		mav.setViewName("/admin/reward");
		
		System.out.println("������������="+vo.getLastRecord());
		System.out.println("��Ż���ڵ�="+vo.getTotalRecord());
		System.out.println("����������="+vo.getStartPage());
		System.out.println("���俩��="+vo.getWriteFinish());
		
		return mav;
	}
	
	//��������� ������ - detail������ �̸����� 
	@RequestMapping(value="/adminDetailPage")
	public ModelAndView adminDetailPage(Project2VO vo, Item2VO itemvo){
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		
		List<Project2VO> vo2 =dao.selectRecord(vo.getCateCode(),vo.getProCode()); //���丮,�ݵ��ȳ�
		List<Item2VO> list = dao.SelectItemList(itemvo.getProCode()); //��ǰ
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo2", vo2.get(0));
		mav.addObject("listItem", list);
		mav.setViewName("admin/deTailPageReView"); // �������� - �̸�����
		
		return mav;
	}
	
	//��������� ������ - deTailGuideReView  - ��ȯ/��ǰ
	@RequestMapping(value="/deTailGuideReView")
	public ModelAndView deTailGuideReView(Project2VO vo) {
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		vo = dao.selectChangeReturn(vo.getCateCode(), vo.getProCode());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("admin_ajax/deTailGuideReView");
		
		return mav;
	}
	
	//��������� ������ - deTailOpenReView - ȸ������
	@RequestMapping(value="/deTailOpenReView")
	public ModelAndView deTailOpenReView(Project2VO vo) {
		AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
		vo = dao.selectCompanyInfo(vo.getCateCode(),vo.getProCode());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("admin_ajax/deTailOpenReView");
		
		return mav;
	}
	
	//������Ʈ �����ϱ� - pro2 update (��¥���)
	@RequestMapping(value="/projectOk")
	public ModelAndView projectOk(Project2VO vo) {
		//Ʈ�����
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		ModelAndView mav = new ModelAndView();
		
		try {
			String proCode = vo.getProCode();
			int proDate = vo.getProDate();
			
			AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
			//                                        proOpen                  
			int cnt  = dao.updatePro2(proCode, (vo.getProWait()+1), proDate);
			
			if(cnt>0){
				mav.addObject("cateCode", vo.getCateCode());//ī�װ� �ڵ�
				mav.addObject("proCode", vo.getProCode());// ������Ʈ �ڵ�
				mav.setViewName("redirect:insertPro");
			}else {
				mav.setViewName("redirect:adminDetailPage");
			}
			
			//�������Ǿ�����
			transactionManager.commit(status);
		
		}catch(Exception e){
			//���������� �����߻���
			transactionManager.rollback(status);
		}
		return mav;
	}
	
	//������Ʈ �����ϱ� - pro insert (ī�װ� �̸� ����)
	@RequestMapping(value="/insertPro")
	public ModelAndView insertPro(ProjectVO vo) {
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		ModelAndView mav = new ModelAndView();
		
		try {
			AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
			
			int cnt = dao.insertPro(vo);
			
			if(cnt>0) {
				mav.addObject("proCode", vo.getProCode());
				mav.setViewName("redirect:itemInsert");
			}else {
				mav.setViewName("redirect:adminDetailPage");
			}
			
			//�������Ǿ�����
			transactionManager.commit(status);
		}catch(Exception e) {
			//���������� �����߻���
			transactionManager.rollback(status);
		}
		return mav;
	}
	
	//������Ʈ �����ϱ� - item2->item insert
 	@RequestMapping("/itemInsert")
 	public ModelAndView itemInsert(ItemVO vo) {
 		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		ModelAndView mav = new ModelAndView();	
		try {
			AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
			int cnt = dao.itemInsert(vo);
			
			if(cnt>0) {
				mav.addObject("proCode", vo.getProCode());
				mav.setViewName("redirect:item2Delete");
			}else {
				mav.setViewName("redirect:adminDetailPage");
			}
			//�������Ǿ�����
			transactionManager.commit(status);
		}catch (Exception e) {
			//���������� �����߻���
			transactionManager.rollback(status);
		}
 		return mav;
 	}
	
 	//������Ʈ �����ϱ� - item2 ����
 	@RequestMapping("/item2Delete")
 	public ModelAndView item2Delete(Item2VO vo) {
 		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		ModelAndView mav = new ModelAndView();	
 		
		try {
			AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
			int cnt = dao.item2Delete(vo);
			
			if(cnt>0) {
				mav.addObject("proCode", vo.getProCode());
				mav.setViewName("redirect:pro2Delete");
			}else {
				mav.setViewName("redirect:adminDetailPage");
			}
			
			//�������Ǿ�����
			transactionManager.commit(status);
		}catch (Exception e) {
			//���������� �����߻���
			transactionManager.rollback(status);
		}
		return mav;
 	}
	
 	//������Ʈ �����ϱ� - pro2 ����
 	 	@RequestMapping("/pro2Delete")
 	 	public ModelAndView pro2Delete(Project2VO vo) {
 	 		TransactionDefinition definition = new DefaultTransactionDefinition();
 			TransactionStatus status = transactionManager.getTransaction(definition);
 			ModelAndView mav = new ModelAndView();	
 	 		
 			try {
 				AdminDaoInterface dao = sqlSession.getMapper(AdminDaoInterface.class);
 				int cnt = dao.pro2Delete(vo);
 				
 				if(cnt>0) {
 					
 					mav.setViewName("redirect:adminReward");
 				}else {
 					mav.setViewName("redirect:adminDetailPage");
 				}
 				
 				//�������Ǿ�����
 				transactionManager.commit(status);
 			}catch (Exception e) {
 				//���������� �����߻���
 				transactionManager.rollback(status);
 			}
 			
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
