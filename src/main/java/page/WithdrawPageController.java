package page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import account.AccountDAO;
import account.AccountVO;
import controller.Controller;

public class WithdrawPageController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		String ac_number = request.getParameter("ac_number");
		
//		AccountVO vo = new AccountVO();
//		vo.setAc_number(Long.parseLong(ac_number));
		
		AccountDAO dao = new AccountDAO();
//		AccountVO account = dao.getAc_number(vo);
		AccountVO account = dao.getAccount(Long.parseLong(ac_number));
		
		request.setAttribute("account", account);
		
		return "/account/withdraw.jsp";
	}
}
