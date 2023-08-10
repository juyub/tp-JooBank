package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class JoinController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String usercode = request.getParameter("usercode");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String postcode = request.getParameter("postcode");
		String address = request.getParameter("address");
		
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		vo.setName(name);
		vo.setUsercode(usercode);
		vo.setPhone(phone);
		vo.setEmail(email);
		vo.setPostcode(postcode);
		vo.setAddress(address);
		
		UserDAO dao = new UserDAO();
		dao.addUser(vo);
		
		return "index.jsp";
	}

}
