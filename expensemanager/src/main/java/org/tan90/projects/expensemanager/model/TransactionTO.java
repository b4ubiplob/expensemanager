package org.tan90.projects.expensemanager.model;

import java.util.Date;

import org.tan90.projects.expensemanager.repository.entities.TransactionSource;
import org.tan90.projects.expensemanager.repository.entities.TransactionType;

public class TransactionTO {
	
	private long id;
	private double amount;
	private double currentBalance;
	private Date date;
	private String description;
	private TransactionSource source;
	private TransactionType type;
	private TransactionItemTO item;
	private CategoryTO category;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TransactionSource getSource() {
		return source;
	}
	public void setSource(TransactionSource source) {
		this.source = source;
	}
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	public TransactionItemTO getItem() {
		return item;
	}
	public void setItem(TransactionItemTO item) {
		this.item = item;
	}
	public CategoryTO getCategory() {
		return category;
	}
	public void setCategory(CategoryTO category) {
		this.category = category;
	}
	

}
