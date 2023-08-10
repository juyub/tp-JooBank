package user;

import java.util.Date;

/*
	CREATE TABLE USER_INFO (
		ID VARCHAR2(50) PRIMARY KEY,
		PASSWORD VARCHAR2(50),
		NAME VARCHAR2(50),
		USERCODE VARCHAR2(20),
		PHONE VARCHAR2(20),
		EMAIL VARCHAR2(50),
		POSTCODE VARCHAR2(8),
		ADDRESS VARCHAR2(100),
		JOINDATE DATE DEFAULT CURRENT_DATE,
		USER_TYPE VARCHAR2(10) DEFAULT 'user'
	);
*/

public class UserVO {
	
    private String id;
    private String password;
    private String name;
    private String usercode;
    private String phone;
    private String email;
    private String postcode;
    private String address;
    private Date joindate;
    private String user_type;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
    
}
