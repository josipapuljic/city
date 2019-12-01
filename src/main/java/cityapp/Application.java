
package cityapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import repository.Repository;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		FileInputStream refreshToken;
		try {
			refreshToken = new FileInputStream("userauthentication-6887.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
				    .setCredentials(GoogleCredentials.fromStream(refreshToken))
				    .build();

			//if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
			//}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Repository.init();

  }
}