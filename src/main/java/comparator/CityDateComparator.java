package comparator;

import java.util.Comparator;

import models.City;

public class CityDateComparator implements Comparator< City > {

	@Override
	public int compare(City c1, City c2) {
		// TODO Auto-generated method stub
		return c1.getCreatedDate().compareTo(c2.getCreatedDate());
	}
	  
}