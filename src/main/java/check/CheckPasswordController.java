package check;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class CheckPasswordController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		long ac_number = Long.parseLong(request.getParameter("sendAc_number"));
        String bank_cd = request.getParameter("sendBank_cd");
        String AC_PW = request.getParameter("AC_PW");

        CheckDAO accountDao = new CheckDAO();
        boolean isPasswordCorrect = accountDao.checkAC_PW(ac_number, bank_cd, AC_PW);

        response.setContentType("text/plain");
        try {
            response.getWriter().print(isPasswordCorrect);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
	}

}
