package demo.facts;

import java.util.Date;

public class CreditRequest {
    private String clientId;
    private float requestedCredit;
    private int numberOfInstallments;
    private String employmentType;
    private Date employmentStart;
    private Date employedUntil;

    public CreditRequest() {
    }

    public CreditRequest(String clientId, float requestedCredit, int numberOfInstallments, String employmentType, Date employmentStart, Date employedUntil) {
        this.clientId = clientId;
        this.requestedCredit = requestedCredit;
        this.numberOfInstallments = numberOfInstallments;
        this.employmentType = employmentType;
        this.employmentStart = employmentStart;
        this.employedUntil = employedUntil;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public float getRequestedCredit() {
        return requestedCredit;
    }

    public void setRequestedCredit(float requestedCredit) {
        this.requestedCredit = requestedCredit;
    }

    public int getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(int numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public Date getEmploymentStart() {
        return employmentStart;
    }

    public void setEmploymentStart(Date employmentStart) {
        this.employmentStart = employmentStart;
    }

    public Date getEmployedUntil() {
        return employedUntil;
    }

    public void setEmployedUntil(Date employedUntil) {
        this.employedUntil = employedUntil;
    }
}
