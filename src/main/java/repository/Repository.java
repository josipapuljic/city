package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import comparator.CityDateComparator;
import comparator.CityUserNumComparator;
import models.City;

public class Repository {
	
	private static List<City> cityArray = new ArrayList<City>();
    private static Map<String, List<City>> favoriteCitiesMap = new HashMap<String, List<City>>();
	  
	public static void init() {
		cityArray.add(new City("Grad2","opis2",3));
		cityArray.add(new City("Grad1","opis1",10));
		cityArray.add(new City("Grad4","opis4",15));
		cityArray.add(new City("Grad5","opis5",200));
		cityArray.add(new City("Grad3","opis3",33));
		cityArray.add(new City("Grad6","opis6",336));
		cityArray.add(new City("Grad7","opis7",337));
		cityArray.add(new City("Grad8","opis8",338));
		cityArray.add(new City("Grad9","opis9",339));
		cityArray.add(new City("Grad10","opis10",3310));
	}
      
      public static List<City> getAllCities(){
		  return cityArray;
	  }
	  
	  public static boolean addCity(City newCity) {
		  if (!cityArray.contains(newCity)) {
			  cityArray.add(newCity);
			  return true;
		  }
		  return false;
	  }	

	  public static List<City> getAllCitiesSortedByDate () {
		  Collections.sort(cityArray, new CityDateComparator());
		  return cityArray;
	  }
	  
	  public static List<City> getAllCitiesSortedByUserNum () {
		  Collections.sort(cityArray, new CityUserNumComparator());
		  return cityArray;
	  }
	  
	  private static List<City> getFavoriteCities(final String email) {
		  return favoriteCitiesMap.get(email.toLowerCase());
	  }
	  
	  public static boolean addFavoriteCityToMap(String email, City favoriteCity) {
		  final City existingCity = getOrAdd(favoriteCity);
		  
		  List<City> existingFavoriteCitiesList = getFavoriteCities(email);
		  if (existingFavoriteCitiesList != null ) {
			  if (!existingFavoriteCitiesList.contains(existingCity)) {
				  existingFavoriteCitiesList.add(existingCity);
				  existingCity.setUserNum(existingCity.getUserNum() + 1);
				  return true;
			  }
			  return false;  
		  }
		  
		  List<City> favoriteCities = new ArrayList<City>();
		  favoriteCities.add(existingCity);
		  existingCity.setUserNum(existingCity.getUserNum() + 1);
		  favoriteCitiesMap.put(email.toLowerCase(), favoriteCities);
		  return true;
	  }
	  
	  private static City getExisting(City city) {
		  for (City existingCity:cityArray)
			  if (existingCity.equals(city))
				  return existingCity;
		  
		  return null;
	  }
	  private static City getOrAdd(City newCity) {
		  City result = getExisting(newCity);
		  if (result != null)
			  return result;

		  addCity(newCity);
		  return newCity;
	}

	public static boolean deleteFavoriteCityFromMap(String email, City favoriteCity) {
		  List<City> existingFavoriteCitiesList = getFavoriteCities(email);
		  if (existingFavoriteCitiesList != null) {
			  City existingCity = getExisting(favoriteCity);
			  if (existingCity == null)
				  return false;
			  
			  existingFavoriteCitiesList.remove(existingCity);
			  existingCity.setUserNum(existingCity.getUserNum() - 1);
			  return true;
		  }

		  return false;
	  }
	  
	  public static List<City> getUserFavoriteCities(String email){
		  List<City> existingFavoriteCitiesList = getFavoriteCities(email);
		  if (existingFavoriteCitiesList != null)
			  return existingFavoriteCitiesList;

		  return new ArrayList<City>();
	  }
}
