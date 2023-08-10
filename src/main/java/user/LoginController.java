package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;


public class LoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);

		UserDAO dao = new UserDAO();
		UserVO user = dao.getUser(vo);

		if (user != null) {
			HttpSession session = request.getSession();
//			HttpSession session2 = request.getSession();
//			session.setAttribute("user", user);
			session.setAttribute("login", user);
			request.setAttribute("user", user);
			return "index.jsp";
		} else {
			request.setAttribute("loginFailed", true);
			return "JooBank/user/login.jsp";
		}

	}
}
