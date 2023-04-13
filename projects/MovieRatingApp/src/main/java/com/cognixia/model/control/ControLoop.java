package com.cognixia.model.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognixia.exception.InvalidException;
import com.cognixia.model.User;
import com.cognixia.repo.MovieRepo;
import com.cognixia.repo.RatingRepo;
import com.cognixia.repo.UserRepo;
import com.cognixia.service.UserService;

import jakarta.transaction.Transactional;

public class ControLoop {
	@Autowired
	private UserService userService;
	@Autowired
	private MovieRepo movieRepo;
	@Autowired
	private RatingRepo ratingRepo;
	
	private Scanner sc;
	
	private String curUser;
	
	private final String COOKIE_PATH = getClass().getResource("cookie.txt").getPath();

	public ControLoop() {
		sc = new Scanner(System.in); // create scanner
		try(BufferedReader reader = new BufferedReader(new FileReader(new File(COOKIE_PATH)))) { // load cached user
			String savedUser = reader.readLine();
			if (savedUser == null) {
				curUser = "guest";
				System.out.println("Login with 'login' to continue!");
			} else {
				curUser = savedUser;
				System.out.println("Welcome back " + savedUser);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Scanner getSc() {
		return sc;
	}

	public String getCurUser() {
		return curUser;
	}

	public void setCurUser(String curUser) {
		this.curUser = curUser;
	}

	@Transactional
	public void controLoop() {
		
		boolean running = true;
		while(running) {
			System.out.print(curUser + "> ");
			String command = sc.nextLine(); 
			switch(command.toLowerCase()) {
			case "login":
				login();
				break;
			}
		}
	}
	
	
	
//	public void addUser() {
//		try {
//			boolean loop = true;
//			while (true) {
//				
//			}
//			System.out.println("Enter Username:");
//			String username = sc.nextLine();
//			
//			if (userService.userExists(username) ) {
//				
//				return;
//			}
//			
//			System.out.println("Enter Password:");
//			String password = sc.nextLine();
//			
//			System.out.println("Enter email:");
//			String email = sc.nextLine();
//			
//			User newUser = new User(null, username, password, email, null);
//			User userService.createUser(newUser);
//			
//			if (success == false) {
//				System.out.println("Can't create user");
//			} else {
//				user = username;
//				System.out.println("Logged in as " + user);
//				
//				//update cookies
//				try(BufferedWriter writer = new BufferedWriter( new FileWriter(new File(cookiePath), false))) {
//					writer.write(user);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		} catch (Exception e) { // TODO: custom exception for invalid login
//			e.printStackTrace();
//		}
//	}
//	
//	private static void createUser(User newUser) {
//		// TODO Auto-generated method stub
//		
//	}

	public void login(){
		boolean loop = true;
		while(loop) {
			try {
				System.out.println("Enter Username:");
				String username = sc.nextLine();
				
				System.out.println("Enter Password:");
				String password = sc.nextLine();
				curUser = userService.login(username, password).getUsername();
				
				// if exception not thrown, then success
				System.out.println("Logged in as " + curUser);
				
				//update cookies
				try(BufferedWriter writer = new BufferedWriter( new FileWriter(new File(COOKIE_PATH), false))) {
					writer.write(curUser);
				} catch (Exception e) {
					e.printStackTrace();
				}
				loop = false;
        
			} catch (InvalidException e) { // TODO: custom exception for invalid login
				System.out.println( e.getMessage());
				System.out.println("Press q to quit or any other key to try again:");
				
				String ans = sc.nextLine();
				if (ans.toLowerCase().equals("q")) {
					System.out.println("Exiting");
					loop = false; 
				}
			}
		}
	}
	
	

}
