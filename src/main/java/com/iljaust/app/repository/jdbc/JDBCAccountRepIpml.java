package com.iljaust.app.repository.jdbc;

import com.iljaust.app.model.Account;
import com.iljaust.app.model.AccountStatus;
import com.iljaust.app.model.Skill;
import com.iljaust.app.repository.AccountRepository;
import com.iljaust.app.util.DatabaseConnection;
import com.iljaust.app.util.mapper.AccountMapper;
import com.iljaust.app.util.mapper.SkillMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCAccountRepIpml implements AccountRepository {

    static private Connection connection = DatabaseConnection.getConnection();
    private PreparedStatement preparedStatement;

    private static final String SELECT_ALL = "SELECT accounts.idaccounts_table, accounts.data, account_status.status\n" +
            "FROM accounts\n" +
            "INNER JOIN account_status ON accounts.idaccount_status = account_status.idaccount_status";
    private static final String SELECT_BY_ID = "SELECT account_status.status, accounts.idaccounts_table, accounts.data\n" +
            "FROM accounts\n" +
            "INNER JOIN account_status ON accounts.idaccount_status = account_status.idaccount_status\n" +
            "WHERE idaccounts_table = ?";
    private static final String DELETE_BY_ID = "DELETE FROM accounts WHERE idaccounts_table = ?";
    private static final String INSERT_AUTO_ID = "INSERT INTO accounts (data, idaccount_status) VALUES (?,?)";
    private static final String UPDATE_BY_ID = "UPDATE accounts SET data = ?, idaccount_status = ? WHERE idaccounts_table=?";

    public Account getById(Long id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return AccountMapper.mapToAccount(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if(preparedStatement != null && connection!= null)
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }
        return null;
    }

    public Account save(Account account) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUTO_ID);
            preparedStatement.setString(1,account.getData());
            preparedStatement.setInt(2, accountStatusToNum(account.getAccountStatus()));
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        finally {
            if(preparedStatement != null && connection!= null)
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }
        return account;
    }

    public Account update(Account account) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1,account.getData());
            preparedStatement.setLong(2,accountStatusToNum(account.getAccountStatus()));
            preparedStatement.setLong(3,account.getId());
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        finally {
            if(preparedStatement != null && connection!= null)
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }
        return account;
    }

    public List<Account> getAll() {
        List<Account>  accounts = new ArrayList<>();

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                accounts.add(AccountMapper.mapToAccount(resultSet));
            }
            connection.commit();

            return accounts;

        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            if(preparedStatement != null && connection!= null)
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }
        return null;
    }

    public void deleteById(Long id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        finally {
            if(preparedStatement != null && connection!= null)
                try {
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }

    }

    private static Integer accountStatusToNum(AccountStatus accountStatus){
        if(accountStatus == AccountStatus.ACTIVE)
            return 1;
        else if(accountStatus == AccountStatus.BANNED)
            return 2;
        else
            return 3;
    }
}