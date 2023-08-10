package account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import user.UserVO;

public class GetAccountListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("login");
		
		AccountVO vo = new AccountVO();
		vo.setId(user.getId());
		
		AccountDAO dao = new AccountDAO();
		List<AccountVO> accountList = dao.myAccountList(vo);
		
		request.setAttribute("accountList", accountList);
				
		return "/account/accountMain.jsp";
	}

}
