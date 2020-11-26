package com.iljaust.app.service;

import com.iljaust.app.model.Skill;
import com.iljaust.app.repository.jdbc.JDBCAccountRepIpml;
import com.iljaust.app.repository.jdbc.JDBCSkillRepImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class SkillServiceTest {
    @Mock
    private static JDBCSkillRepImpl jdbcSkillRep;

    private  static SkillService skillService;

    public SkillServiceTest(){
        MockitoAnnotations.initMocks(this);
        skillService = new SkillService(jdbcSkillRep);
    }

    private  static Skill skill;
    private  static Skill skill2;

    @BeforeClass
    public static void setUp(){

        skill = new Skill((long)1,"Java");
        skill2 = new Skill((long)2,"JavaScript");

    }


    @Test
    public void getAll() {
        when(jdbcSkillRep.getAll()).thenReturn(Arrays.asList(skill,skill2));

        List<Skill> skills = new ArrayList<>();
        skills.add(skill);
        skills.add(skill2);
        List<Skill> skillList = skillService.getAll();

        assertThat(skillList).isEqualTo(skills);
    }
    @Test
    public void update() {
        when(jdbcSkillRep.update(any(Skill.class))).thenReturn(skill);

        Skill skillForUpdate = new Skill((long)1, "Python");
        Skill resultSkill = skillService.update(skillForUpdate);

        assertNotNull(resultSkill);
        assertSame(resultSkill.getId(),skill.getId());
    }

    @Test
    public void deleteById() {
        Long skillId = (long)1;

        skillService.deleteById(skillId);

        verify(jdbcSkillRep,times(1)).deleteById(eq(skillId));
    }

    @Test
    public void save() {
        when(jdbcSkillRep.save(any(Skill.class))).thenReturn(skill);

        Skill newSkill = skillService.save(skill);

        assertNotNull(newSkill);
        assertEquals(newSkill.getId(), skill.getId());
        assertEquals(newSkill.getName(),skill.getName());
    }

    @Test
    public void getById() {
        when(jdbcSkillRep.getById(any(Long.class))).thenReturn(skill);

        Long id = (long) 1;

        Skill skill = skillService.getById(id);

        assertNotNull(skill);
        assertEquals(1, skill.getId());

    }

}