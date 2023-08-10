package comment;

import java.sql.Timestamp;

/*
	CREATE TABLE jb_comment (
		commentNO number(10) PRIMARY KEY,
		boardNO number(10),
		id VARCHAR2(50),
		CONTENT VARCHAR2(2000),
		regtime TIMESTAMP DEFAULT SYSTIMESTAMP,
		FOREIGN KEY (id) REFERENCES jb_user (id),
		FOREIGN KEY (boardNO) REFERENCES jb_board(boardNO)
	);
	
	create sequence seq_commentNO nocache;
*/

public class CommentVO {
	
	private int commentNO;
	private int boardNO;
	private String id;
	private String content;
	private Timestamp regtime;
	
	public int getCommentNO() {
		return commentNO;
	}
	public void setCommentNO(int commentNO) {
		this.commentNO = commentNO;
	}
	public int getBoardNO() {
		return boardNO;
	}
	public void setBoardNO(int boardNO) {
		this.boardNO = boardNO;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegtime() {
		return regtime;
	}
	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
	}
	
}
