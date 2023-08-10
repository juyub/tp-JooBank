package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	
	private HandlerMapping mappings;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// ServletConfig config 가 xml의 param-value를 가져 옴
		
		String propLoc = config.getInitParameter("propLoc");
		
		System.out.println(propLoc);
		
		mappings = new HandlerMapping(propLoc);
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); // 이 부분을 시험적으로 추가
		
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		uri = uri.substring(request.getContextPath().length());
		System.out.println("substring uri : " + uri);
		
		// HandlerMapping에서 /login.do를 묻고 LoginController 을 받아옴
		Controller ctrl = mappings.getController(uri);		
		// Controller 호출
		String callPage = ctrl.handleRequest(request, response); 		
		
		System.out.println("jsp : " + callPage);
		
	    if (callPage != null) {
	        if(callPage.startsWith("redirect:")) {
	            response.sendRedirect(callPage.substring("redirect:".length()));
	        } else {
	            RequestDispatcher dispatcher = request.getRequestDispatcher(callPage);
	            dispatcher.forward(request, response);
	        }
	    }
		
//		if(callPage.startsWith("redirect:")) {
//			response.sendRedirect(callPage.substring("redirect:".length()));
//		} else {
//			RequestDispatcher dispatcher = request.getRequestDispatcher(callPage);
//			dispatcher.forward(request, response);
//		}
		
	}

}
