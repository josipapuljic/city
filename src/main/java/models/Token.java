package models;

import java.util.Date;

public class Token {
	private String tokenId;
	private Date issueDate;
	
	public Token(String tokenId, Date date) {
		this.tokenId = tokenId;
		this.issueDate = date;
	}
	
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	
}
