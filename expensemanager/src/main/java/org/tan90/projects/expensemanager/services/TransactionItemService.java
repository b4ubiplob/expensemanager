package org.tan90.projects.expensemanager.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tan90.projects.expensemanager.exceptions.ResourceNotFoundException;
import org.tan90.projects.expensemanager.model.TransactionItemTO;

@Service
public interface TransactionItemService {

	public TransactionItemTO createTransactionItem(String name);
	
	public List<TransactionItemTO> getAllTransactionItems();
	
	public TransactionItemTO getTransactionItem(long id) throws ResourceNotFoundException;
	
	public TransactionItemTO getTransactionItemByName(String name) throws ResourceNotFoundException;
	
	public void updateTransactionItem(TransactionItemTO transactionItemTO) throws ResourceNotFoundException;
	
	public void deleteTransactionItem(long id);
	
}
