package com.expenseTracking.model;

import java.time.LocalDate;

public class ExpenseCreateForm {
	private String date;
	private String description;
	private Double amount;
	private boolean recurring;
	
	public ExpenseCreateForm() {
		date = "MM/dd/YYYY";
		description = "";
		amount = 0.00;
		recurring = false;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public boolean isRecurring() {
		return recurring;
	}

	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	@Override
	public String toString() {
		return "ExpenseCreateForm [date=" + date + ", description=" + description + ", amount=" + amount
				+ ", recurring=" + recurring + "]";
	}
	
	
}
