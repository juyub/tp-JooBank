package check;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.JDBCUtil;

public class CheckDAO {

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	// Ac_number, bank_cd 확인
	public boolean CheckAc_number(long ac_number, String bank_cd) {
		boolean check = false;

		String query;
		switch (bank_cd) {
		case "204":
			query = " select * from account " + " where ac_number = ? and bank_cd = ? ";
			break;
		case "159":
			query = " select * from account@XE@shBank " + " where accnum = ? and bank_cd = ? ";
			break;
		case "111":
			query = " select * from account@XE@bhBank " + " where ac_number = ? and bank_cd = ? ";
			break;
		case "616":
			query = " select * from account@XE@sjBank " + " where ac_number = ? and bank_cd = ? ";
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

			check = rs.next(); // true
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return check;
	}

	// Ac_number, AC_PW 확인
	public boolean checkAC_PW(long ac_number, String bank_cd, String AC_PW) {
		boolean check = false;
		
		String query;
		switch (bank_cd) {
			case "204":
				query = " select * from account " + " where ac_number = ? and AC_PW = ? ";
				break;
			case "159":
				query = " select * from account@XE@shBank " + " where accnum = ? and AC_PW = ? ";
				break;
			case "111":
				query = " select * from account@XE@bhBank " + " where ac_number = ? and AC_PW = ? ";
				break;
			case "616":
				query = " select * from account@XE@sjBank " + " where ac_number = ? and AC_PW = ? ";
				break;
			default:
				// 다른 은행 코드에 대한 처리를 여기에 추가하거나 또는 에러 처리를 할 수 있습니다.
				throw new RuntimeException("지원되지 않는 은행 코드입니다.");
			}
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setLong(1, ac_number);
			stmt.setString(2, AC_PW);
			rs = stmt.executeQuery();

			check = rs.next(); // true
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return check;
	}

}
