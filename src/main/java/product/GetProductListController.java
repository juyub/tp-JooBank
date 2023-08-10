package product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class GetProductListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		ProductDAO dao= new ProductDAO();
		List<ProductVO> productList = dao.getProductList();

        request.setAttribute("productList", productList);
        
		return "/product/getProductList.jsp";
	}

}
