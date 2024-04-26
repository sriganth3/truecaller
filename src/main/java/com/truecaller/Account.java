package com.truecaller;

import java.util.Map;
import java.util.Set;

import com.truecaller.exception.BlockLimitExceedException;
import com.truecaller.exception.ContactsExceededException;

import orestes.bloomfilter.CountingBloomFilter;

public abstract class Account {
	public ContactTrie getContactTrie() {
		return contactTrie;
	}

	public void setContactTrie(ContactTrie contactTrie) {
		this.contactTrie = contactTrie;
	}

	private String id;
	private String phoneNumber;
	private String userName;
	private String password;
	private String email;
	private UserCategory userCategory;
	private PersonalInfo personalInfo;
	private Contact contact;
	private ContactTrie contactTrie;
	private Map<String, User> contacts;
	private CountingBloomFilter<String> blockedContacts;
	private GlobalSpam globalSpam;
	
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

	public UserCategory getUserCategory() {
		return userCategory;
	}
	
	public void setUserCategory(UserCategory userCategory) {
		this.userCategory = userCategory;
	}

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
	
	public Map<String, User> getContacts() {
		return contacts;
	}

	public void setContacts(Map<String, User> contacts) {
		this.contacts = contacts;
	}

	public CountingBloomFilter<String> getBlockedContacts() {
		return blockedContacts;
	}

	public void setBlockedContacts(CountingBloomFilter<String> blockedContacts) {
		this.blockedContacts = blockedContacts;
	}

	public GlobalSpam getGlobalSpam() {
		return globalSpam;
	}

	public void setGlobalSpam(GlobalSpam globalSpam) {
		this.globalSpam = globalSpam;
	}

	public abstract void register(String firstName, String userName, 
			String email, String password, String phoneNumber, String countryCode, UserCategory userCategory);
	
	public abstract void addContact(User user) throws ContactsExceededException;
	
	public abstract void blockContact(String number) throws BlockLimitExceedException;
	
	public abstract boolean isBlocked(String number);
	
	public abstract boolean canRecieve(String number);
	
	public abstract void reportSpam(String number, String reason);

	
}
