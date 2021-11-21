package com.example.agrilife.model_classes;

public class User {
    String name ;
    String email ;
    String password ;
    String phone ;
    String address ;
    String uid ;
    String aadharno;
    String Bankaccno;
    String bankifsc;
    String sector;
    String description;


    public User(){

    }



    public User(String name, String email, String password, String phone, String address, String uid) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.uid = uid;
    }

    public User(String name, String email, String password, String phone, String address, String uid, String aadharno, String bankaccno, String bankifsc, String sector, String description) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.uid = uid;
        this.aadharno = aadharno;
        Bankaccno = bankaccno;
        this.bankifsc = bankifsc;
        this.sector = sector;
        this.description = description;
    }

    public String getAadharno() {
        return aadharno;
    }

    public void setAadharno(String aadharno) {
        this.aadharno = aadharno;
    }

    public String getBankaccno() {
        return Bankaccno;
    }

    public void setBankaccno(String bankaccno) {
        Bankaccno = bankaccno;
    }

    public String getBankifsc() {
        return bankifsc;
    }

    public void setBankifsc(String bankifsc) {
        this.bankifsc = bankifsc;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}


