package demo.facts;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.net.InetAddress;


public class Transaction {
    private UUID id;
    private LocalDateTime transactionDate;
    //pos
    private String senderEmail;
    private String senderFirstName;
    private  String senderLastName;
    private UUID senderAccountId;
    private int senderAccountNumber;
    private int senderCardNumber;
    //prim
    private int beneficiarAccountNumber;
    //card
    private Date cardExpirationDate;
    private int cvv;
    private double amountTrans;
    private InetAddress locationIP;
    private boolean isSuspicious;
    private boolean validateTransaction;


    public Transaction(){}

    public Transaction(LocalDateTime transactionDate, String senderEmail, String senderFirstName, String senderLastName,
                       UUID senderAccountId, int senderAccountNumber, int senderCardNumber, int beneficiarAccountNumber,
                       Date cardExpirationDate, int cvv, double amountTrans, InetAddress locationIP, boolean isSuspicious) {
        this.transactionDate = transactionDate;
        this.senderEmail = senderEmail;
        this.senderFirstName = senderFirstName;
        this.senderLastName = senderLastName;
        this.senderAccountId = senderAccountId;
        this.senderAccountNumber = senderAccountNumber;
        this.senderCardNumber = senderCardNumber;
        this.beneficiarAccountNumber = beneficiarAccountNumber;
        this.cardExpirationDate = cardExpirationDate;
        this.cvv = cvv;
        this.amountTrans = amountTrans;
        this.locationIP = locationIP;
        this.isSuspicious = isSuspicious;
        this.validateTransaction = false;
    }

    //za seed podatke
    public Transaction(UUID id, LocalDateTime transactionDate, String senderEmail, String senderFirstName,
                       String senderLastName, UUID senderAccountId, int senderAccountNumber, int senderCardNumber,
                       int beneficiarAccountNumber, Date cardExpirationDate, int cvv, double amountTrans,
                       InetAddress locationIP, boolean isSuspicious) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.senderEmail = senderEmail;
        this.senderFirstName = senderFirstName;
        this.senderLastName = senderLastName;
        this.senderAccountId = senderAccountId;
        this.senderAccountNumber = senderAccountNumber;
        this.senderCardNumber = senderCardNumber;
        this.beneficiarAccountNumber = beneficiarAccountNumber;
        this.cardExpirationDate = cardExpirationDate;
        this.cvv = cvv;
        this.amountTrans = amountTrans;
        this.locationIP = locationIP;
        this.isSuspicious = isSuspicious;
        this.validateTransaction = false;
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public String getSenderLastName() {
        return senderLastName;
    }

    public void setSenderLastName(String senderLastName) {
        this.senderLastName = senderLastName;
    }

    public UUID getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(UUID senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public int getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(int senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public int getSenderCardNumber() {
        return senderCardNumber;
    }

    public void setSenderCardNumber(int senderCardNumber) {
        this.senderCardNumber = senderCardNumber;
    }

    public int getBeneficiarAccountNumber() {
        return beneficiarAccountNumber;
    }

    public void setBeneficiarAccountNumber(int beneficiarAccountNumber) {
        this.beneficiarAccountNumber = beneficiarAccountNumber;
    }

    public Date getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(Date cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public double getAmountTrans() {
        return amountTrans;
    }

    public void setAmountTrans(double amountTrans) {
        this.amountTrans = amountTrans;
    }

    public InetAddress getLocationIP() {
        return locationIP;
    }

    public void setLocationIP(InetAddress locationIP) {
        this.locationIP = locationIP;
    }

    public boolean isSuspicious() {
        return isSuspicious;
    }

    public void setSuspicious(boolean suspicious) {
        isSuspicious = suspicious;
    }

    public boolean isValidateTransaction() {
        return validateTransaction;
    }

    public void setValidateTransaction(boolean validateTransaction) {
        this.validateTransaction = validateTransaction;
    }
}
