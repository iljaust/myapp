package com.iljaust.app.util.mapper;

import com.iljaust.app.model.Skill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillListMapper {
    public static List<Skill> mapToSkill(ResultSet resultSet) throws SQLException {
        Skill skill;
        List<Skill> skillList = new ArrayList<>();

        while (resultSet.next()) {
            skill = new Skill();
            skill.setId((resultSet.getLong("id")));
            skill.setName((resultSet.getString("name")));

            skillList.add(skill);
        }

        return skillList;
    }
}
