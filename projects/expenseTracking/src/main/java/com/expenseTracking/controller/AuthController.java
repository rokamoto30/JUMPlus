package com.expenseTracking.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.expenseTracking.model.CompareDTO;
import com.expenseTracking.model.Expense;
import com.expenseTracking.model.ExpenseCreateForm;
import com.expenseTracking.model.User;
import com.expenseTracking.service.ExpenseService;
import com.expenseTracking.service.UserService;
import com.expenseTracking.util.ColorUtil;
import com.expenseTracking.util.JsonMapper;
import com.expenseTracking.util.PrintUtil;

import jakarta.annotation.PostConstruct;

@Component
public class AuthController {
	
	
	@Autowired
	UserService userServiceAuto;
	private static UserService userService;
	
	@Autowired
	ExpenseService expenseServiceAuto;
	private static ExpenseService expenseService;
	
	@PostConstruct
	private void init() {
		userService = userServiceAuto;
		expenseService = expenseServiceAuto;
	}
	
	
	public static void run(User curUser, Scanner sc) {
		boolean running = true;
		String[] commands = new String[]{"add expense", "remove expense", "user info", "view recent expenses", "compare budget", "logout"};
		
		while(running) {
			System.out.println("\n");  
			PrintUtil.commandPrint(commands);
			ColorUtil.colorPrint("lightGreen", curUser.getUsername() + "> ");
			String command = sc.nextLine();		
			switch(command) {
				case("1"):
					JsonMapper.write(new ExpenseCreateForm());
					ColorUtil.colorPrintln("lightRed", "Enter expense details in form.json then press enter");
					command = sc.nextLine();		
					ExpenseCreateForm details = JsonMapper.read(ExpenseCreateForm.class);
					
					DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					LocalDate localDate = LocalDate.parse(details.getDate(), dateTimeFormatter);
					
					Expense newExpense = new Expense(localDate, details.getDescription(), details.getAmount(), details.isRecurring(), curUser);
					expenseService.create(newExpense);
					break;
				case("2"):
					ColorUtil.colorPrintln("lightRed", "Enter expense id");
					ColorUtil.colorPrint("lightGreen", "> ");
					command = sc.nextLine();
					expenseService.delete(Integer.parseInt(command));
					break;
				case("3"):
					int id = curUser.getId();
					JsonMapper.write(curUser);
					ColorUtil.colorPrintln("lightRed", "User info has been populated in form.txt");
					String[] saveCommands = new String[] {"save", "return"};
					PrintUtil.commandPrint(saveCommands);
					ColorUtil.colorPrint("lightGreen", curUser.getUsername() + "> ");
					command = sc.nextLine();
					switch(command) {
						case("1"):
							try {
								User newUser =  JsonMapper.read(User.class);
								newUser.setId(id);
								curUser = userService.update(newUser);
							} catch (Exception e) {
								ColorUtil.colorPrintln("red", e.getMessage());
							}
						default:
							break;
					}
					break;
				case("4"):
					try {
						List<Expense> expenses = expenseService.top5(curUser.getId());
						Object[] arr = expenses.toArray(new Object[expenses.size()]);
						
						PrintUtil.printObjects(arr);
					} catch (Exception e) {
						ColorUtil.colorPrintln("red", e.getMessage());
					}
					break;
				case("5"):
					JsonMapper.write(new CompareDTO());
					ColorUtil.colorPrintln("lightRed", "enter details in form.txt then press enter");
					ColorUtil.colorPrintln("lightRed", "leave month blank to search by year");
					command = sc.nextLine();
					CompareDTO compareDetails =  JsonMapper.read(CompareDTO.class);
					List<Expense> expenses = new ArrayList<Expense>();
					
					double total = 0;
					double budget = 0;
					
					try {
						if (compareDetails.getMonth().isBlank()) {
							expenses = expenseService.getYear(curUser.getId(), compareDetails.getYear());
							budget = curUser.getYearBudget();
							for (Expense exp: expenses) {
								if (exp.isRecurring()) {
									total += exp.getAmount() * 12;
								} else {
									total += exp.getAmount();
								}
							}
						} else {
							expenses = expenseService.getMonth(curUser.getId(), compareDetails.getMonth(), compareDetails.getYear());
							budget = curUser.getMonthBudget();
							for (Expense exp: expenses) {
								total += exp.getAmount();
							}
							
						}
					} catch (Exception e){
						System.out.println(e.getMessage());
					}
					
					Object[] arr = expenses.toArray(new Object[expenses.size()]);
					try {
						PrintUtil.printObjects(arr);
						System.out.print("Total expenses: ");
						ColorUtil.colorPrintln("yellow", String.valueOf(total));
						System.out.print("Budget: ");
						String budgetColor = "lightGreen";
						if (total > budget) {
							budgetColor = "lightRed";
						}
						ColorUtil.colorPrintln(budgetColor, String.valueOf(budget));
						
						
					} catch (Exception e) {
						ColorUtil.colorPrintln("red", e.getMessage());
					}
					break;
				case("6"):
					return;
				default:
					ColorUtil.colorPrintln("red", "Invalid command");
					break;
			}
		}
		
	}
	
}
