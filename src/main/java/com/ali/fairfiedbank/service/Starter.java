package com.ali.fairfiedbank.service;

import com.ali.fairfiedbank.model.AccountType;
import org.springframework.beans.factory.annotation.Autowired;

public class Starter {
    public static void main(String[] args) {
        AccountType at1 = new AccountType("Checking");
        AccountType at2 = new AccountType("Loan");
        AccountType at3 = new AccountType("Savings");

//        @Autowired
//        AccountTypeService ats = new AccountTypeService();
//        ats.saveAccountType(at1);
    }
}
