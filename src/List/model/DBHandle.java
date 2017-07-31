package List.model;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class DBHandle {
	public void dbSetting() {
		try{
			// Initialize the app with a service account, granting admin privileges
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setDatabaseUrl("https://salelist-474d5.firebaseio.com")
					.setServiceAccount(new FileInputStream("salelist-474d5-firebase-adminsdk-07zy9-cef1007019.json"))
					.build();
			FirebaseApp.initializeApp(options);

		}catch(IOException e) {
			System.out.println(e);
		}
	}


}
