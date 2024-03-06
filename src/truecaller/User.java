package truecaller;

import java.util.UUID;

public class User extends Account {

	public User(String phoneNumber, String firstName) {
		super(phoneNumber, firstName);
	}
	
	public User(String phoneNumber, String firstName, String lastName) {
		super(phoneNumber, firstName, lastName);
	}
	
	public User() {
		this.setContactTrie(new ContactTrie());
	}

	@Override
	public void register(String firstName, String userName, String email, String password, String phoneNumber,
			String countryCode, UserCategory userCategory) {
		this.setId(UUID.randomUUID().toString());
		this.setPersonalInfo(new PersonalInfo(firstName));
		this.setUserName(userName);
		this.setEmail(email);
		this.setPassword(password);
		this.setContact(new Contact(phoneNumber, email, countryCode));
		this.setUserCategory(userCategory);
		this.setPassword(password);
		insertToTries(phoneNumber, firstName);
		
		
	}
	
	private void insertToTries(String phoneNumber, String firstName) {
		this.getContactTrie().insert(firstName);
		this.getContactTrie().insert(phoneNumber);
	}

	

}
