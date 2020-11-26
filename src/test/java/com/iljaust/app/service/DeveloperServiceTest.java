package com.iljaust.app.service;

import com.iljaust.app.model.Account;
import com.iljaust.app.model.AccountStatus;
import com.iljaust.app.model.Developer;
import com.iljaust.app.model.Skill;
import com.iljaust.app.repository.jdbc.JDBCDeveloperRepImpl;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class DeveloperServiceTest {
    @Mock
    private static JDBCDeveloperRepImpl jdbcDeveloperRep;
    private static DeveloperService developerService;

    private static Developer developer;
    private static Developer developer2;

    public DeveloperServiceTest(){
        MockitoAnnotations.initMocks(this);
        developerService = new DeveloperService(jdbcDeveloperRep);
    }

    @BeforeClass
    void setUp() {
        Skill skill = new Skill((long)1,"Java");
        Skill skill2 = new Skill((long)2,"JavaScript");
        Skill skill3 = new Skill((long)2,"Python");

        List<Skill> skillList = new ArrayList<>();
        skillList.add(skill);
        skillList.add(skill2);

        List<Skill> skillList2 = new ArrayList<>();
        skillList.add(skill2);
        skillList.add(skill3);

        Account account = new Account((long)1,"Good dev", AccountStatus.ACTIVE);
        Account account2 = new Account((long)2,"Bad dev",AccountStatus.BANNED);



        developer = new Developer.DeveloperBuilder("John Jones",(long)1)
                .skillSet(skillList)
                .accountStatus(account)
                .build();

        developer2 = new Developer.DeveloperBuilder("Max Read",(long)2)
                .skillSet(skillList2)
                .accountStatus(account2)
                .build();
    }

    @Test
    void getAll() {
        when(jdbcDeveloperRep.getAll()).thenReturn(Arrays.asList(developer,developer2));

        List<Developer> developers = new ArrayList<>();
        developers.add(developer);
        developers.add(developer2);
        List<Developer> developerList = developerService.getAll();

        assertThat(developerList).isEqualTo(developers);
    }

    @Test
    void update() {
        when(jdbcDeveloperRep.update(any(Developer.class))).thenReturn(developer);

        Developer resultDeveloper = developerService.update(developer);

        assertNotNull(resultDeveloper);
        assertSame(resultDeveloper.getId(),developer.getId());
    }

    @Test
    void deleteById() {
        Long developerId = (long)1;

        developerService.deleteById(developerId);

        verify(jdbcDeveloperRep,times(1)).deleteById(eq(developerId));
    }

    @Test
    void save() {
        when(jdbcDeveloperRep.save(any(Developer.class))).thenReturn(developer);

        Developer newDeveloper = developerService.save(developer);

        assertNotNull(newDeveloper);
        assertEquals(newDeveloper.getId(),developer.getId());
        assertEquals(newDeveloper.getName(),developer.getName());
    }

    @Test
    void getById() {
        when(jdbcDeveloperRep.getById(any(Long.class))).thenReturn(developer);

        Long id = (long) 1;

        Developer developer = developerService.getById(id);

        assertNotNull(developer);
        assertEquals(1, developer.getId());
    }
}