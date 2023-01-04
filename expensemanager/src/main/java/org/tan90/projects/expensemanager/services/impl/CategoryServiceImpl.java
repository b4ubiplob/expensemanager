package org.tan90.projects.expensemanager.services.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.tan90.projects.expensemanager.exceptions.ErrorMessageConstants;
import org.tan90.projects.expensemanager.exceptions.ResourceNotFoundException;
import org.tan90.projects.expensemanager.model.CategoryTO;
import org.tan90.projects.expensemanager.repository.TransactionCategoryRepository;
import org.tan90.projects.expensemanager.repository.entities.TransactionCategory;
import org.tan90.projects.expensemanager.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private TransactionCategoryRepository categoryRepository;
	
	@Override
	public CategoryTO createCategory(String name) {
		TransactionCategory transactionCategory = new TransactionCategory();
		transactionCategory.setName(name);
		TransactionCategory savedCategory = categoryRepository.save(transactionCategory);
		return TOConversionUtil.getCategoryTO(savedCategory);
	}

	@Override
	public CategoryTO getCategory(long id) throws ResourceNotFoundException{
		Optional<TransactionCategory> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			return TOConversionUtil.getCategoryTO(category.get());
		}
		throw new ResourceNotFoundException(MessageFormat.format(
				ErrorMessageConstants.CATEGORY_NOT_FOUND, id));
	}

	@Override
	public CategoryTO getCatgoryByName(String name) throws ResourceNotFoundException{
		TransactionCategory probe = new TransactionCategory();
		probe.setName(name);
		Example<TransactionCategory> example = Example.of(probe);
		Optional<TransactionCategory> optional = categoryRepository.findOne(example);
		if (optional.isPresent()) {
			return TOConversionUtil.getCategoryTO(optional.get());
		}
		throw new ResourceNotFoundException(MessageFormat.format(
				ErrorMessageConstants.CATEGORY_NOT_FOUND_BY_NAME, name));
	}

	@Override
	public List<CategoryTO> getAllCategories() {
		List<TransactionCategory> transactionCategories = categoryRepository.findAll();
		List<CategoryTO> categories = new ArrayList<>();
		transactionCategories.stream().forEach(
				(transactionCategory) -> categories.add(TOConversionUtil.getCategoryTO(transactionCategory))
			);
		return categories;
	}

	@Override
	public void updateCategory(CategoryTO category) throws ResourceNotFoundException {
		Optional<TransactionCategory> optional = categoryRepository.findById(category.getId());
		if (optional.isPresent()) {
			TransactionCategory transactionCategory = optional.get();
			transactionCategory.setName(category.getName());
			categoryRepository.save(transactionCategory);
		}
		else {
			throw new ResourceNotFoundException(MessageFormat.format(
					ErrorMessageConstants.CATEGORY_NOT_FOUND, category.getId()));
		}
		
	}

	@Override
	public void deleteCategory(long id) {
		categoryRepository.deleteById(id);
	}

}
