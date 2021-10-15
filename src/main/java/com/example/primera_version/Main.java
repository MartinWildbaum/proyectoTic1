package com.example.primera_version;

import com.example.primera_version.ui.JavaFXApplication;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Main {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {

		Main.context = SpringApplication.run(Main.class, args);

		Application.launch(JavaFXApplication.class, args);
	}
	public static ConfigurableApplicationContext getContext(){
		return context;
}
}
