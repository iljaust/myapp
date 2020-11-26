package com.iljaust.app.service;

import com.iljaust.app.model.Skill;
import com.iljaust.app.repository.jdbc.JDBCSkillRepImpl;

import java.util.List;

public class SkillService {
    private JDBCSkillRepImpl jdbcSkillRep = new JDBCSkillRepImpl();

    public SkillService(JDBCSkillRepImpl jdbcSkillRep){

        this.jdbcSkillRep = jdbcSkillRep;
    }

    public SkillService(){

    }


    public List<Skill> getAll(){

        return jdbcSkillRep.getAll();
    }

    public Skill update(Skill skill) {

        return jdbcSkillRep.update(skill);
    }

    public void deleteById(Long id) {

        jdbcSkillRep.deleteById(id);
    }

    public Skill save(Skill skill) {
        jdbcSkillRep.save(skill);
        return skill;
    }

    public Skill getById(Long id) {

        return jdbcSkillRep.getById(id);
    }

}
