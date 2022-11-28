package org.tan90.projects.expensemanager.services;

import java.util.List;

import org.tan90.projects.expensemanager.model.CategoryTO;

public interface CategoryService {
	
	
	public CategoryTO createCategory(String name);
	
	public CategoryTO getCategory(long id);
	
	public CategoryTO getCatgoryByName(String name);
	
	public List<CategoryTO> getAllCategories();

	public void updateCategory(CategoryTO category);
	
	public void deleteCategory(long id);
	
}
