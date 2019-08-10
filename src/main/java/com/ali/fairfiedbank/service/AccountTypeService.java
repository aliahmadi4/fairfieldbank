package com.ali.fairfiedbank.service;


import com.ali.fairfiedbank.model.AccountType;
import com.ali.fairfiedbank.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeService {

    @Autowired
    AccountTypeRepository atRepo;

    public void saveAccountType(AccountType at){
        atRepo.save(at);
    }

    public List<AccountType> getAcountTypes(){
        return atRepo.findAll();
    }

    public AccountType getAccountTypeById(int id){ return atRepo.findById(id).orElse(null); }

}
