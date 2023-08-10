package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import account.AccountVO;
import board.BoardVO;
import comment.CommentVO;
import common.JDBCUtil;

public class ProductDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	// 상품 목록 조회
	public List<ProductVO> getProductList() {
		List<ProductVO> productList = new ArrayList<ProductVO>();
		String query = " select * from product ";

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);

			rs = stmt.executeQuery();
			while (rs.next()) {
				ProductVO product = new ProductVO();
				product.setPd_number(rs.getInt("pd_number"));
				product.setPd_name(rs.getString("pd_name"));
				product.setPd_content(rs.getString("pd_content"));
				product.setPd_ed_date(rs.getInt("pd_ed_date"));
				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return productList;
	}

	// 상품 등록
	public void addProduct(ProductVO vo) {
		try {	
			String query = " INSERT INTO product (PD_NUMBER, pd_name, pd_content, pd_ed_date) "
					+ " VALUES (?, ?, ?, ?) ";
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, vo.getPd_number());
			stmt.setString(2, vo.getPd_name());
			stmt.setString(3, vo.getPd_content());
			stmt.setInt(4, vo.getPd_ed_date());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}	
	
	// 상품 상세보기
	public ProductVO getProduct(int PD_NUMBER) {
		ProductVO product = null;
		try {	 
			String query = 
					" select * from product "
					+ " where PD_NUMBER = ? ";
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, PD_NUMBER);
			rs = stmt.executeQuery();
			while(rs.next()) {
				product = new ProductVO();
				product.setPd_number(rs.getInt("pd_number"));
				product.setPd_name(rs.getString("pd_name"));
				product.setPd_content(rs.getString("pd_content"));
				product.setPd_ed_date(rs.getInt("pd_ed_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return product;
	}

	// 상품 수정
	public void updateProduct(ProductVO vo) {
		try {	 
			String query = 
					" update product set pd_name = ?, pd_content = ?, pd_ed_date = ? "
					+ " where pd_number = ? ";
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, vo.getPd_name());
			stmt.setString(2, vo.getPd_content());
			stmt.setInt(3, vo.getPd_ed_date());
			stmt.setInt(4, vo.getPd_number());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}		
	}	
	
	// 상품 가입 조회
	
}



























