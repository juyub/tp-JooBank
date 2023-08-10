package openbank;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class ObTransferController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		long sendAc_number = Long.parseLong(request.getParameter("sendAc_number"));
        long receivAc_number = Long.parseLong(request.getParameter("receivAc_number"));
        long transferAmount = Long.parseLong(request.getParameter("transferAmount"));
        String sendBank_cd = request.getParameter("sendBank_cd");
        String receivBank_cd = request.getParameter("receivBank_cd");
        String rc_text = request.getParameter("rc_text");

        System.out.println(receivBank_cd);
        System.out.println(receivAc_number);
        
        OpenbankDAO openbankDAO = new OpenbankDAO();
        int result = openbankDAO.transfer(sendAc_number, sendBank_cd, receivAc_number, receivBank_cd, transferAmount,rc_text);

        if (result > 0) {
            request.setAttribute("message", "Transfer successful!");
        } else {
            request.setAttribute("message", "Transfer failed!");
        }

        return "redirect:/JooBank/obAcList.do";
    }

}
