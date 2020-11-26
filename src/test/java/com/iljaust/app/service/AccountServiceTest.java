package com.iljaust.app.service;

import com.iljaust.app.model.Account;
import com.iljaust.app.model.AccountStatus;
import com.iljaust.app.model.Skill;
import com.iljaust.app.repository.jdbc.JDBCAccountRepIpml;
import liquibase.pro.packaged.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class AccountServiceTest {
    @Mock
    private static JDBCAccountRepIpml jdbcAccountRepIpml;
    private static AccountService accountService;

    private  static Account account;
    private  static Account account2;

    public AccountServiceTest(){
        MockitoAnnotations.initMocks(this);
        accountService = new AccountService(jdbcAccountRepIpml);
    }

    @BeforeEach
    void setUp() {
        account = new Account((long)1,"Good dev", AccountStatus.ACTIVE);
        account2 = new Account((long)2,"Bad dev",AccountStatus.BANNED);
    }

    @Test
    void getAll() {
        when(jdbcAccountRepIpml.getAll()).thenReturn(Arrays.asList(account,account2));

        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        accounts.add(account2);
        List<Account> accountList = accountService.getAll();

        assertThat(accountList).isEqualTo(accounts);
    }

    @Test
    void update() {
        when(jdbcAccountRepIpml.update(any(Account.class))).thenReturn(account);

        Account accountForUpdate = new Account((long)1,"Mid dev",AccountStatus.ACTIVE);
        Account resultAccount = accountService.update(accountForUpdate);

        assertNotNull(resultAccount);
        assertSame(resultAccount.getId(),accountForUpdate.getId());
    }

    @Test
    void deleteById() {
        Long accountId = (long) 1;

        accountService.deleteById(accountId);

        verify(jdbcAccountRepIpml,times(1)).deleteById(eq(accountId));
    }

    @Test
    void save() {
        when(jdbcAccountRepIpml.save(any(Account.class))).thenReturn(account);

        Account newAccount = accountService.save(account);

        assertNotNull(newAccount);
        assertEquals(newAccount.getId(),account.getId());
        assertEquals(newAccount.getData(),account.getData());
        assertEquals(newAccount.getAccountStatus(),account.getAccountStatus());
    }

    @Test
    void getById() {
        when(jdbcAccountRepIpml.getById(any(Long.class))).thenReturn(account);

        Long id = (long) 1;

        Account account = accountService.getById(id);

        assertNotNull(account);
        assertEquals(1,account.getId());
    }
}