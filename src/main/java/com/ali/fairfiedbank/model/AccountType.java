package com.ali.fairfiedbank.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name="accounttypes")
public class AccountType {
    @Id
    @GeneratedValue
    private int accountTypeId;

    @NotBlank(message = "Account type should not be blank")
    @Column(unique = true)
    private String accountTypeName;

    @OneToMany(mappedBy="accountType")
    private List<Account> accounts = new ArrayList<Account>();

    public AccountType() {
    }

    public AccountType(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
