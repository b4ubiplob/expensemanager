package org.tan90.projects.expensemanager.services.impl;

import org.tan90.projects.expensemanager.model.CategoryTO;
import org.tan90.projects.expensemanager.model.TransactionItemTO;
import org.tan90.projects.expensemanager.model.TransactionTO;
import org.tan90.projects.expensemanager.repository.entities.Transaction;
import org.tan90.projects.expensemanager.repository.entities.TransactionCategory;
import org.tan90.projects.expensemanager.repository.entities.TransactionItem;

public class TOConversionUtil {
	
	private TOConversionUtil() {
	}

	public static TransactionTO getTransactionTO(Transaction transaction) {
		TransactionTO transactionTO = new TransactionTO();
		transactionTO.setAmount(transaction.getAmount());
		transactionTO.setCategory(getCategoryTO(transaction.getCategory()));
		transactionTO.setCurrentBalance(transaction.getCurrentBalance());
		transactionTO.setDate(transaction.getTransactionDate());
		transactionTO.setDescription(transaction.getDescription());
		transactionTO.setId(transaction.getId());
		transactionTO.setItem(getTransactionItemTO(transaction.getItem()));
		transactionTO.setSource(transaction.getSource());
		transactionTO.setType(transaction.getType());
		return transactionTO;
	}
	
	
	public static TransactionItemTO getTransactionItemTO(TransactionItem transactionItem) {
		TransactionItemTO transactionItemTO = new TransactionItemTO();
		transactionItemTO.setId(transactionItem.getId());
		transactionItemTO.setName(transactionItem.getName());
		return transactionItemTO;
	}
	
	public static CategoryTO getCategoryTO(TransactionCategory transactionCategory) {
		CategoryTO categoryTO = new CategoryTO();
		categoryTO.setId(transactionCategory.getId());
		categoryTO.setName(transactionCategory.getName());
		return categoryTO;
	}

	

}
