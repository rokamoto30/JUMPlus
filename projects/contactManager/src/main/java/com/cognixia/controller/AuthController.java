package com.cognixia.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognixia.model.User;
import com.cognixia.service.AppService;
import com.expenseTracking.util.ColorUtil;
import com.expenseTracking.util.PrintUtil;

import jakarta.annotation.PostConstruct;
import jakarta.validation.ValidationException;


@Component
public class AuthController {
	private static AppService service;
	@Autowired
	AppService service0;
	
	@PostConstruct
	private void init() {
		service = this.service0;
	}
	
	public static User run(Scanner sc) {
		boolean auth = false;
		String[] commands = new String[]{"login", "sign up"};
		
		String email;
		String password;
		
		while (auth == false) {
			PrintUtil.commandPrint(commands);
			ColorUtil.colorPrint("lightGreen", "> ");
			String command = sc.nextLine();
			
			try {
				switch(command) {
					case "1":
						ColorUtil.colorPrint("lightGreen", "Enter email> ");
						email = sc.nextLine();
						ColorUtil.colorPrint("lightGreen", "Enter password> ");
						password = sc.nextLine();
						return service.login(new User(0, email, password));
					case "2":
						ColorUtil.colorPrint("lightGreen", "Enter email> ");
						email = sc.nextLine();
						ColorUtil.colorPrint("lightGreen", "Enter password> ");
						password = sc.nextLine();
						return service.createUser(new User(0, email, password));
					default:
						ColorUtil.colorPrintln("red", "Invalid command");
						break;
					
				}
			} catch (ValidationException e) {
				ColorUtil.colorPrintln("red", "Fields can not be blank");
			} catch (Exception e) {
				ColorUtil.colorPrintln("red", e.getMessage());
			}
			
		}
		return null;
	}
}
