package demo.facts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreditRequest {

    private UUID id;
    private String email;
    private double amount;
    private int rateNumber;
    private double mounthlyRate;
    private EmployType employType;
    private int ages;
    private LocalDateTime startContract;
    private LocalDateTime endContract;

    private LocalDateTime minDate;
    private LocalDateTime maxDate;
    private double avgIncome;
    private double avgCosts;
    private boolean isMissedLoan;
    private CreditRequestType creditRequestType;

    private boolean isRecommend;
    public CreditRequest() {

    }

    public CreditRequest(String email, double amount, int rateNumber, double mounthlyRate, EmployType employType, int ages,
                         LocalDateTime startContract, LocalDateTime endContract, double avgIncome, double avgCosts, boolean isMissedLoan,
                         CreditRequestType creditRequestType, boolean isRecommend, LocalDateTime minDate, LocalDateTime maxDate) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.amount = amount;
        this.rateNumber = rateNumber;
        this.mounthlyRate = mounthlyRate;
        this.employType = employType;
        this.ages = ages;
        this.startContract = startContract;
        this.endContract = endContract;
        this.avgIncome = avgIncome;
        this.avgCosts = avgCosts;
        this.isMissedLoan = isMissedLoan;
        this.creditRequestType = creditRequestType;
        this.isRecommend = isRecommend;
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public boolean isRecommend() {
        return isRecommend;
    }

    public void setRecommend(boolean recommend) {
        isRecommend = recommend;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getRateNumber() {
        return rateNumber;
    }

    public void setRateNumber(int rateNumber) {
        this.rateNumber = rateNumber;
    }

    public double getMounthlyRate() {
        return mounthlyRate;
    }

    public void setMounthlyRate(double mounthlyRate) {
        this.mounthlyRate = mounthlyRate;
    }

    public EmployType getEmployType() {
        return employType;
    }

    public void setEmployType(EmployType employType) {
        this.employType = employType;
    }

    public int getAges() {
        return ages;
    }

    public void setAges(int ages) {
        this.ages = ages;
    }

    public LocalDateTime getStartContract() {
        return startContract;
    }

    public void setStartContract(LocalDateTime startContract) {
        this.startContract = startContract;
    }

    public LocalDateTime getEndContract() {
        return endContract;
    }

    public void setEndContract(LocalDateTime endContract) {
        this.endContract = endContract;
    }

    public double getAvgIncome() {
        return avgIncome;
    }

    public void setAvgIncome(double avgIncome) {
        this.avgIncome = avgIncome;
    }

    public double getAvgCosts() {
        return avgCosts;
    }

    public void setAvgCosts(double avgCosts) {
        this.avgCosts = avgCosts;
    }

    public boolean isMissedLoan() {
        return isMissedLoan;
    }

    public void setMissedLoan(boolean missedLoan) {
        isMissedLoan = missedLoan;
    }

    public CreditRequestType getCreditRequestType() {
        return creditRequestType;
    }

    public void setCreditRequestType(CreditRequestType creditRequestType) {
        this.creditRequestType = creditRequestType;
    }

    public LocalDateTime getMinDate() {
        return minDate;
    }

    public void setMinDate(LocalDateTime minDate) {
        this.minDate = minDate;
    }

    public LocalDateTime getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(LocalDateTime maxDate) {
        this.maxDate = maxDate;
    }
}
