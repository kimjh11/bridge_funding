package kr.goott.bridge;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception{
		//로그인 여부 체크
		try {
			HttpSession session = request.getSession();
			
			System.out.println(session.getAttribute("userMail"));
			System.out.println(session.getAttribute("userName"));
			System.out.println(session.getAttribute("logStatus"));
			
			boolean boo = session.getAttribute("logStatus") == null ||
						  session.getAttribute("logStatus").equals("N") ||
						  session.getAttribute("logStatus").equals("");
			if(boo) { //로그인 안된 경우
				response.sendRedirect("/bridge/loginAlert");
				return false;
			}
		}catch(Exception e) {
			System.out.println("로그인 Interceptor 에러..");
		}
		return true;
	}
	//컨트롤러 호출 후 view페이지 출력전에 호출
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			
	}
	//컨트롤러 실행 후 호출
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, 
			Exception ex) throws Exception {
			 
	}
}
