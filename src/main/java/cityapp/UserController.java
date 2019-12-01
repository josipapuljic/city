package cityapp;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import models.City;
import models.CityResult;
import models.UserResult;
import models.User;
import repository.Repository;
import repository.UserRepository;

@RestController
public class UserController {

  @PostMapping("/user/register")
  public UserResult registerUser(@RequestBody User user) {
	  
/*	  try 
	  {	
		CreateRequest request = new CreateRequest()
				    .setEmail(user.getEmail())
				    .setEmailVerified(true)
				    .setPassword(user.getPassword())
				    .setDisplayName(user.getEmail())
				    .setDisabled(false);
		
		UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);*/
		String token = UserRepository.registerUser(user);
		  if ("".equals(token)) {
			  return new UserResult(false, null, "User already exists!");
		  }
		  return new UserResult(true, token, null); 
/*	  } 
	  catch (Exception e)
	  {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
	  } */
  }
  
  @PostMapping("/user/login")
  public UserResult logInUser(@RequestBody User user) {
	  String token = UserRepository.logInUser(user);
	  if ("".equals(token)) {
		  return new UserResult(false, null, "Unauthorized user");
	  }
	  return new UserResult(true, token, null); 
  }
  
/*  public static String authenticateUser(String tokenId) throws Exception {
	  String uid = null;
	  try 
	  {	
		uid = FirebaseAuth.getInstance().verifyIdTokenAsync(tokenId).get().getUid();
		return uid;
	  } 
	  catch (InterruptedException | ExecutionException e)
	  {
		  throw new Exception("User Not Authenticated");
	  }
  }
  */
/*  //javascript postom posalje token
  @RequestMapping(method = RequestMethod.POST, value = "/user/auth")
  public void authenticateUser(@RequestHeader(value="ID-TOKEN") String tokenId) throws Exception {
	  authenticateUser(tokenId);
  }
  */
  
  @PostMapping("/user/{token}/favoriteCity")
  public CityResult addFavoriteCity(@PathVariable String token, @RequestBody() City city) {
	  if (!UserRepository.isTokenValid(token))
		  throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is not valid", null);

	  String email = UserRepository.getEmail(token);
	  if (Repository.addFavoriteCityToMap(email, city))
		  return new CityResult(true, city, null);

	  return new CityResult(false, null, "Could not add city to favorites");
}
  
  @DeleteMapping("/user/{token}/favoriteCity")
  public CityResult deleteFavoriteCity(@PathVariable String token , @RequestBody() City city) {
	  if (!UserRepository.isTokenValid(token))
		  throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is not valid", null);

	  String email = UserRepository.getEmail(token);
	  if (Repository.deleteFavoriteCityFromMap(email, city))
		  return new CityResult(true, city, null);

	  return new CityResult(false, null, "Favorite city could not be deleted");
  }
  
  @RequestMapping("/user/{token}/favoriteCity")
  public List<City> getFavoriteCities(@PathVariable String token) {
	  if (!UserRepository.isTokenValid(token))
		  throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is not valid", null);

	  String email = UserRepository.getEmail(token);
	  return Repository.getUserFavoriteCities(email);
  } 
}
