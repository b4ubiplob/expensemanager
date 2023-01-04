package org.tan90.projects.expensemanager.services.impl;

import java.sql.Date;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.tan90.projects.expensemanager.exceptions.ErrorMessageConstants;
import org.tan90.projects.expensemanager.exceptions.ResourceNotFoundException;
import org.tan90.projects.expensemanager.model.TransactionTO;
import org.tan90.projects.expensemanager.repository.TransactionCategoryRepository;
import org.tan90.projects.expensemanager.repository.TransactionItemRepository;
import org.tan90.projects.expensemanager.repository.TransactionRepository;
import org.tan90.projects.expensemanager.repository.entities.Transaction;
import org.tan90.projects.expensemanager.repository.entities.TransactionCategory;
import org.tan90.projects.expensemanager.repository.entities.TransactionItem;
import org.tan90.projects.expensemanager.services.TransactionService;

public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionCategoryRepository transactionCategoryRepository;
	
	@Autowired
	private TransactionItemRepository transactionItemRepository;
	

	@Override
	public TransactionTO createTransaction(TransactionTO transactionTO) throws ResourceNotFoundException {
		Transaction transaction = createTransactionEntity(transactionTO);
		return TOConversionUtil.getTransactionTO(transactionRepository.save(transaction));
	}
	

	@Override
	public TransactionTO updateTransaction(long id, TransactionTO transactionTO) throws ResourceNotFoundException {
		Optional<Transaction> optional = transactionRepository.findById(id);
		if (optional.isPresent()) {
			Transaction transaction = optional.get();
			updateTransactionEntity(transaction, transactionTO);
			return TOConversionUtil.getTransactionTO(transactionRepository.save(transaction));
		}
		else {
			throw new ResourceNotFoundException(MessageFormat.format(ErrorMessageConstants.TRANSACTION_NOT_FOUND, id));
		}
		
	}

	@Override
	public List<TransactionTO> getAllTransactions() {
		List<TransactionTO> transactions = new ArrayList<>();
		transactionRepository.findAll().stream().forEach( 
				transaction -> transactions.add(TOConversionUtil.getTransactionTO(transaction)));
		return transactions;
	}

	@Override
	public TransactionTO getTransaction(long id) throws ResourceNotFoundException {
		Optional<Transaction> optional = transactionRepository.findById(id);
		if (optional.isPresent()) {
			return TOConversionUtil.getTransactionTO(optional.get());
		}
		throw new ResourceNotFoundException(MessageFormat.format(ErrorMessageConstants.TRANSACTION_NOT_FOUND, id));
	}

	@Override
	public void deleteTransaction(long id) throws ResourceNotFoundException {
		Optional<Transaction> optional = transactionRepository.findById(id);
		if (optional.isPresent()) {
			transactionRepository.delete(optional.get());
		}
		throw new ResourceNotFoundException(MessageFormat.format(ErrorMessageConstants.TRANSACTION_NOT_FOUND, id));
	}
	
	private Transaction createTransactionEntity(TransactionTO transactionTO) throws ResourceNotFoundException {
		Transaction transaction = new Transaction();
		return updateTransactionEntity(transaction, transactionTO);
	}
	
	private Transaction updateTransactionEntity(Transaction transaction, TransactionTO transactionTO) throws ResourceNotFoundException {
		transaction.setAmount(transaction.getAmount());
		Optional<TransactionCategory> optional = transactionCategoryRepository.findById(transactionTO.getCategory().getId());
		if (optional.isPresent()) {
			transaction.setCategory(optional.get());
		}
		else {
			throw new ResourceNotFoundException(MessageFormat.format(
					ErrorMessageConstants.CATEGORY_NOT_FOUND, transactionTO.getCategory().getId()));
		}
		
		//TODO: calculate the current balance
		transaction.setCurrentBalance(0);
		transaction.setDescription(transactionTO.getDescription());
		Optional<TransactionItem> itemOptional = transactionItemRepository.findById(transactionTO.getItem().getId());
		if (itemOptional.isPresent()) {
			transaction.setItem(itemOptional.get());
		}
		else {
			throw new ResourceNotFoundException(MessageFormat.format(
					ErrorMessageConstants.TRANSACTION_ITEM_NOT_FOUND, transactionTO.getItem().getId()));
		}
		transaction.setSource(transactionTO.getSource());
		transaction.setTransactionDate(new Date(new java.util.Date().getTime()));
		transaction.setType(transactionTO.getType());
		
		return transaction;
	}
	
	

}
