package openbank;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import user.UserVO;

public class GetObAcListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("login");
		
		OpenbankVO vo = new OpenbankVO();
		vo.setId(user.getId());
		
		OpenbankDAO dao = new OpenbankDAO();
		List<OpenbankVO> nsAcList = dao.nsAcList(vo);
		List<OpenbankVO> bhAcList = dao.bhAcList(vo);
		List<OpenbankVO> plAcList = dao.plAcList(vo);
		
		request.setAttribute("nsAcList", nsAcList);
		request.setAttribute("bhAcList", bhAcList);
		request.setAttribute("plAcList", plAcList);
				
		return "/openbank/openbankMain.jsp";
	}

}
