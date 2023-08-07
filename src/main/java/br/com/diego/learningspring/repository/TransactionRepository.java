package br.com.diego.learningspring.repository;

import br.com.diego.learningspring.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository
  extends JpaRepository<Transaction, Long> {}
