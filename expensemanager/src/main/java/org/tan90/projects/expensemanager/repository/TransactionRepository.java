package org.tan90.projects.expensemanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.tan90.projects.expensemanager.repository.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
