
package cityapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import repository.Repository;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		Repository.init();
  }
}