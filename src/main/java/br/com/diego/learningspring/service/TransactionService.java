package br.com.diego.learningspring.service;

import br.com.diego.learningspring.dto.CreateTransactionDto;
import br.com.diego.learningspring.model.Transaction;

public interface TransactionService {
  Transaction createTransaction(final CreateTransactionDto transactionData);

  Transaction retrieveTransaction(final long id);
}
