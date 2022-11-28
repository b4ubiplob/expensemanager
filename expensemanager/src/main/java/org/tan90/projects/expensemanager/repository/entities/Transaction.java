package org.tan90.projects.expensemanager.repository.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "amount", nullable = false, unique = false)
	private double amount;
	
	@Column(name="description", length=1024, nullable=true, unique = false)
	private String description;
	
	@Column(name="transaction_date", nullable=false, unique = false)
	private Date transactionDate;
	
	@Enumerated(EnumType.STRING)
	private TransactionSource source;
	
	@Enumerated(EnumType.STRING)
	private TransactionType type;
	
	@Column(name="current_balance", nullable=false, unique=false)
	private double currentBalance;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id", nullable=false)
	private TransactionCategory category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="transaction_item_id", nullable = false)
	private TransactionItem item;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public TransactionSource getSource() {
		return source;
	}

	public void setSource(TransactionSource source) {
		this.source = source;
	}

	public TransactionCategory getCategory() {
		return category;
	}

	public void setCategory(TransactionCategory category) {
		this.category = category;
	}

	public TransactionItem getItem() {
		return item;
	}

	public void setItem(TransactionItem item) {
		this.item = item;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", description=" + description + ", transactionDate="
				+ transactionDate + ", source=" + source + ", type=" + type + ", category=" + category + ", item="
				+ item + "]";
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	
}
