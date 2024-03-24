package truecaller;

import java.util.List;

import truecaller.exception.ContactsExceededException;

public class TrueCallerApplication {
	
	public static void main(String[] args) throws ContactsExceededException {
		
		
		// Register the User
		User account = new User();
		account.register("John","john123","john123@john.com","XXXXXX","9293457473","+91", UserCategory.FREE);
		
		
		// Add Contact to User
		account.addContact(new User("1234567890", "Alice"));
		account.addContact(new User("9876543210", "Bob"));
		account.addContact(new User("5551234567", "Charlie"));
		account.addContact(new User("9998887776", "David"));
		account.addContact(new User("1112223334", "Ada"));
		account.addContact(new User("7775554440", "Adam"));
		account.addContact(new User("3336669998", "Adeline"));
		account.addContact(new User("4447770001", "Henry"));
		account.addContact(new User("8889991112", "Peter"));
		account.addContact(new User("6663332225", "Petra"));

		// Check Added Contacts Count
		System.out.println(account.getContacts().size());
		
		// Search for Contacts by Name
		List<String> names = account.getContactTrie().allWordsWithPrefix("Jcm");
		System.out.println(names);
		
		names = account.getContactTrie().allWordsWithPrefix("Pet");
		System.out.println(names);

		names = account.getContactTrie().allWordsWithPrefix("Ada");
		System.out.println(names);

		names = account.getContactTrie().allWordsWithPrefix("A");
		System.out.println(names);
		
		
	}

}
