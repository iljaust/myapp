package com.iljaust.app.repository.jdbc;


import com.iljaust.app.model.Developer;
import com.iljaust.app.repository.DeveloperRepository;
import com.iljaust.app.util.DatabaseConnection;
import com.iljaust.app.util.mapper.AccountMapper;
import com.iljaust.app.util.mapper.SkillListMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCDeveloperRepImpl implements DeveloperRepository {

    static private Connection connection = DatabaseConnection.getConnection();
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatement2;
    private PreparedStatement preparedStatement3;

    private static final String SELECT_ALL = "select developers.developers_id, developers.name as dev_name, developers.account_id,\n" +
            "accounts.idaccounts_table as acc_id, accounts.data, accounts.idaccount_status,\n" +
            "account_status.idaccount_status as status_id, account_status.status\n" +
            "FROM developers, skills, developer_skills, account_status, accounts\n" +
            "group by developers.name" ;

    private static final String SELECT_BY_ID = "SELECT name, developers_id  FROM developers WHERE developers_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM developers WHERE developers_id=?";
    private static final String INSERT_AUTO_ID = "INSERT INTO developers (name, developers_id, account_id) VALUES (?, ?,?)";
    private static final String UPDATE_BY_ID = "UPDATE developers SET name= ?  WHERE developers_id = ?";
    private static final String GET_SKILLS = "SELECT id , name\n" +
            "FROM skills\n" +
            "inner join developer_skills ON skills.id = developer_skills.skill_id\n" +
            "where developer_skills.developer_id = ?";

    private static final String GET_ACCOUNT = "SELECT accounts.idaccounts_table, accounts.data, account_status.status\n" +
            "From accounts\n" +
            "INNER JOIN account_status ON account_status.idaccount_status = accounts.idaccount_status\n" +
            "where accounts.idaccounts_table = (SELECT account_id\n" +
            "FROM developers\n" +
            "WHERE developers_id = ? )";



    public Developer getById(Long id) {
        Developer developer;

        try {
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1,id);
            preparedStatement2 = connection.prepareStatement(GET_SKILLS);
            preparedStatement2.setLong(1,id);
            preparedStatement3 = connection.prepareStatement(GET_ACCOUNT);
            preparedStatement3.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            ResultSet resultSet3 = preparedStatement3.executeQuery();

            if (resultSet.next()){
                developer = new Developer.DeveloperBuilder((resultSet.getString("name")), resultSet.getLong("developers_id"))
                        .skillSet(SkillListMapper.mapToSkill(resultSet2))
                        .accountStatus(AccountMapper.mapToAccount(resultSet3))
                        .build();

                return developer;
            }

            connection.commit();

            return null;

        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        finally {
            if(preparedStatement != null && connection!= null && preparedStatement2 != null && preparedStatement3 != null)
                try {
                    preparedStatement.close();
                    preparedStatement2.close();
                    preparedStatement3.close();
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }
        return null;
    }

    public Developer save(Developer developer) {

        System.out.println(developer.getAccountStatus().getId());
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUTO_ID);
            preparedStatement.setString(1,developer.getName());
            preparedStatement.setLong(2,developer.getId());
            preparedStatement.setLong(3, developer.getAccountStatus().getId());
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
        return developer;
    }

    public Developer update(Developer developer) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1,developer.getName());
            preparedStatement.setLong(2,developer.getId());
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

        return developer;
    }

    public List<Developer> getAll() {
        List<Developer>  developers = new ArrayList<>();
        Developer developer;

        try {
            connection.setAutoCommit(false);
             preparedStatement = connection.prepareStatement(SELECT_ALL);

             ResultSet resultSet = preparedStatement.executeQuery();

             while (resultSet.next()) {
                 preparedStatement2 = connection.prepareStatement(GET_SKILLS);
                 preparedStatement2.setLong(1,resultSet.getLong("account_id"));
                 ResultSet resultSet1 = preparedStatement2.executeQuery();
                developer =  new Developer.DeveloperBuilder(resultSet.getString("dev_name"),resultSet.getLong("developers_id"))
                        .skillSet(SkillListMapper.mapToSkill(resultSet1))
                        .accountStatus(AccountMapper.mapToAccount(resultSet))
                        .build();

                developers.add(developer);
             }

            connection.commit();

            return developers;

        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            if(preparedStatement != null && preparedStatement2 != null && preparedStatement3 != null && connection!= null)
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

}
