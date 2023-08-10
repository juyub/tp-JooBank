package openbank;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bankinfo.BankinfoDAO;
import bankinfo.BankinfoVO;
import controller.Controller;

public class ObTransferPageController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		long ac_number = Long.parseLong(request.getParameter("ac_number"));
        String bank_cd = request.getParameter("bank_cd");
		
        OpenbankDAO dao = new OpenbankDAO();
        
        OpenbankVO account = dao.getAccount(ac_number, bank_cd);
        
        request.setAttribute("account", account);        
        
        BankinfoDAO bankinfoDao = new BankinfoDAO();
		List<BankinfoVO> bankList = bankinfoDao.getBankList();
		
		request.setAttribute("bankList", bankList);
		
		return "/openbank/obTransfer.jsp";
	}

}
