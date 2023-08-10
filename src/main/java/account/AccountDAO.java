package account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ac_record.Ac_recordDAO;
import ac_record.Ac_recordVO;
import common.JDBCUtil;

public class AccountDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	// 계좌 생성
	public void createAccount(AccountVO vo) {
	    String accountNumber = AccountNumberGenerator.generateAccountNumber();
	    String query = "INSERT INTO account (ac_number, id, AC_PW, AC_MONEY, bank_cd, AC_OP_DATE, PD_NUMBER, AC_ED_DATE)"
	            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = JDBCUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setString(1, accountNumber);
	        stmt.setString(2, vo.getId());
	        stmt.setString(3, vo.getAC_PW());
	        stmt.setInt(4, 0);
	        stmt.setString(5, "204");
	        
	        vo.setAC_OP_DATE(new Date()); // 현재 날짜와 시간으로 설정
	        
	        stmt.setDate(6, new java.sql.Date(vo.getAC_OP_DATE().getTime()));
	        stmt.setInt(7, vo.getPD_NUMBER());
	        stmt.setDate(8, new java.sql.Date(vo.getAC_ED_DATE().getTime()));

	        stmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	// 내 계좌 목록
	public List<AccountVO> myAccountList(AccountVO vo) {
		List<AccountVO> accountList = new ArrayList<AccountVO>();
		String query = 
				" SELECT c.ac_number, c.id, ui.NAME, c.AC_NAME, c.AC_MONEY, c.AC_OP_DATE, c.AC_ED_DATE, c.bank_name, d.pd_name "
				+ " FROM ("
				 +  " SELECT a.ac_number, a.id, a.AC_NAME, a.AC_MONEY, a.AC_OP_DATE, a.AC_ED_DATE, a.PD_NUMBER, b.bank_name "
				 +   " FROM account a "
				 +  " JOIN bankinfo b ON b.bank_cd = a.bank_cd "
			    + " ) c "
				+ " JOIN product d ON d.PD_NUMBER = c.PD_NUMBER "
				+ " JOIN USER_INFO ui ON ui.ID = c.id "
				+ " WHERE c.id = ? ORDER BY c.AC_OP_DATE DESC ";

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, vo.getId());
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				AccountVO account = new AccountVO();
				account.setAc_number(rs.getLong("ac_number"));
				account.setId(rs.getString("id"));
				account.setAC_NAME(rs.getString("AC_NAME"));
				account.setAC_MONEY(rs.getLong("AC_MONEY"));
				account.setAC_OP_DATE(rs.getDate("AC_OP_DATE"));
				account.setAC_ED_DATE(rs.getDate("AC_ED_DATE"));
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
	
	// Ac_number로 조회
		public AccountVO getAccount(long ac_number) {
		    AccountVO account = null;
		    String query = 
		    		" SELECT c.ac_number, c.id, ui.NAME, c.AC_NAME, c.AC_MONEY, c.AC_OP_DATE, c.AC_ED_DATE, c.bank_cd, c.bank_name, d.pd_name "
		            + " FROM ( "
		            + "   SELECT a.ac_number, a.id, a.AC_NAME, a.AC_MONEY, a.AC_OP_DATE, a.AC_ED_DATE, a.PD_NUMBER, a.bank_cd, b.bank_name "
		            + "   FROM account a "
		            + "   JOIN bankinfo b ON b.bank_cd = a.bank_cd "
		            + " ) c "
		            + " JOIN product d ON d.PD_NUMBER = c.PD_NUMBER "
		            + " JOIN USER_INFO ui ON ui.ID = c.id "
		            + " WHERE c.ac_number = ? ";
		    
		    try {
		        conn = JDBCUtil.getConnection();
		        stmt = conn.prepareStatement(query);
		        stmt.setLong(1, ac_number);
		        
		        ResultSet rs = stmt.executeQuery();
		        if (rs.next()) {
		            account = new AccountVO();
		            account.setAc_number(rs.getLong("ac_number"));
		            account.setId(rs.getString("id"));
		            account.setAC_NAME(rs.getString("AC_NAME"));
		            account.setAC_MONEY(rs.getLong("AC_MONEY"));
		            account.setAC_OP_DATE(rs.getDate("AC_OP_DATE"));
		            account.setAC_ED_DATE(rs.getDate("AC_ED_DATE"));
		            account.setBank_name(rs.getString("bank_name"));
		            account.setPd_name(rs.getString("pd_name"));
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
	
	// Ac_number, bank_cd로 조회
	public AccountVO getAccount(long Ac_number, String bank_cd) {
		AccountVO account = null;
		String query =
				" SELECT c.ac_number, c.id, ui.NAME, c.AC_NAME, c.AC_MONEY, c.AC_OP_DATE, c.AC_ED_DATE, c.bank_cd, c.bank_name, d.pd_name "
						+ " FROM ("
						 +  " SELECT a.ac_number, a.id, a.AC_NAME, a.AC_MONEY, a.AC_OP_DATE, a.AC_ED_DATE, a.PD_NUMBER, a.bank_cd, b.bank_name "
						 +   " FROM account a "
						 +  " JOIN bankinfo b ON b.bank_cd = a.bank_cd "
					    + " ) c "
						+ " JOIN product d ON d.PD_NUMBER = c.PD_NUMBER "
						+ " JOIN USER_INFO ui ON ui.ID = c.id "
				+ " where c.ac_number = ? and c.bank_cd = ? ";
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setLong(1, Ac_number);
			stmt.setString(2, bank_cd);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				account = new AccountVO();
				account.setAc_number(rs.getLong("ac_number"));
				account.setId(rs.getString("id"));
				account.setAC_NAME(rs.getString("AC_NAME"));
				account.setAC_MONEY(rs.getLong("AC_MONEY"));
				account.setAC_OP_DATE(rs.getDate("AC_OP_DATE"));
				account.setAC_ED_DATE(rs.getDate("AC_ED_DATE"));
				account.setBank_name(rs.getString("bank_name"));
				account.setPd_name(rs.getString("pd_name"));
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
	
	
	// 입금
	public int deposit(long ac_number, String bank_cd, String rc_text, long depositAmount, boolean withTransactionLog) {
	    String query = 
	    		" UPDATE account SET AC_MONEY = AC_MONEY + ? "
	    		+ " WHERE ac_number = ? and bank_cd = ? ";
	    int result = 0;

	    try {
	        conn = JDBCUtil.getConnection();
	        stmt = conn.prepareStatement(query);
	        stmt.setLong(1, depositAmount);
	        stmt.setLong(2, ac_number);
	        stmt.setString(3, bank_cd);

	        result = stmt.executeUpdate();

	        // Insert transaction history for deposit
	        if (result > 0 && withTransactionLog) {
	        	AccountVO account = getAccount(ac_number);
		        String Id = account.getId();
		        String Name = account.getName();
		        long AC_MONEY = account.getAC_MONEY();
		        
		        Ac_recordDAO ac_recordDAO = new Ac_recordDAO(); // 클래스 인스턴스 생성
//	            ac_recordDAO.insertTransaction(ac_number, Id, "입금", Name, depositAmount, AC_MONEY);
	            
//	            ac_recordDAO.insertTransaction(ac_number, Id, "입금", Name, depositAmount, AC_MONEY , rc_text, ac_number);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCUtil.close(stmt, conn);
	    }

	    return result;
	}

	// 출금
	public int withdraw(long ac_number, String bank_cd, String rc_text, long withdrawAmount, boolean withTransactionLog) {
	    String query = 
	    		" UPDATE account SET AC_MONEY = AC_MONEY - ? "
	    		+ " WHERE ac_number = ? and bank_cd = ? ";
	    int result = 0;

	    try {
	        conn = JDBCUtil.getConnection();
	        stmt = conn.prepareStatement(query);
	        stmt.setLong(1, withdrawAmount);
	        stmt.setLong(2, ac_number);
	        stmt.setString(3, bank_cd);

	        result = stmt.executeUpdate();

	        // Insert transaction history for withdrawal
	        if (result > 0 && withTransactionLog) {
	        	AccountVO account = getAccount(ac_number);
		        String Id = account.getId();
		        String Name = account.getName();
		        long AC_MONEY = account.getAC_MONEY();
		        
		        Ac_recordDAO ac_recordDAO = new Ac_recordDAO(); // 클래스 인스턴스 생성
//	            ac_recordDAO.insertTransaction(ac_number, Id, "출금", Name, withdrawAmount,AC_MONEY);
		        
//	            ac_recordDAO.insertTransaction(ac_number, Id, "출금", Name, withdrawAmount,AC_MONEY,rc_text,ac_number);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCUtil.close(stmt, conn);
	    }

	    return result;
	}

	// 이체
	public int transfer(long sendAc_number, String sendBank_cd, long receivAc_number, String receivBank_cd, long transferAmount, String rc_text) {
	    int result = 0;

	    // 출금 쿼리문
	    String withdrawalQuery = "UPDATE account SET AC_MONEY = AC_MONEY - ? WHERE ac_number = ? AND bank_cd = ?";
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


	// 오버로드 메서드를 추가
	public int deposit(long ac_number, String bank_cd, String rc_text, long depositAmount) {
	    return deposit(ac_number, bank_cd, rc_text, depositAmount, true);
	}

	public int withdraw(long ac_number, String bank_cd, String rc_text, long withdrawAmount) {
	    return withdraw(ac_number, bank_cd, rc_text, withdrawAmount, true);
	}
	
}
