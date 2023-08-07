package br.com.diego.learningspring.service.Impl;

import br.com.diego.learningspring.dto.CreateTransactionDto;
import br.com.diego.learningspring.exception.AppException;
import br.com.diego.learningspring.model.Transaction;
import br.com.diego.learningspring.model.User;
import br.com.diego.learningspring.repository.TransactionRepository;
import br.com.diego.learningspring.repository.UserRepository;
import br.com.diego.learningspring.service.TransactionService;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;
  private final UserRepository userRepository;

  public TransactionServiceImpl(
    TransactionRepository transactionRepository,
    UserRepository userRepository
  ) {
    this.transactionRepository = transactionRepository;
    this.userRepository = userRepository;
  }

  public Transaction createTransaction(
    final CreateTransactionDto transactionData
  ) {
    final User foundPayer = userRepository
      .findById(transactionData.getPayer_id())
      .orElseThrow(() -> new AppException("payerNotFound", HttpStatus.NOT_FOUND)
      );

    if (Objects.equals(foundPayer.getType(), "SELLER")) {
      throw new AppException("invalidUserType", HttpStatus.FORBIDDEN);
    }

    final User foundPayee = userRepository
      .findById(transactionData.getPayee_id())
      .orElseThrow(() -> new AppException("payeeNotFound", HttpStatus.NOT_FOUND)
      );

    final float transactionValue = transactionData.getValue();

    final float payerCurrentBalance = foundPayer.getBalance();

    if (payerCurrentBalance < transactionValue) {
      throw new AppException("balanceNotSufficient", HttpStatus.FORBIDDEN);
    }

    final float payeeCurrentBalance = foundPayee.getBalance();

    foundPayer.setBalance(payerCurrentBalance - transactionValue);
    foundPayee.setBalance(payeeCurrentBalance + transactionValue);

    final Transaction newTransaction = new Transaction(
      foundPayer,
      foundPayee,
      transactionValue
    );

    return transactionRepository.save(newTransaction);
  }

  public Transaction retrieveTransaction(final long id) {
    return transactionRepository
      .findById(id)
      .orElseThrow(() ->
        new AppException("transactionNotFound", HttpStatus.NOT_FOUND)
      );
  }
}
