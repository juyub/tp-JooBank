package bankinfo;

/*
	CREATE TABLE bankinfo (
	    bank_cd VARCHAR2(20) PRIMARY KEY,
	    bank_name VARCHAR2(50),
	);
 */

public class BankinfoVO {

	private String bank_cd;
    private String bank_name;
    
	public String getBank_cd() {
		return bank_cd;
	}
	public void setBank_cd(String bank_cd) {
		this.bank_cd = bank_cd;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
    
}
