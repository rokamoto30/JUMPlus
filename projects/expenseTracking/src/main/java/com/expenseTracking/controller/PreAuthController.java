package com.expenseTracking.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.expenseTracking.model.User;
import com.expenseTracking.model.UserDTO;
import com.expenseTracking.service.UserService;
import com.expenseTracking.util.ColorUtil;
import com.expenseTracking.util.JsonMapper;
import com.expenseTracking.util.PrintUtil;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@Component
@Valid
public class PreAuthController {
	
	
	private static UserService service;
	@Autowired
	UserService service0;
	
	@PostConstruct
	private void init() {
		service = this.service0;
	}
	
	
	
	public static User auth(Scanner sc) {
		
		boolean auth = false;
		String[] commands = new String[]{"login", "sign up", "clear form"};
		
				
		JsonMapper.write(new UserDTO());
		
		
		while (auth == false) {
			System.out.println("\n");  

			
			ColorUtil.colorPrintln("lightRed", "Enter credentials in form.json");
			PrintUtil.commandPrint(commands);
			ColorUtil.colorPrint("lightGreen", "> ");
			String command = sc.nextLine();
			UserDTO user = JsonMapper.read(UserDTO.class);
			try {
				switch(command) {
					case "1":
						return service.login(user.getUsername(), user.getPassword());
					case "2":
						return service.create(new User(user.getUsername(), user.getPassword()));
					case "3":
						JsonMapper.write(new UserDTO());
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
