package com.example.agrilife.model_classes;

public class LoanCollateralClass {
    private String c_name; // c indicates collateral
    private Double c_value;
    public LoanCollateralClass(String c_name, Double c_value) {
        this.c_name = c_name;
        this.c_value = c_value;
    }

    public LoanCollateralClass() {
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public Double getC_value() {
        return c_value;
    }

    public void setC_value(Double c_value) {
        this.c_value = c_value;
    }
}
