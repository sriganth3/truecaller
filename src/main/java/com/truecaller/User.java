package com.truecaller;

import java.util.HashMap;
import java.util.UUID;

import com.truecaller.common.Constants;
import com.truecaller.exception.BlockLimitExceedException;
import com.truecaller.exception.ContactsExceededException;

import orestes.bloomfilter.FilterBuilder;

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
		this.setGlobalSpam(new GlobalSpam());


	}

	private void init(UserCategory userCategory) {
		switch(userCategory) {
		case FREE:
			this.setContacts(new HashMap<>(Constants.MAX_FREE_USER_CONTACTS));
			
			this.setBlockedContacts(new FilterBuilder(Constants.MAX_FREE_USER_BLOCKED_CONTACTS, .01)
                    .buildCountingBloomFilter());
			break;
		case BASIC:
			this.setContacts(new HashMap<>(Constants.MAX_BASIC_USER_CONTACTS));
			this.setBlockedContacts(new FilterBuilder(Constants.MAX_BASIC_USER_CONTACTS, .01)
                    .buildCountingBloomFilter());
			break;
		case PREMIUM:
			this.setContacts(new HashMap<>(Constants.MAX_PREMIUM_USER_CONTACTS));
			this.setBlockedContacts(new FilterBuilder(Constants.MAX_PREMIUM_USER_CONTACTS, .01)
                    .buildCountingBloomFilter());
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

	@Override
	public boolean canRecieve(String number) {
		
		return !this.getBlockedContacts().contains(number) && !this.getGlobalSpam().isGlobalSpam(number);
	}

	@Override
	public void reportSpam(String number, String reason) {
		
		this.getBlockedContacts().add(number);
		this.getGlobalSpam().reportSpam(number, this.getPhoneNumber(), reason);
	}
	
	
	
	



}
