package com.example.agrilife.model_classes;

import java.util.UUID;

public class insuranceModel {
    private String policyName , policyDescription;
    private int policyCover;
    private String targetEntity ; // equipment insurance or crop insurance
    private int premiumLowerBound;
    private double claimSettlementRatio ,amountSettlementRatio;
    private String planId;
    private int waitingPeriodInDays;
    private int noClaimBonus;

    public insuranceModel() {
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyDescription() {
        return policyDescription;
    }

    public void setPolicyDescription(String policyDescription) {
        this.policyDescription = policyDescription;
    }

    public int getPolicyCover() {
        return policyCover;
    }

    public void setPolicyCover(int policyCover) {
        this.policyCover = policyCover;
    }

    public String getTargetEntity() {
        return targetEntity;
    }

    public void setTargetEntity(String targetEntity) {
        this.targetEntity = targetEntity;
    }

    public int getPremiumLowerBound() {
        return premiumLowerBound;
    }

    public void setPremiumLowerBound(int premiumLowerBound) {
        this.premiumLowerBound = premiumLowerBound;
    }

    public double getClaimSettlementRatio() {
        return claimSettlementRatio;
    }

    public void setClaimSettlementRatio(double claimSettlementRatio) {
        this.claimSettlementRatio = claimSettlementRatio;
    }

    public double getAmountSettlementRatio() {
        return amountSettlementRatio;
    }

    public void setAmountSettlementRatio(double amountSettlementRatio) {
        this.amountSettlementRatio = amountSettlementRatio;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public int getWaitingPeriodInDays() {
        return waitingPeriodInDays;
    }

    public void setWaitingPeriodInDays(int waitingPeriodInDays) {
        this.waitingPeriodInDays = waitingPeriodInDays;
    }

    public int getNoClaimBonus() {
        return noClaimBonus;
    }

    public void setNoClaimBonus(int noClaimBonus) {
        this.noClaimBonus = noClaimBonus;
    }

    public insuranceModel(String policyName, String policyDescription, int policyCover, String targetEntity, int premiumLowerBound, double claimSettlementRatio, double amountSettlementRatio, String planId, int waitingPeriodInDays, int noClaimBonus) {
        this.policyName = policyName;
        this.policyDescription = policyDescription;
        this.policyCover = policyCover;
        this.targetEntity = targetEntity;
        this.premiumLowerBound = premiumLowerBound;
        this.claimSettlementRatio = claimSettlementRatio;
        this.amountSettlementRatio = amountSettlementRatio;
        this.planId = planId;
        this.waitingPeriodInDays = waitingPeriodInDays;
        this.noClaimBonus = noClaimBonus;
    }
}

