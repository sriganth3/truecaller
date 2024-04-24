package com.truecaller;

import java.util.List;

import com.truecaller.exception.BlockLimitExceedException;
import com.truecaller.exception.ContactsExceededException;

public class TrueCallerApplication {
	
	public static void main(String[] args) throws ContactsExceededException, BlockLimitExceedException {
		
		
		// Register the User
		User account = new User();
		account.register("John","john123","john123@john.com","XXXXXX","9293457473","+91", UserCategory.FREE);
		
		
		// Add Contact to User
		account.addContact(new User("1234567890", "Alice"));
		account.addContact(new User("9876543210", "Bob"));
		account.addContact(new User("1112223334", "Ada"));
		account.addContact(new User("7775554440", "Adam"));
		account.addContact(new User("3336669998", "Adeline"));
		account.addContact(new User("8889991112", "Peter"));
		account.addContact(new User("6663332225", "Petra"));

		// Check Added Contacts Count
		System.out.println(account.getContacts().size());
		
		// Search for Contacts by Name
		System.out.println("Search for Contacts by Prefix Jcm");
		List<String> names = account.getContactTrie().allWordsWithPrefix("Jcm");
		System.out.println(names);
		
		System.out.println("Search for Contacts by Prefix Pet");
		names = account.getContactTrie().allWordsWithPrefix("Pet");
		System.out.println(names);

		System.out.println("Search for Contacts by Prefix Ada");
		names = account.getContactTrie().allWordsWithPrefix("Ada");
		System.out.println(names);

		System.out.println("Search for Contacts by Prefix A");
		names = account.getContactTrie().allWordsWithPrefix("A");
		System.out.println(names);
		

		account.addContact(new User("4447770001", "Blocked contact"));
		account.addContact(new User("4447756701", "Junk Caller"));
		
		// Blocking Contact
		account.blockContact("4447770001");
		account.blockContact("4447756701");
		
		System.out.println("Checking for blocked number: ");
		System.out.println(account.isBlocked("4447756701"));
		
		
	}
}
