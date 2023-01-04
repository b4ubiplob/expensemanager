package org.tan90.projects.expensemanager.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tan90.projects.expensemanager.model.TransactionTO;

@Service
public interface TransactionService {

	public TransactionTO createTransaction(TransactionTO transactionTO);
	
	public TransactionTO updateTransaction(long id, TransactionTO transactionTO);
	
	public List<TransactionTO> getAllTransactions();
	
	public TransactionTO getTransaction(long id);
	
	public void deleteTransaction(long id);
	
}
