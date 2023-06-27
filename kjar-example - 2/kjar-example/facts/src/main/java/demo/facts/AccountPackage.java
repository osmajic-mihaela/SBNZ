package demo.facts;

import java.util.Date;
import java.util.UUID;

public class AccountPackage {
    private UUID id;
    private String clientId; //email
    private String clientFirstName;
    private String clientLastName;
    private int cardNumber;
    private int accountNumber;
    private int cvv;
    private double balance;
    private Date expirationDate;

    public AccountPackage(){}

    public AccountPackage(String clientId, String clientFirstName, String clientLastName, int cardNumber, int accountNumber, int cvv, double balance, Date expirationDate){
        this.id = UUID.randomUUID();
        this.clientId= clientId;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.cvv = cvv;
        this.balance = balance;
        this.expirationDate = expirationDate;

    }

    public AccountPackage(AccountPackage acc){
        this.id = acc.getId();
        this.clientId= acc.getClientId();
        this.clientFirstName = acc.getClientFirstName();
        this.clientLastName = acc.getClientLastName();
        this.cardNumber = acc.getCardNumber();
        this.accountNumber = acc.getAccountNumber();
        this.cvv = acc.getCvv();
        this.balance = acc.getBalance();
        this.expirationDate = acc.getExpirationDate();
    }

    // za seed podatke
    public AccountPackage(UUID id,String clientId, String clientFirstName, String clientLastName, int cardNumber,
                          int accountNumber, int cvv, double balance, Date expirationDate){
        this.id = id;
        this.clientId= clientId;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.cvv = cvv;
        this.balance = balance;
        this.expirationDate = expirationDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
