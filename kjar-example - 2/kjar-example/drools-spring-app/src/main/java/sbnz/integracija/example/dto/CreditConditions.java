package sbnz.integracija.example.dto;

public class CreditConditions {

    private boolean hasStableIncome;
    private boolean hasSufficientIncome;
    private boolean activeCredits;
    private boolean notSeniorWithMaxLoanRepaymentTerm;
    private boolean regularRepaymentsInThePast;

    public CreditConditions(){};

    public float getConditionFulfillmentPercentage() {
        float percentage = 0;
        if(hasStableIncome) percentage+=1.0;
        if(hasSufficientIncome) percentage+=1.0;
        if(activeCredits) percentage+=1.0;
        if(notSeniorWithMaxLoanRepaymentTerm) percentage+=1.0;
        if(regularRepaymentsInThePast) percentage+=1.0;

        return percentage/ 5;
    }

    public boolean isHasStableIncome() {
        return hasStableIncome;
    }

    public void setHasStableIncome(boolean hasStableIncome) {
        this.hasStableIncome = hasStableIncome;
    }

    public boolean isHasSufficientIncome() {
        return hasSufficientIncome;
    }

    public void setHasSufficientIncome(boolean hasSufficientIncome) {
        this.hasSufficientIncome = hasSufficientIncome;
    }

    public boolean isActiveCredits() {
        return activeCredits;
    }

    public void setActiveCredits(boolean activeCredits) {
        this.activeCredits = activeCredits;
    }

    public boolean isNotSeniorWithMaxLoanRepaymentTerm() {
        return notSeniorWithMaxLoanRepaymentTerm;
    }

    public void setNotSeniorWithMaxLoanRepaymentTerm(boolean notSeniorWithMaxLoanRepaymentTerm) {
        this.notSeniorWithMaxLoanRepaymentTerm = notSeniorWithMaxLoanRepaymentTerm;
    }

    public boolean isRegularRepaymentsInThePast() {
        return regularRepaymentsInThePast;
    }

    public void setRegularRepaymentsInThePast(boolean regularRepaymentsInThePast) {
        this.regularRepaymentsInThePast = regularRepaymentsInThePast;
    }
}
