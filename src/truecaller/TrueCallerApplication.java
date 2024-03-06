package truecaller;

public class TrueCallerApplication {
	
	public static void main(String[] args) {
		
		
		// Register the User
		User account = new User();
		account.register("John","john123","john123@john.com","XXXXXX","9293457473","+91", UserCategory.FREE);
		
		System.out.println(account.getContactTrie().getRoot());
		
	}

}
