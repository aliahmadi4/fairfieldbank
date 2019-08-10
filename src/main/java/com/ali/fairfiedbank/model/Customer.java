package com.ali.fairfiedbank.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.security.auth.login.AccountException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity(name="customers")
public class Customer {
    @Id
    @GeneratedValue
    private long customerId;
    private long customerNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddress;
    private String contactPhoneNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "customer")
    private List<Account> accounts = new ArrayList<Account>();

    public Customer() {
    }

    public Customer(long customerNumber, String firstName, String middleName, String lastName, String emailAddress, String contactPhoneNumber, LocalDate dateOfBirth) {
        this.customerNumber = customerNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.contactPhoneNumber = contactPhoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public Customer(long customerNumber, String firstName, String middleName, String lastName, String emailAddress, String contactPhoneNumber, LocalDate dateOfBirth, Account account) {
        this.customerNumber = customerNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.contactPhoneNumber = contactPhoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.accounts.add(account);
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    public void addAccount(long accountNumber, double balance){
        Account account = new Account(accountNumber, balance);
        this.accounts.add(account);
    }
}
