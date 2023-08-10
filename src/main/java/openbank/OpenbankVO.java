package openbank;

import java.util.Date;

public class OpenbankVO {

    private long ac_number;
    private String id;
    private String AC_PW;
    private String AC_NAME;
    private long AC_MONEY;
    private String bankCode;
    private Date AC_OP_DATE;
    private Date AC_ED_DATE;
    private String STATE;
    private int PD_NUMBER;
    private String bank_cd;
    
    private String bank_name;

    private String pd_name;
    
    private String name;

	public long getAc_number() {
		return ac_number;
	}

	public void setAc_number(long ac_number) {
		this.ac_number = ac_number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAC_PW() {
		return AC_PW;
	}

	public void setAC_PW(String aC_PW) {
		AC_PW = aC_PW;
	}

	public String getAC_NAME() {
		return AC_NAME;
	}

	public void setAC_NAME(String aC_NAME) {
		AC_NAME = aC_NAME;
	}

	public long getAC_MONEY() {
		return AC_MONEY;
	}

	public void setAC_MONEY(long aC_MONEY) {
		AC_MONEY = aC_MONEY;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Date getAC_OP_DATE() {
		return AC_OP_DATE;
	}

	public void setAC_OP_DATE(Date aC_OP_DATE) {
		AC_OP_DATE = aC_OP_DATE;
	}

	public Date getAC_ED_DATE() {
		return AC_ED_DATE;
	}

	public void setAC_ED_DATE(Date aC_ED_DATE) {
		AC_ED_DATE = aC_ED_DATE;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}

	public int getPD_NUMBER() {
		return PD_NUMBER;
	}

	public void setPD_NUMBER(int pD_NUMBER) {
		PD_NUMBER = pD_NUMBER;
	}

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

	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
