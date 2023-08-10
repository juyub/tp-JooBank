package bankinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class BankinfoDAO {
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public List<BankinfoVO> getBankList() {
		List<BankinfoVO> bankList = new ArrayList<BankinfoVO>();
		String query = 
				" SELECT * from bankinfo";

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			while(rs.next()) {
				BankinfoVO bank = new BankinfoVO();
				bank.setBank_cd(rs.getString("bank_cd"));
				bank.setBank_name(rs.getString("bank_name"));
				bankList.add(bank);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return bankList;
	}

	public List<BankinfoVO> getObList(String userType) {
	    List<BankinfoVO> bankList = new ArrayList<BankinfoVO>();
	    String query = "SELECT * FROM bankinfo WHERE bank_cd != '204'";
	        
	    try {
	        conn = JDBCUtil.getConnection();
	        stmt = conn.prepareStatement(query);
	        rs = stmt.executeQuery();
	        while (rs.next()) {
	            BankinfoVO bank = new BankinfoVO();
	            bank.setBank_cd(rs.getString("bank_cd"));
	            bank.setBank_name(rs.getString("bank_name"));

	            boolean shouldAdd = true;
	            switch(bank.getBank_cd()) {
	                case "159":
	                    shouldAdd = !userType.contains("/q");
	                    break;
	                case "111":
	                    shouldAdd = !userType.contains("/w");
	                    break;
	                case "616":
	                    shouldAdd = !userType.contains("/e");
	                    break;
	            }

	            if (shouldAdd) {
	                bankList.add(bank);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCUtil.close(rs, stmt, conn);
	    }

	    return bankList;
	}

//	public List<BankinfoVO> getObList() {
//	    List<BankinfoVO> bankList = new ArrayList<BankinfoVO>();
//	    
//	    // 변경된 부분
//	    String query = "SELECT * FROM bankinfo WHERE bank_cd != '204'";
//
//	    try {
//	        conn = JDBCUtil.getConnection();
//	        stmt = conn.prepareStatement(query);
//	        rs = stmt.executeQuery();
//	        while (rs.next()) {
//	            BankinfoVO bank = new BankinfoVO();
//	            bank.setBank_cd(rs.getString("bank_cd"));
//	            bank.setBank_name(rs.getString("bank_name"));
//	            bankList.add(bank);
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    } finally {
//	        JDBCUtil.close(rs, stmt, conn);
//	    }
//	    return bankList;
//	}
	
}
