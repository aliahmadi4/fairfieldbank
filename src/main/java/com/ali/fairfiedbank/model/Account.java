package com.ali.fairfiedbank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="accounts")
public class Account {
    @Id
    @GeneratedValue
    private long accountId;
    private long accountNumber;
    private double balance;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private AccountType accountType;

    public Account() {
    }

    public Account(long accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Account(long accountNumber, double balance, Customer customer, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customer = customer;
        this.accountType = accountType;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
