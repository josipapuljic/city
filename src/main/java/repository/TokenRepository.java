package repository;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import models.Token;

public class TokenRepository {
	
	private static Map<String, Token> tokenMap = new HashMap<String, Token>();
	
	public static String issueToken(String email) {
		final String normalizedEmail = email.toLowerCase();
		//da li token repository sadrzi email
		//ako ne sadrzi, ubaci ga u mapu i pridruzi mu token, vrati tokenid
		//ako sadrzi provjeri da li je token izdan unutar sat vremena, 
		//ako je vrati tokenId, 
		//ako je vise od sat vremena izdaj novi i azuriraj issueDate tokena za tog usera i vrati tokenId
		if (!tokenMap.containsKey(normalizedEmail)) {
			String tokenId = generateTokenId(normalizedEmail);
			tokenMap.put(normalizedEmail, new Token(tokenId,new Date()));
			return tokenId;
		}
		Token token = tokenMap.get(normalizedEmail);
		if (!isTokenExpired(token)) {
			return token.getTokenId();
		}
		String tokenId = generateTokenId(normalizedEmail);
		token.setTokenId(tokenId);
		token.setIssueDate(new Date());
		return tokenId;
	}

	private static boolean isTokenExpired(Token token) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, -1);
		Date oneHourAgo = calendar.getTime();
		
		return token.getIssueDate().before(oneHourAgo);
	}

	public static String generateTokenId(final String email) {
		return UUID.randomUUID().toString() + email;
	}

	public static boolean isTokenValid(String token) {
		if (token == null || "".equals(token))
			return false;
		for (Map.Entry<String, Token> entry:tokenMap.entrySet())
			if (token.equals(entry.getValue().getTokenId()))
				return !isTokenExpired(entry.getValue());
		
		return false;
	}

	public static String getEmail(String token) {
		if (token == null || "".equals(token))
			return "";
		for (Map.Entry<String, Token> entry:tokenMap.entrySet())
			if (token.equals(entry.getValue().getTokenId()))
				return entry.getKey();
		
		return "";
	}
}
