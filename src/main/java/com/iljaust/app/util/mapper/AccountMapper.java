package com.iljaust.app.util.mapper;

import com.iljaust.app.model.Account;
import com.iljaust.app.model.AccountStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper {
    public static Account mapToAccount (ResultSet resultSet) throws SQLException {
        Account account = new Account();

            account.setId(resultSet.getLong("developers_id"));
            account.setData(resultSet.getString("data"));
            account.setAccountStatus(AccountStatus.valueOf(resultSet.getString("status")));


            return account;
            
    }
}
