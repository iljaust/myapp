package com.iljaust.app.service;

import com.iljaust.app.model.Developer;
import com.iljaust.app.repository.DeveloperRepository;
import com.iljaust.app.repository.jdbc.JDBCDeveloperRepImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperService {
    private DeveloperRepository developerRepository = new JDBCDeveloperRepImpl();

    public DeveloperService(JDBCDeveloperRepImpl DeveloperRepository){

        this.developerRepository = DeveloperRepository;
    }

    public DeveloperService(){

    }

    public List<Developer> getAll(){

        try {
            return developerRepository.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new ArrayList<>();
    }

    public Developer update(Developer developer) {

        return developerRepository.update(developer);
    }

    public void deleteById(Long id) {

        developerRepository.deleteById(id);
    }

    public Developer save(Developer developer) {

        developerRepository.save(developer);
        return developer;
    }

    public Developer getById(Long id) {

        return developerRepository.getById(id);
    }
}
