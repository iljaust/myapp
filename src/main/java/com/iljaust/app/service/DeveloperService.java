package com.iljaust.app.service;

import com.iljaust.app.model.Account;
import com.iljaust.app.model.Developer;
import com.iljaust.app.repository.jdbc.JDBCDeveloperRepImpl;

import java.util.List;

public class DeveloperService {
    private JDBCDeveloperRepImpl jdbcDeveloperRep = new JDBCDeveloperRepImpl();

    public DeveloperService(JDBCDeveloperRepImpl jdbcDeveloperRep){

        this.jdbcDeveloperRep = jdbcDeveloperRep;
    }

    public DeveloperService(){

    }

    public List<Developer> getAll(){

        return jdbcDeveloperRep.getAll();
    }

    public Developer update(Developer developer) {

        return jdbcDeveloperRep.update(developer);
    }

    public void deleteById(Long id) {

        jdbcDeveloperRep.deleteById(id);
    }

    public Developer save(Developer developer) {

        jdbcDeveloperRep.save(developer);
        return developer;
    }

    public Developer getById(Long id) {

        return jdbcDeveloperRep.getById(id);
    }
}
