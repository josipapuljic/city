package models;

public class CityResult {
	private boolean success;
	private City city;
	private String message;

	public CityResult(final boolean success, final City city, final String message) {
		this.success = success;
		this.city = city;
		this.message = message;
	}
	
	public City getCity() {
		return city;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
}
