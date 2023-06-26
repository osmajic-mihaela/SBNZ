package sbnz.integracija.example.dto;

public class ApprovalRecommendation {
    private boolean shouldApprove;

    public ApprovalRecommendation(boolean shouldApprove) {
        this.shouldApprove = shouldApprove;
    }

    public ApprovalRecommendation(){}

    public boolean isShouldApprove() {
        return shouldApprove;
    }

    public void setShouldApprove(boolean shouldApprove) {
        this.shouldApprove = shouldApprove;
    }
}
