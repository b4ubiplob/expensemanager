package org.tan90.projects.expensemanager.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tan90.projects.expensemanager.exceptions.ResourceNotFoundException;
import org.tan90.projects.expensemanager.model.CategoryTO;

@Service
public interface CategoryService {
	
	
	public CategoryTO createCategory(String name);
	
	public CategoryTO getCategory(long id) throws ResourceNotFoundException;
	
	public CategoryTO getCatgoryByName(String name) throws ResourceNotFoundException;
	
	public List<CategoryTO> getAllCategories();

	public void updateCategory(CategoryTO category) throws ResourceNotFoundException;
	
	public void deleteCategory(long id);
	
}
