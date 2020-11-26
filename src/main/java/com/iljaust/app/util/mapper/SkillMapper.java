package com.iljaust.app.util.mapper;

import com.iljaust.app.model.Skill;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillMapper {
    public static Skill mapToSkill(ResultSet resultSet) throws SQLException {
        Skill skill = new Skill();

        skill.setId((resultSet.getLong("id")));
        skill.setName((resultSet.getString("name")));

        return skill;
    }
}
