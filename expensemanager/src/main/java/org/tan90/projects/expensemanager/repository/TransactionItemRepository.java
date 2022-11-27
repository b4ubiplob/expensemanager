package org.tan90.projects.expensemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tan90.projects.expensemanager.repository.entities.TransactionItem;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long>{

}
