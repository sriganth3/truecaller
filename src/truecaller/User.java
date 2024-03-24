package truecaller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import truecaller.common.Constants;
import truecaller.exception.BlockLimitExceedException;
import truecaller.exception.ContactsExceededException;

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
		init(userCategory);
		insertToTries(phoneNumber, firstName);


	}

	private void init(UserCategory userCategory) {
		switch(userCategory) {
		case FREE:
			this.setContacts(new HashMap<>(Constants.MAX_FREE_USER_CONTACTS));
			this.setBlockedContacts(new HashSet<>(Constants.MAX_FREE_USER_CONTACTS));
			break;
		case BASIC:
			this.setContacts(new HashMap<>(Constants.MAX_BASIC_USER_CONTACTS));
			this.setBlockedContacts(new HashSet<>(Constants.MAX_FREE_USER_CONTACTS));
			break;
		case PREMIUM:
			this.setContacts(new HashMap<>(Constants.MAX_PREMIUM_USER_CONTACTS));
			this.setBlockedContacts(new HashSet<>(Constants.MAX_FREE_USER_CONTACTS));
			break;
		}
		
	}

	private void insertToTries(String phoneNumber, String firstName) {
		this.getContactTrie().insert(firstName);
		this.getContactTrie().insert(phoneNumber);
	}

	@Override
	public void addContact(User user) throws ContactsExceededException {
		checkAddUser();
		this.getContacts().putIfAbsent(user.getPhoneNumber(), user);
		insertToTries(user.getPhoneNumber(), user.getPersonalInfo().getFirstName());
		

	}

	private void checkAddUser() throws ContactsExceededException {
		switch(this.getUserCategory()) {
		case FREE:
			if(this.getContacts().size() >= Constants.MAX_FREE_USER_CONTACTS) {
				throw new ContactsExceededException("Default Contact Size Exceeded");
			}
		case BASIC:
			if(this.getContacts().size() >= Constants.MAX_BASIC_USER_CONTACTS) {
				throw new ContactsExceededException("Default Contact Size Exceeded");
			}
		case PREMIUM:
			if(this.getContacts().size() >= Constants.MAX_PREMIUM_USER_CONTACTS) {
				throw new ContactsExceededException("Default Contact Size Exceeded");
			}
		}

	}

	@Override
	public void blockContact(String string) throws BlockLimitExceedException {
		checkBlockContact();
		this.getBlockedContacts().add(string);
		
		
	}
	
	private void checkBlockContact() throws BlockLimitExceedException {
		switch(this.getUserCategory()) {
		case FREE:
			if(this.getContacts().size() >= Constants.MAX_FREE_USER_CONTACTS) {
				throw new BlockLimitExceedException("Default Blocked Contact Size Exceeded");
			}
		case BASIC:
			if(this.getContacts().size() >= Constants.MAX_BASIC_USER_CONTACTS) {
				throw new BlockLimitExceedException("Default Blocked Contact Size Exceeded");
			}
		case PREMIUM:
			if(this.getContacts().size() >= Constants.MAX_PREMIUM_USER_CONTACTS) {
				throw new BlockLimitExceedException("Default Blocked Contact Size Exceeded");
			}
		}

	}

	@Override
	public boolean isBlocked(String number) {
		return this.getBlockedContacts().contains(number);
	}



}
