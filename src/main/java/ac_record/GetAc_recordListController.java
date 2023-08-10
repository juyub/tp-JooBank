package ac_record;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jdi.request.InvalidRequestStateException;

import account.AccountDAO;
import account.AccountVO;
import controller.Controller;

public class GetAc_recordListController implements Controller {

	private static final int pageSize = 10; // 페이지당 보여줄 레코드 수

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

		long ac_number = Long.parseLong(request.getParameter("ac_number"));
		
		AccountDAO accountDao = new AccountDAO();
		AccountVO account = accountDao.getAccount(ac_number);
		
		request.setAttribute("account", account);

		
		int pageNo;
		String pageNoParam = request.getParameter("pageNo");
		if (pageNoParam != null) {
			pageNo = Integer.parseInt(pageNoParam);
		} else {
			pageNo = 1; // 기본값으로 1을 설정
		}

		Ac_recordDAO dao = new Ac_recordDAO();
		int totalRecordCount = dao.getAc_recordCount(ac_number); // 총 레코드 수를 가져옴
//		int totalPageCount = (int) Math.ceil((double) totalRecordCount / pageSize); // 총 페이지 수 계산
		int totalPageCount = (totalRecordCount + pageSize - 1) / pageSize; // 총 페이지 수 계산


		List<Ac_recordVO> recordList = dao.getAc_recordList(ac_number, pageNo, pageSize);

		request.setAttribute("ac_number", ac_number);
		request.setAttribute("recordList", recordList);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("currentPage", pageNo);

		return "/account/getAc_recordList.jsp";
	}

}
