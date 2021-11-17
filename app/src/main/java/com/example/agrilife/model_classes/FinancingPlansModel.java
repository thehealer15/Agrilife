package com.example.agrilife.model_classes;

import java.io.Serializable;

public class FinancingPlansModel implements Serializable {
    private int amount;
    private String planName;
    private Double farmerCreditScoreMultiplier ; // this will be indicator which will enable us to consider credit score of farmer
    private LoanCollateralClass collateral;

    // repayment related
    private Double interestRate;
    private int returnPeriod_inMonths; // in months

    // aquision period
    // if fails within aquision period either finaned product or collateral will be aquired
    private Double aquision_periood ; // multiplier of returnp period in months


    public FinancingPlansModel(int amount, String planName, Double farmerCreditScoreMultiplier, LoanCollateralClass collateral, Double interestRate, int returnPeriod_inMonths , Double aquision_periood) {
        this.amount = amount;
        this.planName = planName;
        this.farmerCreditScoreMultiplier = farmerCreditScoreMultiplier;
        this.collateral = collateral;
        this.interestRate = interestRate;
        this.returnPeriod_inMonths = returnPeriod_inMonths;
        this.aquision_periood = aquision_periood;
    }

    public FinancingPlansModel() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Double getFarmerCreditScoreMultiplier() {
        return farmerCreditScoreMultiplier;
    }

    public void setFarmerCreditScoreMultiplier(Double farmerCreditScoreMultiplier) {
        this.farmerCreditScoreMultiplier = farmerCreditScoreMultiplier;
    }

    public LoanCollateralClass getCollateral() {
        return collateral;
    }

    public void setCollateral(LoanCollateralClass collateral) {
        this.collateral = collateral;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public int getReturnPeriod_inMonths() {
        return returnPeriod_inMonths;
    }

    public void setReturnPeriod_inMonths(int returnPeriod_inMonths) {
        this.returnPeriod_inMonths = returnPeriod_inMonths;
    }

    public Double getAquision_periood() {
        return aquision_periood;
    }

    public void setAquision_periood(Double aquision_periood) {
        this.aquision_periood = aquision_periood;
    }

    // return collateral information
    public String get_collateral_name(){
        return this.collateral.getC_name();
    }
    public Double get_collateral_value(){
        return this.collateral.getC_value();
    }

}

