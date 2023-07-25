package com.cognixia.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognixia.model.Contact;
import com.cognixia.model.User;
import com.cognixia.service.AppService;
import com.expenseTracking.util.ColorUtil;
import com.expenseTracking.util.PrintUtil;

import jakarta.annotation.PostConstruct;
import jakarta.validation.ValidationException;

@Component
public class PostAuthController {
	@Autowired
	AppService appServiceAuto;
	private static AppService service;
	
	@PostConstruct
	private void init() {
		service = appServiceAuto;
	}
	
	public static void run(User curUser, Scanner sc) {
		boolean running = true;
		String[] commands = new String[]{"add contact", "update contact", "remove contact", "sort by id", "sort by name", "sort by recent", "logout"};
		String name;
		String number;
		String id;
		List<Contact> contacts;
		Object[] arr;
		
		try {
			while(running) {
				System.out.println("\n");  
				PrintUtil.commandPrint(commands);
				ColorUtil.colorPrint("lightGreen", curUser.getEmail()+ "> ");
				String command = sc.nextLine();	
				
				switch (command) {
				case("1"):
					ColorUtil.colorPrint("lightGreen", "Enter name> ");
					name = sc.nextLine();
					ColorUtil.colorPrint("lightGreen", "Enter number> ");
					number = sc.nextLine();
					service.createContact(new Contact(0, name, number, LocalDate.now(),curUser));
					break;
				case("2"):
					ColorUtil.colorPrint("lightGreen", "Enter id> ");
					id = sc.nextLine();
					ColorUtil.colorPrint("lightGreen", "Enter name> ");
					name = sc.nextLine();
					ColorUtil.colorPrint("lightGreen", "Enter number> ");
					number = sc.nextLine();
					service.updateContact(new Contact(Integer.parseInt(id), name, number, LocalDate.now(),curUser));
					break;
				case("3"):
					ColorUtil.colorPrint("lightGreen", "Enter id> ");
					id = sc.nextLine();
					service.deleteContact(Integer.parseInt(id));
					break;
				case("4"):
					contacts = service.sortId(curUser);
					arr = contacts.toArray(new Object[contacts.size()]);
					PrintUtil.printObjects(arr);
					break;
				case("5"):
					contacts = service.sortName(curUser);
					arr = contacts.toArray(new Object[contacts.size()]);
					PrintUtil.printObjects(arr);
					break;
				case("6"):
					contacts = service.sortCreated(curUser);
					arr = contacts.toArray(new Object[contacts.size()]);
					PrintUtil.printObjects(arr);
					break;
				case("7"):
					return;
				default:
					ColorUtil.colorPrintln("red", "Invalid command");
					break;
				}
			}
		} catch (ValidationException e) {
			ColorUtil.colorPrintln("red", "Fields can not be blank");
		} catch (Exception e) {
			ColorUtil.colorPrintln("red", e.getMessage());
		}
	}
}
