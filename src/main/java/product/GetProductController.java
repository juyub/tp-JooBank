package product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class GetProductController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		int pd_number = Integer.parseInt(request.getParameter("pd_number"));
		
		ProductDAO dao = new ProductDAO();
		ProductVO product = dao.getProduct(pd_number);
		
		request.setAttribute("product", product);
		
		return "/product/getProduct.jsp";
	}

}
