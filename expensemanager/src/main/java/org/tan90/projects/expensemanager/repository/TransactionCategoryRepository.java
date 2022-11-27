package org.tan90.projects.expensemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tan90.projects.expensemanager.repository.entities.TransactionCategory;

public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, Long>{

}
