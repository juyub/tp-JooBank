package openbank;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import user.UserDAO;
import user.UserVO;

public class DisAgreeController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");
		String bank_cd = request.getParameter("bank_cd");
		
		UserDAO dao = new UserDAO();
		dao.disAgree(id, bank_cd);
		
	    UserVO user = dao.getUser(id);

	    // 세션의 login 속성 업데이트
	    HttpSession session = request.getSession();
	    session.setAttribute("login", user);
       
		return "redirect:/JooBank/obAcList.do";
	}

}
