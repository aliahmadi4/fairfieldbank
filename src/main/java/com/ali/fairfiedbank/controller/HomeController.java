package com.ali.fairfiedbank.controller;

import com.ali.fairfiedbank.model.Account;
import com.ali.fairfiedbank.model.AccountType;
import com.ali.fairfiedbank.model.Customer;
import com.ali.fairfiedbank.service.AccountService;
import com.ali.fairfiedbank.service.AccountTypeService;
import com.ali.fairfiedbank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    AccountTypeService accountTypeService;
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public String showHomepage() {
        return "index";
    }

    @GetMapping("/accountType/new")
    public String showNewAccountTypePage(Model model) {
        model.addAttribute("accountType", new AccountType());
        return "newAccountType";
    }

    @PostMapping("/accountType/new")
    public String saveAccountType(@Valid @ModelAttribute AccountType accountType, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "newAccountType";
        }

        accountTypeService.saveAccountType(accountType);
        return "index";
    }

    @GetMapping("/customer/new")
    public String showNewCustomerPage(Model model) {
        List<AccountType> accountsTypes = accountTypeService.getAcountTypes();
        model.addAttribute("accountTypes", accountsTypes);
        model.addAttribute("customer", new Customer());
        return "newCustomer";
    }

    @PostMapping("/customer/new")
    public String saveCustomer(@ModelAttribute("customer") Customer customer,
                               @RequestParam long accountNumber, @RequestParam double balance,
                               @RequestParam int accountType) {
        System.out.println(accountNumber);
        System.out.println(balance);
        System.out.println(accountType);

//        customer.addAccount(accountNumber, balance);
        customerService.saveCustomer(customer);

        AccountType act = accountTypeService.getAccountTypeById(accountType);


        Account account = new Account(accountNumber, balance);
        account.setAccountType(act);
        account.setCustomer(customer);
        accountService.saveAccount(account);
        return "index";
    }

    @GetMapping("/customers")
    public ModelAndView showCustomersPage(@RequestParam(defaultValue = "0") int pageNo) {
        Page<Customer> customers = customerService.getCustomers(pageNo);
        ModelAndView mv = new ModelAndView();
        mv.addObject("customers", customers);
        mv.addObject("currentPage", pageNo);
        mv.setViewName("customers");
        return mv;
    }

    @GetMapping("/customer/account/new")
    public String showOpenAccountPage(@PathParam("customerId") String customerId, Model model) {
        List<AccountType> accountsTypes = accountTypeService.getAcountTypes();
        model.addAttribute("account", new Account());
        model.addAttribute("customerId", customerId);
        model.addAttribute("accountTypes", accountsTypes);
        return "newAccount";
    }

    @PostMapping("/customer/account/new")
    public String addAccountForCustomer(@ModelAttribute("account") Account account,
                                        @RequestParam("accountType") int accountType,
                                        @RequestParam("customerId") String customerId) {
        AccountType act = accountTypeService.getAccountTypeById(accountType);
        long customerId2 = Long.parseLong(customerId);
        Customer customer = customerService.getCustomerById(customerId2);

//        System.out.println(customer);
        account.setCustomer(customer);
        account.setAccountType(act);
        accountService.saveAccount(account);
        return "index";
    }

    @GetMapping("/accounts")
    public String showAccountsPage(@RequestParam(defaultValue = "0") int pageNo, Model model) {

        Page<Account> accountsPaged = accountService.getAllAccountsPaged(pageNo);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("accounts", accountsPaged);

        List<Account> accounts = accountService.getAllAccounts();
        double sumSavingsAndCheckings = 0;
        double sumLoan = 0;
        for (Account a : accounts) {
            if (a.getAccountType().getAccountTypeName().equals("Checking") || a.getAccountType().getAccountTypeName().equals("Savings")) {
                sumSavingsAndCheckings += a.getBalance();
            } else {
                sumLoan += a.getBalance();
            }
        }
//        System.out.println(sumLoan);
//        System.out.println(sumSavingsAndCheckings);
        model.addAttribute("liquidity", sumSavingsAndCheckings - sumLoan);
        return "accounts";
    }
}
