package org.tan90.projects.expensemanager.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		return getCategoryTO(savedCategory);
	}

	private CategoryTO getCategoryTO(TransactionCategory transactionCategory) {
		CategoryTO categoryTO = new CategoryTO();
		categoryTO.setId(transactionCategory.getId());
		categoryTO.setName(transactionCategory.getName());
		return categoryTO;
	}
	
	private TransactionCategory getTransactionCategory(CategoryTO categoryTO) {
		TransactionCategory transactionCategory  = new TransactionCategory();
		transactionCategory.setId(categoryTO.getId());
		transactionCategory.setName(categoryTO.getName());
		return transactionCategory;
	}

	@Override
	public CategoryTO getCategory(long id) {
		Optional<TransactionCategory> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			return getCategoryTO(category.get());
		}
		throw new IllegalArgumentException("Category not found");
	}

	@Override
	public CategoryTO getCatgoryByName(String name) {
		return null;
	}

	@Override
	public List<CategoryTO> getAllCategories() {
		List<TransactionCategory> transactionCategories = categoryRepository.findAll();
		List<CategoryTO> categories = new ArrayList<>();
		transactionCategories.stream().forEach(
				(transactionCategory) -> categories.add(getCategoryTO(transactionCategory))
			);
		return categories;
	}

	@Override
	public void updateCategory(CategoryTO category) {
		categoryRepository.save(getTransactionCategory(category));
		
	}

	@Override
	public void deleteCategory(long id) {
		categoryRepository.deleteById(id);
	}

}
