package org.tan90.projects.expensemanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.tan90.projects.expensemanager.exceptions.ResourceNotFoundException;
import org.tan90.projects.expensemanager.model.CategoryTO;
import org.tan90.projects.expensemanager.services.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public List<CategoryTO> getAllCategories() {
		return categoryService.getAllCategories();
	}
	
	@GetMapping(value="/{id}")
	public CategoryTO getCategory(@PathVariable long id) {
		try  {
			return categoryService.getCategory(id);
		}
		catch (ResourceNotFoundException resourceNotFoundException) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, resourceNotFoundException.getMessage());
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryTO createCategory(@RequestBody CategoryTO category) {
		return categoryService.createCategory(category.getName());
	}

	@PutMapping(value = "/{id}")
	public void updateCategory(@PathVariable long id, @RequestBody CategoryTO category) {
		category.setId(id);
		try {
			categoryService.updateCategory(category);
		}
		catch (ResourceNotFoundException resourceNotFoundException) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, resourceNotFoundException.getMessage());
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public void deleteCategory(@PathVariable long id) {
		categoryService.deleteCategory(id);
	}
}
