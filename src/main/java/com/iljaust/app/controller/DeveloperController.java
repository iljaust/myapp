package com.iljaust.app.controller;

import com.iljaust.app.model.Developer;
import com.iljaust.app.service.DeveloperService;
import liquibase.pro.packaged.D;

import java.util.List;

public class DeveloperController {

    DeveloperService developerService = new DeveloperService();

    public void deleteById(long id){
        developerService.deleteById(id);
    }

    public Developer save(Developer developer){
        developerService.save(developer);
        return developer;
    }

    public Developer getById(long id){
        return developerService.getById(id);
    }

    public List<Developer> getAll(){
        return developerService.getAll();
    }

    public Developer update(Developer developer){
        return developerService.update(developer);
    }
}
