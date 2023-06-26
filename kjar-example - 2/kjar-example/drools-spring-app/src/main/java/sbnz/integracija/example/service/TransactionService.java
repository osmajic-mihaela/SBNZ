package sbnz.integracija.example.service;

import demo.facts.Transaction;
import org.springframework.stereotype.Service;
import sbnz.integracija.example.repository.AccountPackageRepository;
import sbnz.integracija.example.repository.TransactionRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository repository = TransactionRepository.getInstance();
    private final AccountPackageRepository accountPackageRepository = AccountPackageRepository.getInstance();

    public Transaction addTransaction(Transaction transaction) {
        //validacija, provera sredstva, provera racuna na koji se uplacuje

        //pravila za detekciju prevare

        transaction.setValidateTransaction(true);
        repository.addTransaction(transaction);
        return transaction;
    }

    public Transaction getTransactionById(UUID id) {
        return repository.getTransactionById(id);
    }

    public List<Transaction> getTransactionsByClientId(String email) {
        return repository.getTransactionsByClient(email);
    }

    public List<Transaction> getAllTransactions() {
        return repository.getTransactions();
    }
}
