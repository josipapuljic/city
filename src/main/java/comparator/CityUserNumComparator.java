package comparator;

import java.util.Comparator;

import models.City;

public class CityUserNumComparator implements Comparator< City > {

	@Override
	public int compare(City c1, City c2) {
		// TODO Auto-generated method stub
		int ret = 0;
		
		if (c1.getUserNum() >= c2.getUserNum()) ret= -1;
		if (c1.getUserNum() <= c2.getUserNum()) ret = 1;
		
		return ret;
	}

}