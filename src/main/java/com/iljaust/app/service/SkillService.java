package com.iljaust.app.service;

import com.iljaust.app.model.Skill;
import com.iljaust.app.repository.SkillRepository;
import com.iljaust.app.repository.jdbc.JDBCSkillRepImpl;


import java.sql.SQLException;
import java.util.*;


public class SkillService {
    private SkillRepository skillRepository = new JDBCSkillRepImpl();

    public SkillService(JDBCSkillRepImpl jdbcSkillRep){

        this.skillRepository = jdbcSkillRep;
    }

    public SkillService(){

    }


    public List<Skill> getAll(){

        try {
            return skillRepository.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Skill update(Skill skill) {

        return skillRepository.update(skill);
    }

    public void deleteById(Long id) {

        skillRepository.deleteById(id);
    }

    public Skill save(Skill skill) {
        skillRepository.save(skill);
        return skill;
    }

    public Skill getById(Long id) {

        return skillRepository.getById(id);
    }

}


