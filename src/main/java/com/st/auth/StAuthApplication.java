package com.st.auth;

import com.st.auth.config.DdlAutoValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StAuthApplication {

	public static void main(String[] args) {
		//SpringApplication.run(StAuthApplication.class, args);
		SpringApplication app =
				new SpringApplication(StAuthApplication.class);
		app.addInitializers(new DdlAutoValidator());
		app.run(args);
	}

}
