package com.truecaller;

public class Contact {
	private String countryCode;
	private String phone;
	private String email;
	
	public Contact(String phone) {
		this.phone = phone;
	}
	
	public Contact(String phone, String email) {
		this(phone);
		this.email = email;
	}
	
	public Contact(String phone, String email, String countryCode) {
		this(phone, email);
		this.countryCode = countryCode;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
	
	
}
