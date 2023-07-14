package com.expenseTracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenseTracking.exception.InvalidException;
import com.expenseTracking.model.Expense;
import com.expenseTracking.repo.ExpenseRepo;
import com.expenseTracking.repo.UserRepo;

@Service
public class ExpenseService {
	@Autowired
	ExpenseRepo repo;
	
	@Autowired
	UserRepo userRepo;
	
	public Expense create(Expense expense) {
		expense.setId(null);
		return repo.save(expense);
	}
	
	public void delete(Integer id) {
		repo.deleteById(id);
	}
	
	public List<Expense> getMonth(Integer userId, String month, String year) throws InvalidException {
		if(userRepo.findById(userId).isEmpty()) {
			throw new InvalidException("User doesnt exist");
		}
		return repo.getMonth(userId, month, year);
	}
	
	public List<Expense> getYear(Integer userId, String year) throws InvalidException {
		if(userRepo.findById(userId).isEmpty()) {
			throw new InvalidException("User doesnt exist");
		}
		return repo.getYear(userId, year);
	}
	
	public List<Expense> top5(Integer userId) throws InvalidException {
		if(userRepo.findById(userId).isEmpty()) {
			throw new InvalidException("User doesnt exist");
		}
		return repo.getTop5(userId);
	}
	
	
}
