package models;

import java.util.Date;

public class City {

	private String name;
	private String description;
	private int population;
	private Date createdDate;
	private Integer userNum = 0;

	public City(String name, String description, int population) {
	    this.name = name;
	    this.description = description;
	    this.population = population;
	    this.createdDate = new Date();
	}

	public String getName() {
		return name;
	}
	  
	public String getDescription() {
		return description;
	}

	public int getPopulation() {
		return population;
	}
	  
	  public Date getCreatedDate() {
		return createdDate;
	  }

	  public int getUserNum() {
		return userNum;
	  }
	
	  public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	  }

	  @Override
	  public boolean equals(Object obj) {
          if (this == obj) {
              return true;
          }
          if (obj == null || getClass() != obj.getClass()) {
              return false;
          }
          City city = (City) obj;
          return population == city.population &&
                  name.equals(city.name) && description.equals(city.description);
	  }
	  
	}