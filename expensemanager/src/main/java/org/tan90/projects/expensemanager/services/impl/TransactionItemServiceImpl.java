package org.tan90.projects.expensemanager.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.tan90.projects.expensemanager.exceptions.ErrorMessageConstants;
import org.tan90.projects.expensemanager.exceptions.ResourceNotFoundException;
import org.tan90.projects.expensemanager.model.TransactionItemTO;
import org.tan90.projects.expensemanager.repository.TransactionItemRepository;
import org.tan90.projects.expensemanager.repository.entities.TransactionItem;
import org.tan90.projects.expensemanager.services.TransactionItemService;

public class TransactionItemServiceImpl implements TransactionItemService {

	@Autowired
	private TransactionItemRepository transactionItemRepository;
	
	@Override
	public TransactionItemTO createTransactionItem(String name) {
		TransactionItem transactionItem = new TransactionItem();
		transactionItem.setName(name);
		TransactionItem savedItem = transactionItemRepository.save(transactionItem);
		return getTransactionItemTO(savedItem);
	}
	
	private TransactionItemTO getTransactionItemTO(TransactionItem transactionItem) {
		TransactionItemTO transactionItemTO = new TransactionItemTO();
		transactionItemTO.setId(transactionItem.getId());
		transactionItemTO.setName(transactionItem.getName());
		return transactionItemTO;
	}

	@Override
	public List<TransactionItemTO> getAllTransactionItems() {
		List<TransactionItemTO> transactionItemTOs = new ArrayList<>();
		transactionItemRepository.findAll().stream().forEach(
				(transactionItem) -> transactionItemTOs.add(getTransactionItemTO(transactionItem))
			);
		return transactionItemTOs;
	}

	@Override
	public TransactionItemTO getTransactionItem(long id) throws ResourceNotFoundException {
		Optional<TransactionItem> optional = transactionItemRepository.findById(id);
		if (optional.isPresent()) {
			return getTransactionItemTO(optional.get());
		}
		throw new ResourceNotFoundException(ErrorMessageConstants.TRANSACTION_ITEM_NOT_FOUND);
	}

	@Override
	public TransactionItemTO getTransactionItemByName(String name) throws ResourceNotFoundException {
		TransactionItem probe = new TransactionItem();
		probe.setName(name);
		Example<TransactionItem> example = Example.of(probe);
		Optional<TransactionItem> optional = transactionItemRepository.findOne(example);
		if (optional.isPresent()) {
			return getTransactionItemTO(optional.get());	
		}
		throw new ResourceNotFoundException(ErrorMessageConstants.TRANSACTION_ITEM_NOT_FOUND);
	}

	@Override
	public void updateTransactionItem(TransactionItemTO transactionItemTO) throws ResourceNotFoundException {
		Optional<TransactionItem> optional = transactionItemRepository.findById(transactionItemTO.getId());
		if (optional.isPresent()) {
			TransactionItem transactionItem = optional.get();
			transactionItem.setName(transactionItemTO.getName());
			transactionItemRepository.save(transactionItem);
		}
		else {
			throw new ResourceNotFoundException(ErrorMessageConstants.TRANSACTION_ITEM_NOT_FOUND);
		}
		
	}

	@Override
	public void deleteTransactionItem(long id) {
		transactionItemRepository.deleteById(id);
	}

}
