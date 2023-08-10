package account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class WithdrawController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		long ac_number = Long.parseLong(request.getParameter("ac_number"));
        long withdrawAmount = Long.parseLong(request.getParameter("withdrawAmount"));
        String bank_cd = request.getParameter("bank_cd");
        String rc_text = request.getParameter("rc_text");

        AccountDAO dao = new AccountDAO();
        int result = dao.withdraw(ac_number, bank_cd, rc_text, withdrawAmount);

        if(result > 0) {
            return "redirect:/JooBank/myAccountList.do";
        } else {
            // 입금 처리 중 오류가 발생한 경우 적절한 에러 메시지를 사용자에게 보여줍니다.
            request.setAttribute("errorMessage", "입금 처리 중 오류가 발생했습니다. 다시 시도해 주세요.");
            return "/error.jsp";
        }
	}
}
