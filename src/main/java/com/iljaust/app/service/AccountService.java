package com.iljaust.app.service;

import com.iljaust.app.model.Account;
import com.iljaust.app.repository.jdbc.JDBCAccountRepIpml;


import java.util.List;

public class AccountService {
    private JDBCAccountRepIpml jdbcAccountRepIpml = new JDBCAccountRepIpml();

    public AccountService(JDBCAccountRepIpml jdbcAccountRepIpml){

        this.jdbcAccountRepIpml = jdbcAccountRepIpml;
    }

    public AccountService(){
    }

    public List<Account> getAll(){

        return jdbcAccountRepIpml.getAll();

    }

    public Account update(Account account) {

        return jdbcAccountRepIpml.update(account);

    }

    public void deleteById(Long id) {

        jdbcAccountRepIpml.deleteById(id);

    }

    public Account save(Account account) {

        jdbcAccountRepIpml.save(account);
        return account;

    }

    public Account getById(Long id) {

        return jdbcAccountRepIpml.getById(id);

    }
}
