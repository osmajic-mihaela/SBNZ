package demo.facts;

public class Credit {

    private boolean active;
    private float amountOfMoney;
    private String clientId;
    private boolean regularRepayments;

    public Credit(){}

    public Credit(boolean active, float amountOfMoney, String clientId, boolean regularRepayments) {
        this.active = active;
        this.amountOfMoney = amountOfMoney;
        this.clientId = clientId;
        this.regularRepayments = regularRepayments;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(float amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public boolean isRegularRepayments() {
        return regularRepayments;
    }

    public void setRegularRepayments(boolean regularRepayments) {
        this.regularRepayments = regularRepayments;
    }
}
