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


        Transaction transaction1 = new Transaction(UUID.fromString("d38bf5f3-90ca-4c42-967c-995912032975"), LocalDateTime.now(), "lazar@gmail.com",
                "Lazar", "Lazar", null, 11112,
                11111, 33333, r,
                123, 5, InetAddress.getByName("93.87.123.177") , false, true, true, "books");

        Transaction transaction11 = new Transaction(UUID.fromString("cfcab94d-359a-4e81-a8a9-c81a3845df74"), LocalDateTime.now().minusMonths(5), "user",
                "Lazar", "Lazar", null, 11113,
                11113, 33333, r,
                123, 100, InetAddress.getByName("93.87.123.177") , false, true, true, "CREDIT");

        Transaction transaction22 = new Transaction(UUID.fromString("7d07a66f-f59d-4292-a498-b981084b228c"), LocalDateTime.now().minusMonths(4), "user",
                "Lazar", "Lazar", null, 11113,
                11113, 33333, r,
                123, 100, InetAddress.getByName("93.87.123.177") , false, true, true, "CREDIT");

        Transaction transaction33 = new Transaction(UUID.fromString("ed9852d2-f159-4159-89de-9a410f3a4158"), LocalDateTime.now().minusMonths(3), "user",
                "Lazar", "Lazar", null, 11113,
                11113, 33333, r,
                123, 100, InetAddress.getByName("93.87.123.177") , false, true, true, "CREDIT");

        Transaction transaction44 = new Transaction(UUID.fromString("0ac173c0-12cc-45ed-b446-289578d8dcae"), LocalDateTime.now().minusMonths(2), "user",
                "Lazar", "Lazar", null, 11113,
                11113, 33333, r,
                123, 100, InetAddress.getByName("93.87.123.177") , false, true, true, "CREDIT");

        Transaction transaction55 = new Transaction(UUID.fromString("7fc860d2-9b35-490f-925f-dcc439b05e43"), LocalDateTime.now().minusMonths(1), "user",
                "Lazar", "Lazar", null, 11113,
                11113, 33333, r,
                123, 100, InetAddress.getByName("93.87.123.177") , false, true, true, "CREDIT");

        Transaction transaction6 = new Transaction(UUID.fromString("6adc5424-5265-469c-9a49-d0d517bb96b8"), LocalDateTime.now(), "random",
                "Lazar", "Lazar", null, 33333,
                33333, 55555, r,
                123, 2000, InetAddress.getByName("93.87.123.177") , false, true, true, "CREDIT");

        transactions.add(transaction1);
        transactions.add(transaction11);
        transactions.add(transaction22);
        transactions.add(transaction33);
        transactions.add(transaction44);
        transactions.add(transaction55);
        transactions.add(transaction6);
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
        tr.setSuspicious(false);
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
