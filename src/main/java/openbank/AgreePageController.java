package openbank;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bankinfo.BankinfoDAO;
import bankinfo.BankinfoVO;
import controller.Controller;
import user.UserDAO;

public class AgreePageController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		
		request.setAttribute("id", id);

		UserDAO userDao = new UserDAO();
		String userType = userDao.getUserType(id);
		
		BankinfoDAO bankinfoDao = new BankinfoDAO();
		 // 사용자의 user_type 가져오기
		List<BankinfoVO> bankList = bankinfoDao.getObList(userType);
		
		request.setAttribute("bankList", bankList);

		return "/openbank/agree.jsp";
	}

}
