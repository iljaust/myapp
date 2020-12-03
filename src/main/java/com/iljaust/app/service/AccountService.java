package com.iljaust.app.service;

import com.iljaust.app.model.Account;
import com.iljaust.app.repository.AccountRepository;
import com.iljaust.app.repository.jdbc.JDBCAccountRepIpml;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private AccountRepository accountRepository = new JDBCAccountRepIpml();

    public AccountService(JDBCAccountRepIpml accountRepository){

        this.accountRepository = accountRepository;
    }

    public AccountService(){
    }

    public List<Account> getAll(){

        try {
            return accountRepository.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new ArrayList<>();
    }

    public Account update(Account account) {

        return accountRepository.update(account);

    }

    public void deleteById(Long id) {

        accountRepository.deleteById(id);

    }

    public Account save(Account account) {

        accountRepository.save(account);
        return account;

    }

    public Account getById(Long id) {

        return accountRepository.getById(id);

    }
}
