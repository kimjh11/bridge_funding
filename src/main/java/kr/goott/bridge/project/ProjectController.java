package kr.goott.bridge.project;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class ProjectController {
	String folder;
	@Autowired//servlet-context�� �ִ� sqlSession��ü�� ����� �� ����
	SqlSession sqlSession;
////////////// ������Ʈ ����  ///////////////////////////////////	
	@RequestMapping("/inputProject")
	public ModelAndView inputProjectList(ProjectVO vo) {	
		ModelAndView mav = new ModelAndView();
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		
		System.out.println("��ǲ������Ʈ======================================");
		System.out.println("����:"+vo.getUserMail());
		System.out.println("������Ʈ�ڵ�:"+vo.getProCode());
		System.out.println("������Ʈ��ȣ:"+vo.getProNum());
		
		List<CategoryVO> list = dao.selectCate();
		int cnt = dao.inPro(vo);
		ProjectVO vo2 = dao.selectPro(vo.getUserMail());
		System.out.println("����:"+vo2.getUserMail());
		System.out.println("������Ʈ�ڵ�:"+vo2.getProCode());
		System.out.println("������Ʈ��ȣ:"+vo2.getProNum());
		dao.changePro(vo2);	
		
		mav.addObject("list",list);
		mav.addObject("vo",vo2);
		mav.addObject("userMail",vo.getUserMail());
		mav.setViewName("project/inputProject");
		dao.delPro3();
		return mav;
	}
////////// ������Ʈ �����ϱ�  ///////////////////////////////////////////
	@RequestMapping(value="/savePro", method=RequestMethod.POST)
	public ModelAndView savePro(HttpServletRequest req,
			@RequestParam("proImg") MultipartFile proImg,
			@RequestParam("comImg") MultipartFile comImg,
			@RequestParam("userMail") String userMail,
			@RequestParam("proCode") String proCode,
			@RequestParam("proNum") int proNum,
			@RequestParam("cateCode") String cateCode,
			@RequestParam("proName") String proName,
			@RequestParam("proGoal") int proGoal,
			@RequestParam("proDate") int proDate,
			@RequestParam("comName") String comName,
			@RequestParam("comNum") String comNum,
			@RequestParam("account") String account,
			@RequestParam("comTel") String comTel,
			@RequestParam("comEmail") String comEmail,
			@RequestParam("comSite") String comSite
			) {
		System.out.println(userMail);
		System.out.println(proCode);
		System.out.println(proNum);
		
		ModelAndView mav = new ModelAndView();
		folder = req.getSession().getServletContext().getRealPath("/ckstorage");
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		//�Ű��������ϱ�
		String fname1 = proImg.getName(); //���ϳ���
		String fname2 = comImg.getName();//���ϳ���2
							
		//���ε������ϸ� ���ϱ�
		String f1 = proImg.getOriginalFilename();
		String f2 = comImg.getOriginalFilename();
		
		System.out.println(f1+"="+f2);
		String newFilename = null;
				if(f1 != null && !f1.equals("")) {
				//���ϸ��� ������ �ִ��� Ȯ���ϰ� ������ �����̸����� ���ε�
				
				File file = new File(folder, f1);
				
					if(file.exists()) {//�����ϸ�true, ������ false
						//���ϸ����ϱ� abcd_1.png
						int cnt = 1;
						while(true) {
							//������.��ġ ���ϱ�
							int idx = f1.lastIndexOf(".");
							//���ϸ�
							String firstFile = f1.substring(0, idx);
							System.out.println("idx="+idx+firstFile);
							//Ȯ���
							String extFile = f1.substring(idx+1);
							System.out.println("idx="+idx+extFile);
							File newFile = new File(folder,firstFile+"_"+cnt+"."+extFile);
							if(!newFile.exists()) {//�����鳡����
								newFilename = newFile.getName();
								break;
							}
							cnt++;
						}				
					}else {
						newFilename= f1;
					}
					//���ε�
					try {
						
						proImg.transferTo(new File(folder,newFilename));
						
					}catch(Exception e) {
						System.out.println("���Ͼ��ε� ����"+e.getMessage());
					}
				}
				String newFilename2 = null;
				if(f1 != null && !f1.equals("")) {
				//���ϸ��� ������ �ִ��� Ȯ���ϰ� ������ �����̸����� ���ε�
				
				File file = new File(folder, f2);
				
					if(file.exists()) {//�����ϸ�true, ������ false
						//���ϸ����ϱ� abcd_1.png
						int cnt = 1;
						while(true) {
							//������.��ġ ���ϱ�
							int idx = f2.lastIndexOf(".");
							//���ϸ�
							String firstFile = f2.substring(0, idx);
							//Ȯ���
							String extFile = f2.substring(idx+1);
							File newFile = new File(folder,firstFile+"_"+cnt+"."+extFile);
							if(!newFile.exists()) {//�����鳡����
								newFilename2 = newFile.getName();
								break;
							}
							cnt++;
						}				
					}else {
						newFilename2= f2;
					}
					//���ε�
					try {
						comImg.transferTo(new File(folder,newFilename2));
					}catch(Exception e) {
						System.out.println("���Ͼ��ε� ����"+e.getMessage());
					}
				}
		System.out.println(f1+"="+f2);
		System.out.println(newFilename+"="+newFilename2);
		ProjectVO vo = new ProjectVO();
		vo.setProImg(newFilename);
		vo.setComImg(newFilename2);
		vo.setUserMail(userMail);
		vo.setProCode(proCode);
		vo.setProNum(proNum);
		vo.setCateCode(cateCode);
		vo.setProName(proName);
		vo.setProGoal(proGoal);
		vo.setProDate(proDate);
		vo.setComName(comName);
		vo.setComNum(comNum);
		vo.setAccount(account);
		vo.setComTel(comTel);
		vo.setComEmail(comEmail);
		vo.setComSite(comSite);
		int cnt = dao.savePro(vo);
		dao.delPro3();
		ProjectVO vo2 = dao.selectPro3(vo.getUserMail(),vo.getProNum());
		mav.addObject("userMail",vo2.getUserMail());
		mav.addObject("proCode",vo2.getProCode());
		mav.addObject("vo",dao.selectPro2(vo2.getUserMail(),vo2.getProCode()));
		
		mav.setViewName("redirect:inputProject2");
		return mav;
	}
//////////////���丮 �����ϱ�////////////////////////////////////////////////
	@RequestMapping("/saveStory")
	public ModelAndView saveStory(ProjectVO vo) {
		ModelAndView mav = new ModelAndView();
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		
		System.out.println("���̺꽺�丮 ======================================");
		System.out.println("����:"+vo.getUserMail());
		System.out.println("������Ʈ�ڵ�:"+vo.getProCode());
		System.out.println("������Ʈ��ȣ:"+vo.getProNum());
		
		int cnt = dao.saveStory(vo);
		dao.delPro3();
		mav.addObject("userMail",vo.getUserMail());
		mav.addObject("proCode",vo.getProCode());
		mav.addObject("vo",dao.selectPro2(vo.getUserMail(),vo.getProCode()));
		mav.setViewName("redirect:inputStory");
		
		return mav;
	}
////////////////������ �����ϱ�//////////////////////
	@RequestMapping("/saveItem")
	public ModelAndView saveItem(ProjectVO vo,ItemVO vo2) {
		ModelAndView mav = new ModelAndView();
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		
		System.out.println("���̺������ ======================================");
		System.out.println("����:"+vo.getUserMail());
		System.out.println("������Ʈ�ڵ�:"+vo.getProCode());
		System.out.println("������Ʈ��ȣ:"+vo.getProNum());
		
		int cnt = dao.saveItem(vo2);
		dao.delPro3();
		mav.addObject("userMail",vo.getUserMail());
		mav.addObject("proCode",vo.getProCode());
		mav.addObject("vo",dao.selectPro2(vo.getUserMail(),vo.getProCode()));
		mav.setViewName("redirect:inputItem");
		
		return mav;
	}
//////////////////���� Ȯ�� �����ϱ�///////////////////////////////
	@RequestMapping("/saveProOk")
	public ModelAndView saveProOk(ProjectVO vo) {
		ModelAndView mav = new ModelAndView();
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		
		System.out.println("���̺����ο����� ======================================");
		System.out.println("����:"+vo.getUserMail());
		System.out.println("������Ʈ�ڵ�:"+vo.getProCode());
		System.out.println("������Ʈ��ȣ:"+vo.getProNum());
		
		int cnt = dao.saveProOk(vo);
		dao.delPro3();
		mav.addObject("userMail",vo.getUserMail());
		mav.addObject("proCode",vo.getProCode());
		mav.addObject("vo",dao.selectPro2(vo.getUserMail(),vo.getProCode()));
		mav.setViewName("redirect:inputProOk");
		
		return mav;
	}
//////////////////// ������Ʈ �������� ������///////////////////////////////////
	@RequestMapping("/inputProject2")
	public ModelAndView inputProjectList2(ProjectVO vo) {	
		ModelAndView mav = new ModelAndView();
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		
		System.out.println("��ǲ������Ʈ======================================");
		System.out.println("����:"+vo.getUserMail());
		System.out.println("������Ʈ�ڵ�:"+vo.getProCode());
		System.out.println("������Ʈ��ȣ:"+vo.getProNum());
		
		List<CategoryVO> list = dao.selectCate();
		dao.delPro3();
		mav.addObject("list",list);
		mav.addObject("vo",dao.selectPro2(vo.getUserMail(),vo.getProCode()));
		mav.addObject("userMail",vo.getUserMail());
		mav.addObject("proCode",vo.getProCode());
		mav.setViewName("project/inputProject");
		
		return mav;
	}
//////////////////////���丮 �������� ������////////////////////////////////////////
	@RequestMapping("/inputStory")
	public ModelAndView inputStoryList(ProjectVO vo) {
		ModelAndView mav = new ModelAndView();
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		
		System.out.println("��ǲ���丮 ======================================");
		System.out.println("����:"+vo.getUserMail());
		System.out.println("������Ʈ�ڵ�:"+vo.getProCode());
		System.out.println("������Ʈ��ȣ:"+vo.getProNum());
		dao.delPro3();
		
		mav.addObject("userMail",vo.getUserMail());
		mav.addObject("proCode",vo.getProCode());
		mav.addObject("vo",dao.selectPro2(vo.getUserMail(),vo.getProCode()));
		mav.setViewName("project/inputStory");
		
		return mav;
	}
/////////////////////////������ �������� ������///////////////////////////////////////////
	@RequestMapping("/inputItem")
	public ModelAndView inputItemList(ProjectVO vo,ItemVO vo2) {
		ModelAndView mav = new ModelAndView();
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		
		System.out.println("��ǲ������ ======================================");
		System.out.println("����:"+vo.getUserMail());
		System.out.println("������Ʈ�ڵ�:"+vo.getProCode());
		System.out.println("������Ʈ��ȣ:"+vo.getProNum());
		
		List<ItemVO> list = dao.selectItem(vo.getUserMail(), vo.getProCode());
		dao.delPro3();
		
		mav.addObject("userMail",vo.getUserMail());
		mav.addObject("proCode",vo.getProCode());
		mav.addObject("vo",dao.selectPro2(vo.getUserMail(),vo.getProCode()));
		mav.addObject("list",list);
		mav.setViewName("project/inputItem");
		return mav;
	}
//////////////////////////////����Ȯ�� �������� ������///////////////////////////////////////////
	@RequestMapping("/inputProOk")
	public ModelAndView inputProOkList(ProjectVO vo) {
		ModelAndView mav = new ModelAndView();
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		
		System.out.println("��ǲ������ ======================================");
		System.out.println("����:"+vo.getUserMail());
		System.out.println("������Ʈ�ڵ�:"+vo.getProCode());
		System.out.println("������Ʈ��ȣ:"+vo.getProNum());
		dao.delPro3();
		
		mav.addObject("userMail",vo.getUserMail());
		mav.addObject("proCode",vo.getProCode());
		mav.addObject("vo",dao.selectPro2(vo.getUserMail(),vo.getProCode()));
		mav.setViewName("project/inputProOk");
		return mav;
	}
///////////////////////������ ����//////////////////////////////////
	@RequestMapping("/delItem")
	public ModelAndView delItem(ItemVO vo) {
		ModelAndView mav = new ModelAndView();
		ProjectDAOInterface dao = sqlSession.getMapper(ProjectDAOInterface.class);
		
		dao.delItem(vo);
		dao.delPro3();
		
		mav.addObject("userMail",vo.getUserMail());
		mav.addObject("proCode",vo.getProCode());
		mav.setViewName("redirect:inputItem");
		return mav;
	}
	
}
