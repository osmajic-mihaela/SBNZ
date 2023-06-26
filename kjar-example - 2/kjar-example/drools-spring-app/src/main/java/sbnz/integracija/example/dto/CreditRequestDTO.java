package sbnz.integracija.example.dto;

public class CreditRequestDTO
{
    private String clientId;
    private float requestedCredit;
    private int numberOfInstallments;

    public CreditRequestDTO(){}

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
}
