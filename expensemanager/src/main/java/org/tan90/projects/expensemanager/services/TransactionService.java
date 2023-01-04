package org.tan90.projects.expensemanager.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tan90.projects.expensemanager.exceptions.ResourceNotFoundException;
import org.tan90.projects.expensemanager.model.TransactionTO;

@Service
public interface TransactionService {

	public TransactionTO createTransaction(TransactionTO transactionTO) throws ResourceNotFoundException;
	
	public TransactionTO updateTransaction(long id, TransactionTO transactionTO) throws ResourceNotFoundException;
	
	public List<TransactionTO> getAllTransactions();
	
	public TransactionTO getTransaction(long id) throws ResourceNotFoundException;
	
	public void deleteTransaction(long id) throws ResourceNotFoundException;
	
}
