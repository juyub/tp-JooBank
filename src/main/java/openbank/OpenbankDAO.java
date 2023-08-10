package openbank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class OpenbankDAO {
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	// 성혁
	public List<OpenbankVO> nsAcList(OpenbankVO vo) {
		List<OpenbankVO> accountList = new ArrayList<OpenbankVO>();
		String query = 
				" SELECT c.accnum, c.id, ui.NAME, c.AC_NAME, c.AC_MONEY, c.AC_OP_DATE, c.AC_ED_DATE, c.bank_cd, c.bank_name, d.pd_name "
				+ " FROM ("
				 +  " SELECT a.accnum, a.id, a.AC_NAME, a.AC_MONEY, a.AC_OP_DATE, a.AC_ED_DATE, a.PD_NUMBER, a.bank_cd, b.bank_name "
				 +   " FROM account@XE@shBank a "
				 +  " JOIN bankinfo b ON b.bank_cd = a.bank_cd "
			    + " ) c "
				+ " JOIN product@XE@shBank d ON d.PD_NUMBER = c.PD_NUMBER "
				+ " JOIN USER_INFO@XE@shBank ui ON ui.ID = c.id "
				+ " WHERE c.id = ? ORDER BY c.AC_OP_DATE DESC ";

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, vo.getId());
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				OpenbankVO account = new OpenbankVO();
				account.setAc_number(rs.getLong("accnum"));
				account.setId(rs.getString("id"));
				account.setAC_NAME(rs.getString("AC_NAME"));
				account.setAC_MONEY(rs.getLong("AC_MONEY"));
				account.setAC_OP_DATE(rs.getDate("AC_OP_DATE"));
				account.setAC_ED_DATE(rs.getDate("AC_ED_DATE"));
				account.setBank_cd(rs.getString("bank_cd"));
				account.setBank_name(rs.getString("bank_name"));
				account.setPd_name(rs.getString("pd_name"));
				account.setName(rs.getString("name"));
				accountList.add(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return accountList;
	}
	
	// 보형
	public List<OpenbankVO> bhAcList(OpenbankVO vo) {
		List<OpenbankVO> accountList = new ArrayList<OpenbankVO>();
		String query = 
				" SELECT c.Ac_number, c.id, ui.name, c.AC_NAME, c.AC_MONEY, c.AC_OP_DATE, c.AC_ED_DATE, c.bank_cd, c.bank_name "
				+ "	FROM ( "
				+ "	SELECT a.Ac_number, a.id, a.AC_NAME, a.AC_MONEY, a.AC_OP_DATE, a.AC_ED_DATE, a.PD_NUMBER, a.bank_cd, b.bank_name "
				+ "	FROM account@XE@bhBank a"
				+ "	JOIN bankinfo b ON b.bank_cd = a.bank_cd "
				+ "	) c"
				+ " JOIN USER_INFO@XE@bhBank ui ON ui.ID = c.id "
				+ " WHERE c.id = ? ORDER BY c.AC_OP_DATE DESC ";

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, vo.getId());
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				OpenbankVO account = new OpenbankVO();
				account.setAc_number(Long.parseLong(rs.getString("Ac_number")));
				account.setId(rs.getString("id"));
				account.setAC_NAME(rs.getString("AC_NAME"));
				account.setAC_MONEY(rs.getLong("AC_MONEY"));
				account.setAC_OP_DATE(rs.getDate("AC_OP_DATE"));
				account.setAC_ED_DATE(rs.getDate("AC_ED_DATE"));
				account.setBank_cd(rs.getString("bank_cd"));
				account.setBank_name(rs.getString("bank_name"));
				account.setName(rs.getString("name"));
				accountList.add(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return accountList;
	}
	
	// 승재
	public List<OpenbankVO> plAcList(OpenbankVO vo) {
		List<OpenbankVO> accountList = new ArrayList<OpenbankVO>();
		String query = 
				" SELECT c.Ac_number, c.id, ui.NAME, c.AC_NAME, c.AC_MONEY, c.AC_OP_DATE, c.AC_ED_DATE, c.bank_cd, c.bank_name, d.pd_name "
				+ " FROM ("
				 +  " SELECT a.Ac_number, a.id, a.AC_NAME, a.AC_MONEY, a.AC_OP_DATE, a.AC_ED_DATE, a.PD_NUMBER, a.bank_cd, b.bank_name "
				 +   " FROM account@XE@sjBank a "
				 +  " JOIN bankinfo b ON b.bank_cd = a.bank_cd "
			    + " ) c "
				+ " JOIN products@XE@sjBank d ON d.PD_NUMBER = c.PD_NUMBER "
				+ " JOIN USER_INFO@XE@sjBank ui ON ui.ID = c.id "
				+ " WHERE c.id = ? ORDER BY c.AC_OP_DATE DESC ";

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, vo.getId());
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				OpenbankVO account = new OpenbankVO();
				account.setAc_number(Long.parseLong(rs.getString("Ac_number")));
				account.setId(rs.getString("id"));
				account.setAC_NAME(rs.getString("AC_NAME"));
				account.setAC_MONEY(rs.getLong("AC_MONEY"));
				account.setAC_OP_DATE(rs.getDate("AC_OP_DATE"));
				account.setAC_ED_DATE(rs.getDate("AC_ED_DATE"));
				account.setBank_cd(rs.getString("bank_cd"));
				account.setBank_name(rs.getString("bank_name"));
				account.setPd_name(rs.getString("pd_name"));
				account.setName(rs.getString("name"));
				accountList.add(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return accountList;
	}
	
//	public List<OpenbankVO> getAccountList(OpenbankVO vo, String dbLink) {
//	    List<OpenbankVO> accountList = new ArrayList<OpenbankVO>();
//
//	    String query =
//	            " SELECT c.Ac_number, c.id, ui.NAME, c.AC_NAME, c.AC_MONEY, c.AC_OP_DATE, c.AC_ED_DATE, c.bank_cd, c.bank_name, d.pd_name "
//	                    + " FROM ("
//	                    + " SELECT a.Ac_number, a.id, a.AC_NAME, a.AC_MONEY, a.AC_OP_DATE, a.AC_ED_DATE, a.PD_NUMBER, a.bank_cd, b.bank_name "
//	                    + " FROM account" + dbLink + " a "
//	                    + " JOIN bankinfo b ON b.bank_cd = a.bank_cd "
//	                    + " ) c "
//	                    + " JOIN products" + dbLink + " d ON d.PD_NUMBER = c.PD_NUMBER "
//	                    + " JOIN USER_INFO" + dbLink + " ui ON ui.ID = c.id "
//	                    + " WHERE c.id = ? ORDER BY c.AC_OP_DATE DESC ";
//
//	    try {
//	        conn = JDBCUtil.getConnection();
//	        stmt = conn.prepareStatement(query);
//	        stmt.setString(1, vo.getId());
//
//	        rs = stmt.executeQuery();
//	        while (rs.next()) {
//	            OpenbankVO account = new OpenbankVO();
//	            account.setAc_number(Long.parseLong(rs.getString("Ac_number")));
//	            account.setId(rs.getString("id"));
//	            account.setAC_NAME(rs.getString("AC_NAME"));
//	            account.setAC_MONEY(rs.getLong("AC_MONEY"));
//	            account.setAC_OP_DATE(rs.getDate("AC_OP_DATE"));
//	            account.setAC_ED_DATE(rs.getDate("AC_ED_DATE"));
//	            account.setBank_cd(rs.getString("bank_cd"));
//	            account.setBank_name(rs.getString("bank_name"));
//	            account.setPd_name(rs.getString("pd_name"));
//	            account.setName(rs.getString("name"));
//	            accountList.add(account);
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    } finally {
//	        JDBCUtil.close(rs, stmt, conn);
//	    }
//	    return accountList;
//	}
//
//	public List<OpenbankVO> nsAcList(OpenbankVO vo) {
//	    return getAccountList(vo, "@test_link");
//	}
//
//	public List<OpenbankVO> bhAcList(OpenbankVO vo) {
//	    return getAccountList(vo, "@bhBank");
//	}
//
//	public List<OpenbankVO> plAcList(OpenbankVO vo) {
//	    return getAccountList(vo, "@sjBank");
//	}

	
	// Ac_number, bank_cd로 조회
	public OpenbankVO getAccount(long ac_number, String bank_cd) {
		OpenbankVO account = null;
		
		String query;
		switch (bank_cd) {
	        case "204":
	        	query = " SELECT c.ac_number, c.id, ui.NAME, c.AC_NAME, c.AC_MONEY, c.AC_OP_DATE, c.AC_ED_DATE, c.bank_cd, c.bank_name, d.pd_name "
						+ " FROM ("
						 +  " SELECT a.ac_number, a.id, a.AC_NAME, a.AC_MONEY, a.AC_OP_DATE, a.AC_ED_DATE, a.PD_NUMBER, a.bank_cd, b.bank_name "
						 +   " FROM account a "
						 +  " JOIN bankinfo b ON b.bank_cd = a.bank_cd "
					    + " ) c "
						+ " JOIN product d ON d.PD_NUMBER = c.PD_NUMBER "
						+ " JOIN USER_INFO ui ON ui.ID = c.id "
						+ " where c.ac_number = ? and c.bank_cd = ? ";
	            break;
	        case "159":
	        	query = " SELECT c.accnum, c.id, ui.NAME, c.AC_NAME, c.AC_MONEY, c.AC_OP_DATE, c.AC_ED_DATE, c.bank_cd, c.bank_name, d.pd_name "
						+ " FROM ("
						+  " SELECT a.accnum, a.id, a.AC_NAME, a.AC_MONEY, a.AC_OP_DATE, a.AC_ED_DATE, a.PD_NUMBER, a.bank_cd, b.bank_name "
						+   " FROM account@XE@shBank a "
						+  " JOIN bankinfo@XE@shBank b ON b.bank_cd = a.bank_cd "
					    + " ) c "
						+ " JOIN product@XE@shBank d ON d.PD_NUMBER = c.PD_NUMBER "
						+ " JOIN USER_INFO@XE@shBank ui ON ui.ID = c.id "
						+ " where c.accnum = ? and c.bank_cd = ? ";
	            break;
	        case "111":
	        	query = " SELECT c.ac_number, c.id, ui.NAME, c.AC_NAME, c.AC_MONEY, c.AC_OP_DATE, c.AC_ED_DATE, c.bank_cd, c.bank_name "  /*, d.pd_name*/
						+ " FROM ("
						 +  " SELECT a.ac_number, a.id, a.AC_NAME, a.AC_MONEY, a.AC_OP_DATE, a.AC_ED_DATE, a.PD_NUMBER, a.bank_cd, b.bank_name "
						 +   " FROM account@XE@bhBank a "
						 +  " JOIN bankinfo b ON b.bank_cd = a.bank_cd "
					    + " ) c "
						+ " JOIN USER_INFO@XE@bhBank ui ON ui.ID = c.id "
						+ " where c.ac_number = ? and c.bank_cd = ? ";
//						+ " JOIN product@bhBank d ON d.PD_NUMBER = c.PD_NUMBER "
	            break;
	        case "616":
	        	query = " SELECT c.ac_number, c.id, ui.NAME, c.AC_NAME, c.AC_MONEY, c.AC_OP_DATE, c.AC_ED_DATE, c.bank_cd, c.bank_name, d.pd_name "
	        			+ " FROM ("
	        			+  " SELECT a.ac_number, a.id, a.AC_NAME, a.AC_MONEY, a.AC_OP_DATE, a.AC_ED_DATE, a.PD_NUMBER, a.bank_cd, b.bank_name "
	        			+   " FROM account@XE@sjBank a "
	        			+  " JOIN bankinfo b ON b.bank_cd = a.bank_cd "
	        			+ " ) c "
	        			+ " JOIN products@XE@sjBank d ON d.PD_NUMBER = c.PD_NUMBER "
	        			+ " JOIN USER_INFO@XE@sjBank ui ON ui.ID = c.id "
	        			+ " where c.ac_number = ? and c.bank_cd = ? ";
	        	break;
	        default:
	            // 다른 은행 코드에 대한 처리를 여기에 추가하거나 또는 에러 처리를 할 수 있습니다.
	            throw new RuntimeException("지원되지 않는 은행 코드입니다.");
	    }
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setLong(1, ac_number);
			stmt.setString(2, bank_cd);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				account = new OpenbankVO();
				
			    long accNumber;
			    if ("159".equals(bank_cd)) {
			        accNumber = rs.getLong("accnum");
			    } else {
			        accNumber = rs.getLong("ac_number");
			    }
			    account.setAc_number(accNumber);
			    
				account.setId(rs.getString("id"));
				account.setAC_NAME(rs.getString("AC_NAME"));
				account.setAC_MONEY(rs.getLong("AC_MONEY"));
				account.setAC_OP_DATE(rs.getDate("AC_OP_DATE"));
				account.setAC_ED_DATE(rs.getDate("AC_ED_DATE"));
				account.setBank_name(rs.getString("bank_name"));
				if (!"111".equals(bank_cd)) {
				account.setPd_name(rs.getString("pd_name"));
				}
				account.setName(rs.getString("name"));
				account.setBank_cd(rs.getString("bank_cd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		return account;
	}
	
	// 이체
	public int transfer(long sendAc_number, String sendBank_cd, 
						long receivAc_number, String receivBank_cd,	
						long transferAmount, String rc_text) {
		int result = 0;

	    // 출금 쿼리문
	    String withdrawalQuery;
	    switch (sendBank_cd) {
        case "204":
        	withdrawalQuery = " UPDATE account SET AC_MONEY = AC_MONEY - ? "
        					+ " WHERE ac_number = ? AND bank_cd = ?";
            break;
        case "159":
        	withdrawalQuery = " UPDATE account@XE@shBank SET AC_MONEY = AC_MONEY - ? "
        					+ " WHERE accnum = ? AND bank_cd = ? ";
            break;
        case "111":
        	withdrawalQuery = " UPDATE account@XE@bhBank SET AC_MONEY = AC_MONEY - ? "
        					+ " WHERE ac_number = ? AND bank_cd = ? ";
            break;
        case "616":
        	withdrawalQuery = " UPDATE account@XE@sjBank SET AC_MONEY = AC_MONEY - ?"
        					+ " WHERE ac_number = ? AND bank_cd = ? ";
        	break;
        default:
            // 다른 은행 코드에 대한 처리를 여기에 추가하거나 또는 에러 처리를 할 수 있습니다.
            throw new RuntimeException("지원되지 않는 은행 코드입니다.");
	    }
	    
	    int withdrawalResult = 0;

	    // 입금 쿼리문
	    String depositQuery;
	    switch (receivBank_cd) {
	        case "204":
	            depositQuery = "UPDATE account SET AC_MONEY = AC_MONEY + ? WHERE ac_number = ? AND bank_cd = ?";
	            break;
	        case "159":
	            depositQuery = "UPDATE account@XE@shBank SET AC_MONEY = AC_MONEY + ? WHERE accnum = ? AND bank_cd = ?";
	            break;
	        case "111":
	            depositQuery = "UPDATE account@XE@bhBank SET AC_MONEY = AC_MONEY + ? WHERE ac_number = ? AND bank_cd = ?";
	            break;    
	        case "616":
	        	depositQuery = "UPDATE account@XE@sjBank SET AC_MONEY = AC_MONEY + ? WHERE ac_number = ? AND bank_cd = ?";
	        	break;    
	        default:
	            // 다른 은행 코드에 대한 처리를 여기에 추가하거나 또는 에러 처리를 할 수 있습니다.
	            throw new RuntimeException("지원되지 않는 은행 코드입니다.");
	    }
	    
	    int depositResult = 0;

	    try {
	        conn = JDBCUtil.getConnection();

	        // 출금 쿼리 실행
	        stmt = conn.prepareStatement(withdrawalQuery);
	        stmt.setLong(1, transferAmount);
	        stmt.setLong(2, sendAc_number);
	        stmt.setString(3, sendBank_cd);
	        withdrawalResult = stmt.executeUpdate();

	        // 입금 쿼리 실행
	        stmt = conn.prepareStatement(depositQuery);
	        stmt.setLong(1, transferAmount);
	        stmt.setLong(2, receivAc_number);
	        stmt.setString(3, receivBank_cd);
	        depositResult = stmt.executeUpdate();

	        // 입출금 결과가 성공적이면 이체 내역 저장
	        if (withdrawalResult > 0 && depositResult > 0) {
	        	
	        	Ac_recordDAO ac_recordDAO = new Ac_recordDAO();

	            // 출금 이력 추가
	            ac_recordDAO.insertTransaction(sendAc_number, sendBank_cd, "송금", receivBank_cd, receivAc_number, transferAmount, rc_text);

	            // 입금 이력 추가
	            ac_recordDAO.insertTransaction(receivAc_number, receivBank_cd, "수금", sendBank_cd, sendAc_number, transferAmount, rc_text);

	            result = 1;
	        } else {
	            // 실패한 경우 롤백 처리
	            conn.rollback();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCUtil.close(stmt, conn);
	    }

	    return result;
	}
}


