package com.iljaust.app.repository.jdbc;

import com.iljaust.app.model.Skill;
import com.iljaust.app.repository.SkillRepository;
import com.iljaust.app.util.DatabaseConnection;
import com.iljaust.app.util.mapper.SkillMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class JDBCSkillRepImpl implements SkillRepository {

    static private Connection connection = DatabaseConnection.getConnection();
    private PreparedStatement preparedStatement;

    private static final String SELECT_ALL = "select * from skills";
    private static final String SELECT_BY_ID = "SELECT * FROM skills WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM skills WHERE id=?";
    private static final String INSERT_AUTO_ID = "INSERT INTO skills (name) VALUES (?)";
    private static final String UPDATE_BY_ID = "UPDATE skills SET name=? WHERE id=?";

    public Skill getById(Long id) {

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return SkillMapper.mapToSkill(resultSet);
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

    public Skill save(Skill skill) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUTO_ID);
            preparedStatement.setString(1,skill.getName());
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
        return skill;
    }

    public Skill update(Skill skill) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1,skill.getName());
            preparedStatement.setLong(2,skill.getId());
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
        return skill;
    }

    public List<Skill> getAll() {

        List<Skill>  skills = new ArrayList<>();

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                skills.add(SkillMapper.mapToSkill(resultSet));
            }
            connection.commit();

            return skills;

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
}
