package account;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class CreateAccountController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");
		String AC_PW = request.getParameter("AC_PW");
		int pd_number = Integer.parseInt(request.getParameter("pd_number"));
		int pd_ed_years = Integer.parseInt(request.getParameter("pd_ed_date"));
		
		AccountVO vo = new AccountVO();
		vo.setId(id);
		vo.setAC_PW(AC_PW);
		vo.setPD_NUMBER(pd_number);
		
		// pd_ed_date 값을 년수로 계산하여 설정
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, pd_ed_years);
        Date pd_ed_date = calendar.getTime();
        vo.setAC_ED_DATE(pd_ed_date);
		
		AccountDAO dao = new AccountDAO();
		dao.createAccount(vo);
		
		return "myAccountList.do";
	}

}
