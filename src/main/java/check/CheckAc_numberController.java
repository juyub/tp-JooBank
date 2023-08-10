package check;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class CheckAc_numberController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		long receivAc_number = Long.parseLong(request.getParameter("receivAc_number"));
		String selected_bank_cd = request.getParameter("selected_bank_cd");
		
		CheckDAO dao = new CheckDAO();
		
		boolean account = dao.CheckAc_number(receivAc_number, selected_bank_cd);
		
//		System.out.println(account);
		
		response.setContentType("text/plain");
		
		try {
            response.getWriter().print(account);
        } catch (Exception e) {
            e.printStackTrace();
        }

		return null;
	}

}
