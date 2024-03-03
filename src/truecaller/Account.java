package truecaller;

public abstract class Account {
	private String id;
	private String phoneNumber;
	private String userName;
	private String password;
	private String email;
	private UserCategtory userCategory;
	private PersonalInfo personalInfo;
	private Contact contact;
	
	public Account() {
		
	}
	
	public Account(String phoneNumber, String firstName, String lastName) {
		this(phoneNumber, firstName);
		this.personalInfo.setLastName(lastName);
	}

	public Account(String phoneNumber, String firstName) {
		this.phoneNumber = phoneNumber;
		this.personalInfo = new PersonalInfo(firstName);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserCategtory getUserCategory() {
		return userCategory;
	}
	
	public void setUserCategory(UserCategtory userCategory) {
		this.userCategory = userCategory;
	}

	public abstract void register(String firstName, String userName, 
			String email, String password, String phoneNumber, String countryCode, UserCategtory userCategory);

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	
}