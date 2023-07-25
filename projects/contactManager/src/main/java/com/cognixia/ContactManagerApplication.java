package com.cognixia;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cognixia.controller.AuthController;
import com.cognixia.controller.PostAuthController;
import com.cognixia.model.User;

@SpringBootApplication
public class ContactManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactManagerApplication.class, args);
		driver();
	}
	private static void driver() {
		Scanner sc = new Scanner(System.in);
		User curUser = null;
		boolean running = true;
		
		while (running) {
			if (curUser == null) {
				curUser = AuthController.run(sc);
				System.out.println("logged in as: " + curUser.getEmail());
			} else {
				PostAuthController.run(curUser, sc);
				curUser = null;
			}
		}
		
	}

}
