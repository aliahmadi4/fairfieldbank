package com.ali.fairfiedbank.service;

import com.ali.fairfiedbank.model.Account;
import com.ali.fairfiedbank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public void saveAccount(Account account){
        accountRepository.save(account);
    }

    public Page<Account> getAllAccountsPaged(int pageNo){
        return accountRepository.findAll(PageRequest.of(pageNo, 5, Sort.by("accountNumber")));
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

}
