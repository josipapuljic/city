package models;

public class UserResult {
	private boolean success;
	private String tokenId;
	private String message;
	
	public UserResult(final boolean success, final String tokenId, final String message) {
		this.success = success;
		this.tokenId = tokenId;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getTokenId() {
		return tokenId;
	}

	public String getMessage() {
		return message;
	}
}
