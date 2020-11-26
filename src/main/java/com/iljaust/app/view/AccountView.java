package com.iljaust.app.view;

import com.iljaust.app.controller.AccountController;
import com.iljaust.app.model.Account;
import com.iljaust.app.model.AccountStatus;

import java.util.List;
import java.util.Scanner;

public class AccountView {
    private final AccountController controller = new AccountController();
    private final Scanner scan = new Scanner(System.in);

    private AccountStatus checkStatus(Integer num){
        AccountStatus accStatus;
        switch (num){
            case 1: accStatus = AccountStatus.ACTIVE;
                break;
            case 2: accStatus = AccountStatus.DELETED;
                break;
            default: accStatus = AccountStatus.BANNED;
        }
        return accStatus;
    }

    public void deleteById(){
        Long inputID = scan.nextLong();
        controller.deleteById(inputID);
    }

    public void save(){
        String accountData = scan.nextLine();
        Integer accStatus = scan.nextInt();
        AccountStatus accountStatus = checkStatus(accStatus);
        Account account = new Account(null,accountData,accountStatus);
        controller.save(account);
    }

    public void getByIdAcc(){
        Long inputID = scan.nextLong();
        Account account = controller.getById(inputID);
        System.out.println(account);
    }

    public void update(){
        long id = scan.nextLong();
        String accountData = scan.nextLine();
        Integer accStatus = scan.nextInt();
        AccountStatus accountStatus = checkStatus(accStatus);

        controller.update(new Account(id,accountData,accountStatus));
    }

    public void getAll(){
        List<Account> accounts = controller.getAll();

        for(Account account : accounts){
            System.out.println(account);
        }
    }
}
