package sbnz.integracija.example.repository;

import demo.facts.Transaction;
import org.springframework.stereotype.Repository;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {
    private static TransactionRepository instance;

    static {
        try {
            instance = new TransactionRepository();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Transaction> transactions;

    public static TransactionRepository getInstance() {
        return instance;
    }

    public TransactionRepository() throws UnknownHostException {

        initTransaction();
    }

    private void initTransaction() throws UnknownHostException {

        transactions = new ArrayList<>();
        Date r = new Date();
        r.setYear(2024);


        Transaction transaction1 = new Transaction(UUID.randomUUID(), LocalDateTime.now(), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("93.87.123.177") , false, false, true);

        transactions.add(transaction1);
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

    public List<Transaction> getClientSuspiciousTransactions(String email) {
        return (ArrayList<Transaction>) transactions.stream()
                .filter(packge -> packge.getSenderEmail().equals(email))
                .filter(packge -> packge.isSuspicious()==true)
                .filter(packge -> packge.isApproved()==false)
                .collect(Collectors.toList());
    }

    public Transaction approveTransaction(String id)
    {
        Transaction first = transactions.get(0);
        System.out.println(first.getId());
        System.out.println(UUID.fromString(id));
        Transaction tr = transactions.stream()
                .filter(t -> t.getId().equals(UUID.fromString(id)))
                .findFirst()
                .orElse(null);
        tr.setApproved(true);
        return tr;
    }

    public Transaction cancelTransaction(String id)
    {
        Transaction tr = transactions.stream()
                .filter(t -> t.getId().equals(UUID.fromString(id)))
                .findFirst()
                .orElse(null);
        tr.setApproved(true);
        return tr;
    }
}
