package news;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import common.JDBCUtil;

@Component
public class NewsDAO {
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	@Autowired
	private DataSource dataSource;
	
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}

	public List<NewsVO> getBoardList(){
		String sql = " select * from board order by seq desc ";
		
		// spring JDBC
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource);
		List<NewsVO> boardList = template.query(sql, new BeanPropertyRowMapper(NewsVO.class));
		
//		try {
////			conn = JDBCUtil.getConnection();
//			conn = dataSource.getConnection();
//			stmt = conn.prepareStatement(sql);
//			rs = stmt.executeQuery();
//			while(rs.next()) {
//				BoardVO board = new BoardVO();
//				board.setSeq(rs.getInt("SEQ"));
//				board.setTitle(rs.getString("TITLE"));
//				board.setWriter(rs.getString("WRITER"));
//				board.setContent(rs.getString("CONTENT"));
//				board.setRegDate(rs.getDate("REGDATE"));
//				board.setHit(rs.getInt("HIT"));
//				boardList.add(board);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JDBCUtil.close(rs, stmt, conn);
//		}
		return boardList;
	}

//	public BoardVO getBorad(BoardVO vo) {
//		String sql = " select * from board WHERE seq = ? ";
//		
//	    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//	    RowMapper<BoardVO> rowMapper = new BeanPropertyRowMapper<>(BoardVO.class);
//	    BoardVO board = jdbcTemplate.queryForObject(sql.toString(), new Object[]{vo.getSeq()}, rowMapper);
//	    return board;
//		
////		BoardVO board = null; 
////		try {
//////			conn = JDBCUtil.getConnection();
////			conn = dataSource.getConnection();
////			stmt = conn.prepareStatement(sql);
////			stmt.setInt(1, vo.getSeq());
////			
////			rs = stmt.executeQuery();
////			if(rs.next()) {
////				board = new BoardVO();
////				board.setSeq(rs.getInt("SEQ"));
////				board.setTitle(rs.getString("TITLE"));
////				board.setWriter(rs.getString("WRITER"));
////				board.setContent(rs.getString("CONTENT"));
////				board.setRegDate(rs.getDate("REGDATE"));
////				board.setHit(rs.getInt("HIT")+1);
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////		} finally {
////			JDBCUtil.close(rs, stmt, conn);
////		}
////		return board;
//	}

	public NewsVO getBorad(String seq) {
		String sql = " select * from board WHERE seq = ? ";
		
		// spring JDBC
		JdbcTemplate template = new JdbcTemplate(dataSource);
		NewsVO board = template.queryForObject(sql, new BeanPropertyRowMapper<>(NewsVO.class), seq);
		
		return board;
	}
	
	public void addBoard(NewsVO vo) {
		String sql = 
				" insert into board(seq, title, writer, content) " +
		        " values((select nvl(max(seq), 0) + 1 from board), ?, ?, ?) ";
		
	    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	    jdbcTemplate.update(sql, vo.getTitle(), vo.getWriter(), vo.getContent());
	}
	
	public void updateBoard(NewsVO vo) {
		String sql = 
				" UPDATE board SET title = ?, content = ? WHERE seq = ? ";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, vo.getTitle(), vo.getContent(), vo.getSeq());
	}

	public void deleteBoard(String seq) {
		String sql = " delete from board WHERE seq = ? ";
		
	    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	    jdbcTemplate.update(sql, seq);
	}

}