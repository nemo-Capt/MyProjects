package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    private Long id;
    private String username;
    private String password;
    private double balance;
    private String rights;
    private String creditdate;
    private double creditvalue;
    private String depositdate;
    private double depositvalue;
    private double creditpercent;
    private String creditname;
    private double depositpercent;
    private String depositname;
    private String treatynumbercredit;
    private String treatynumberdeposit;
    private String balancenumber;

    public User() {
        creditdate = "No credit";
        creditvalue = 0;
        depositdate = "No deposit";
        depositvalue = 0;
        creditpercent = 0;
        creditname = "No credit";
        depositpercent = 0;
        depositname = "No deposit";
        treatynumbercredit = "No credit";
        treatynumberdeposit = "No deposit";
    }

    public User(Long id, String username, String password, double balance, String rights, String creditdate, double creditvalue, String depositdate, double depositvalue, double creditpercent, String creditname, double depositpercent, String depositname, String treatynumbercredit, String treatynumberdeposit, String balancenumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.rights = rights;
        this.creditdate = creditdate;
        this.creditvalue = creditvalue;
        this.depositdate = depositdate;
        this.depositvalue = depositvalue;
        this.creditpercent = creditpercent;
        this.creditname = creditname;
        this.depositpercent = depositpercent;
        this.depositname = depositname;
        this.treatynumbercredit = treatynumbercredit;
        this.treatynumberdeposit = treatynumberdeposit;
        this.balancenumber = balancenumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getCreditdate() {
        return creditdate;
    }

    public void setCreditdate(String creditdate) {
        this.creditdate = creditdate;
    }

    public double getCreditvalue() {
        return creditvalue;
    }

    public void setCreditvalue(double creditvalue) {
        this.creditvalue = creditvalue;
    }

    public String getDepositdate() {
        return depositdate;
    }

    public void setDepositdate(String depositdate) {
        this.depositdate = depositdate;
    }

    public double getDepositvalue() {
        return depositvalue;
    }

    public void setDepositvalue(double depositvalue) {
        this.depositvalue = depositvalue;
    }

    public double getCreditpercent() {
        return creditpercent;
    }

    public void setCreditpercent(double creditpercent) {
        this.creditpercent = creditpercent;
    }

    public String getCreditname() {
        return creditname;
    }

    public void setCreditname(String creditname) {
        this.creditname = creditname;
    }

    public double getDepositpercent() {
        return depositpercent;
    }

    public void setDepositpercent(double depositpercent) {
        this.depositpercent = depositpercent;
    }

    public String getDepositname() {
        return depositname;
    }

    public void setDepositname(String depositname) {
        this.depositname = depositname;
    }

    public String getTreatynumbercredit() {
        return treatynumbercredit;
    }

    public void setTreatynumbercredit(String treatynumbercredit) {
        this.treatynumbercredit = treatynumbercredit;
    }

    public String getTreatynumberdeposit() {
        return treatynumberdeposit;
    }

    public void setTreatynumberdeposit(String treatynumberdeposit) {
        this.treatynumberdeposit = treatynumberdeposit;
    }

    public String getBalancenumber() {
        return balancenumber;
    }

    public void setBalancenumber(String balancenumber) {
        this.balancenumber = balancenumber;
    }
}
