package com.iljaust.app.controller;

import com.iljaust.app.model.Account;
import com.iljaust.app.service.AccountService;

import java.util.List;

public class AccountController {
    AccountService accountService = new AccountService();

    public List<Account> getAll(){
        return accountService.getAll();
    }

    public Account getById(Long id) {
        return accountService.getById(id);
    }

    public Account save(Account account) {
        return accountService.save(account);
    }

    public Account update(Account account) {
        return accountService.update(account);
    }

    public void deleteById(Long id) {
        accountService.deleteById(id);
    }
}
