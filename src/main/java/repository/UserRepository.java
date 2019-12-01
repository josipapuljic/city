package repository;

import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserRepository {
	
	private static List<User> userList = new ArrayList<User>();
	
	public static String registerUser(User user) {
		if (!contains(user.getEmail())) {
			userList.add(user);
			return logInUser(user);
		}	
		return "";
	}
	
	private static boolean contains(String email) {
		for (User user:userList)
			if (user.getEmail().equalsIgnoreCase(email))
				return true;

		return false;
	}
	
	public static String logInUser(User user) {
		if (userList.contains(user)) {
			return TokenRepository.issueToken(user.getEmail());
		} 
		return "";
	}

	public static boolean isTokenValid(String token) {
		return TokenRepository.isTokenValid(token);
	}

	public static String getEmail(String token) {
		return TokenRepository.getEmail(token);
	}
}
