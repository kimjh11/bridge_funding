package kr.goott.bridge;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception{
		//�α��� ���� üũ
		try {
			HttpSession session = request.getSession();
			
			System.out.println(session.getAttribute("userMail"));
			System.out.println(session.getAttribute("userName"));
			System.out.println(session.getAttribute("logStatus"));
			
			boolean boo = session.getAttribute("logStatus") == null ||
						  session.getAttribute("logStatus").equals("N") ||
						  session.getAttribute("logStatus").equals("");
			if(boo) { //�α��� �ȵ� ���
				response.sendRedirect("/bridge/loginAlert");
				return false;
			}
		}catch(Exception e) {
			System.out.println("�α��� Interceptor ����..");
		}
		return true;
	}
	//��Ʈ�ѷ� ȣ�� �� view������ ������� ȣ��
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			
	}
	//��Ʈ�ѷ� ���� �� ȣ��
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, 
			Exception ex) throws Exception {
			 
	}
}
