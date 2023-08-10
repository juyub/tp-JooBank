package board;

import java.sql.Timestamp;

/*
	CREATE TABLE jb_board (
		boardno NUMBER(10) PRIMARY KEY,
		parentno NUMBER(10) DEFAULT 0,
		id VARCHAR2(50),
		title VARCHAR2(200),
		CONTENT VARCHAR2(2000),
		regtime TIMESTAMP DEFAULT SYSTIMESTAMP,
		hit NUMBER(10, 0) DEFAULT 0,
		FOREIGN KEY (id) REFERENCES jb_user(id)
	);
	
	create sequence seq_boardno nocache;
 */

public class BoardVO {
	
	private int level;
	private int boardNO;
	private int parentNO;
	private String id;
	private String title;
	private String content;
	private Timestamp regtime;
	private int hit;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getBoardNO() {
		return boardNO;
	}
	public void setBoardNO(int boardNO) {
		this.boardNO = boardNO;
	}
	public int getParentNO() {
		return parentNO;
	}
	public void setParentNO(int parentNO) {
		this.parentNO = parentNO;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}

}
