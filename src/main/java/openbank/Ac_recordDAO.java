package openbank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import account.AccountVO;
import common.JDBCUtil;

public class Ac_recordDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	// 거래내역 산입
	public void insertTransaction(long ac_number, String bank_cd, String type, String opBank_cd, long opAc_number, long transferAmount, String rc_text) {
	    
		String query;
	    switch (bank_cd) {
	        case "204":
	            query = " INSERT INTO ac_record (rc_no, ac_number, id, rc_type, rc_name, rc_money, rc_text, rc_number, rc_balance)"
	            		+ " VALUES(seq_rc_no.NEXTVAL,?, ?, ?, ?, ?, ?, ?, ?)";
	            break;
	        case "159":
	    		query = " INSERT INTO ac_record@XE@shBank (rc_no, accnum, id, rc_type, rc_name, rc_money, rc_text, rc_number) "
	    				+ " VALUES((select nvl(max(rc_no),0)+1 from ac_record@XE@shBank),?, ?, ?, ?, ?, ?, ?) ";
	    		break;
	    	case "111":
	    		query = " INSERT INTO ac_record@XE@bhBank (rc_no, ac_number, id, rc_type, rc_name, rc_money, rc_text, rc_number, rc_balance) "
	    				+ " VALUES((select nvl(max(rc_no),0)+1 from ac_record@XE@bhBank),?, ?, ?, ?, ?, ?, ?, ?) "; 
	    		break;
	    	case "616":
	    		query = " INSERT INTO ac_record@XE@sjBank (rc_no, ac_number, id, rc_type, rc_name, rc_money, rc_text, rc_number, rc_balance) "
	    				+ " VALUES((select nvl(max(rc_no),0)+1 from ac_record@XE@sjBank),?, ?, ?, ?, ?, ?, ?, ?) "; 
	    		break;
	    	default:
	    		// 다른 은행 코드에 대한 처리를 여기에 추가하거나 또는 에러 처리를 할 수 있습니다.
	    		throw new RuntimeException("지원되지 않는 은행 코드입니다.");
	    }

	    OpenbankDAO dao = new OpenbankDAO();
	    OpenbankVO account = dao.getAccount(ac_number, bank_cd);
	    long AC_MONEY = account.getAC_MONEY();
	    String Id = account.getId();

	    OpenbankVO opAccount = dao.getAccount(opAc_number, opBank_cd);
	    String Name = opAccount.getName();

	    try {
	        conn = JDBCUtil.getConnection();  
	        stmt = conn.prepareStatement(query);
	        
            stmt.setLong(1, ac_number);
	        stmt.setString(2, Id);
	        stmt.setString(3, type);
	        stmt.setString(4, Name);
	        stmt.setLong(5, transferAmount);
	        stmt.setString(6, rc_text);
	        stmt.setLong(7, opAc_number);
	        
	        if (!"159".equals(bank_cd)) {   // bank_cd가 "159"가 아닐 때
	            stmt.setLong(8, AC_MONEY);
	        }

	        stmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        JDBCUtil.close(stmt, conn);
	    }
	}


	// 거래 내역 목록
	public List<Ac_recordVO> getAc_recordList(long ac_number, String bank_cd, int pageNo, int pageSize) {
		List<Ac_recordVO> ac_recordList = new ArrayList<Ac_recordVO>();
		
		String query;
		switch (bank_cd) {
	        case "204":
	            query = " SELECT * FROM ( "
	            		+ " SELECT a.ac_number, a.id, a.rc_type, a.rc_name, a.rc_money, a.rc_time, "
	            		+ " a.rc_balance, a.rc_text, ROW_NUMBER() OVER (ORDER BY a.rc_time DESC) as row_num FROM ac_record a "
	            		+ " WHERE a.ac_number = ? ) T "
	            		+ " WHERE T.row_num BETWEEN ((? - 1) * ?) + 1 AND ? * ? ";

	            break;
	        case "159":
	    		query = " SELECT * FROM ( "
	            		+ " SELECT a.accnum, a.id, a.rc_type, a.rc_name, a.rc_money, a.rc_time, "
	            		+ " a.rc_text, ROW_NUMBER() OVER (ORDER BY a.rc_time DESC) as row_num FROM ac_record@XE@shBank a "
	            		+ " WHERE a.accnum = ? ) T "
	            		+ " WHERE T.row_num BETWEEN ((? - 1) * ?) + 1 AND ? * ? "; 
	    		break;
	    	case "111":
	    		query = " SELECT * FROM ( "
	            		+ " SELECT a.ac_number, a.id, a.rc_type, a.rc_name, a.rc_money, a.rc_time, "
	            		+ " a.rc_balance, a.rc_text, ROW_NUMBER() OVER (ORDER BY a.rc_time DESC) as row_num FROM ac_record@XE@bhBank a "
	            		+ " WHERE a.ac_number = ? ) T "
	            		+ " WHERE T.row_num BETWEEN ((? - 1) * ?) + 1 AND ? * ? "; 
	    		break;
	    	case "616":
	    		query = " SELECT * FROM ( "
	            		+ " SELECT a.ac_number, a.id, a.rc_type, a.rc_name, a.rc_money, a.rc_time, "
	            		+ " a.rc_balance, a.rc_text, ROW_NUMBER() OVER (ORDER BY a.rc_time DESC) as row_num FROM ac_record@XE@sjBank a "
	            		+ " WHERE a.ac_number = ? ) T "
	            		+ " WHERE T.row_num BETWEEN ((? - 1) * ?) + 1 AND ? * ? "; 
	    		break;
	    	default:
	    		// 다른 은행 코드에 대한 처리를 여기에 추가하거나 또는 에러 처리를 할 수 있습니다.
	    		throw new RuntimeException("지원되지 않는 은행 코드입니다.");
	    }

		try {
		    conn = JDBCUtil.getConnection();
		    stmt = conn.prepareStatement(query);
		    stmt.setLong(1, ac_number);
		    stmt.setInt(2, pageNo);
		    stmt.setInt(3, pageSize);
		    stmt.setInt(4, pageNo);
		    stmt.setInt(5, pageSize);

		    rs = stmt.executeQuery();
		    while (rs.next()) {
		        Ac_recordVO record = new Ac_recordVO();
		        
		        long accNumber;
			    if ("159".equals(bank_cd)) {
			        accNumber = rs.getLong("accnum");
			    } else {
			        accNumber = rs.getLong("ac_number");
			    }
			    record.setAc_number(accNumber);
//			    record.setAc_number(rs.getLong("ac_number"));
		        
		        record.setId(rs.getString("id"));
		        record.setRc_type(rs.getString("rc_type"));
		        record.setRc_name(rs.getString("rc_name"));
		        record.setRc_money(rs.getLong("rc_money"));
		        record.setRc_time(rs.getTimestamp("rc_time"));
//		        record.setBank_name(rs.getString("bank_name"));
		        record.setRc_text(rs.getString("rc_text"));
		        
		        if (!"159".equals(bank_cd)) {   // bank_cd가 "159"가 아닐 때
		        	record.setRc_balance(rs.getLong("rc_balance"));
		        }
		        
		        ac_recordList.add(record);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    JDBCUtil.close(rs, stmt, conn);
		}

		return ac_recordList;
	}

	public int getAc_recordCount(long ac_number) {
		int count = 0;
		String query = "SELECT COUNT(*) AS count FROM ac_record WHERE ac_number = ? ";

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setLong(1, ac_number);
			rs = stmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return count;
	}

}
