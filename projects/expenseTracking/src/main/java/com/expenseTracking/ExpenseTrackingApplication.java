package com.expenseTracking;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.expenseTracking.controller.AuthController;
import com.expenseTracking.controller.PreAuthController;
import com.expenseTracking.model.User;

@SpringBootApplication
public class ExpenseTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackingApplication.class, args);
		driver();
		
	}
	
	private static void driver() {
		Scanner sc = new Scanner(System.in);
		User curUser = null;
		boolean running = true;
		
		while (running) {
			if (curUser == null) {
				curUser = PreAuthController.auth(sc);
				System.out.println("logged in as: " + curUser.getUsername());
			} else {
				AuthController.run(curUser, sc);
				curUser = null;
			}
		}
		
		
		
		
	}
	


}
