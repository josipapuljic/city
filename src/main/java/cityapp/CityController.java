package cityapp;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import models.City;
import models.CityResult;
import repository.Repository;
import repository.UserRepository;

@RestController
public class CityController {

  @RequestMapping("/city")
  public List<City> getAllCities() {
    return Repository.getAllCities();
  }
  
  @RequestMapping("/city/sortedByDate")
  public List<City> getAllCitiesSortedByDate() {
    return Repository.getAllCitiesSortedByDate();
  }
  
  @RequestMapping("/city/sortedByUser")
  public List<City> getAllCitiesSortedByUserNum() {
    return Repository.getAllCitiesSortedByUserNum();
  }

  @PostMapping("/city/{token}")
  public CityResult createCity(@PathVariable() String token, @RequestBody() City city) {
	  if ("".equals(city.getName()))
		  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be empty string!", null);
	  if ("".equals(city.getDescription()))
		  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Description cannot be empty string!", null);
	  if (city.getPopulation() <= 0)
		  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You need to input all 3 params to create a city!", null);
	  
	  if (!UserRepository.isTokenValid(token))
		  throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is not valid", null);
 
	  if (Repository.addCity(city))
		  return new CityResult(true, city, null);

	  return new CityResult(false, null, "City could not be created");
  }
}
