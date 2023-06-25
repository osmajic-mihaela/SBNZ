package sbnz.integracija.example.repository;

import demo.facts.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {
    private static TransactionRepository instance = new TransactionRepository();
    private List<Transaction> transactions;

    public static TransactionRepository getInstance() {
        return instance;
    }

    private TransactionRepository() {
        initTransaction();
    }

    private void initTransaction(){
        transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public Transaction getTransactionById(UUID id) {
        return transactions.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Transaction> getTransactionsByClient(String email) {
        return (ArrayList<Transaction>) transactions.stream()
                .filter(packge -> packge.getSenderEmail().equals(email))
                .collect(Collectors.toList());
    }

}
