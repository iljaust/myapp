package com.iljaust.app.view;

import com.iljaust.app.controller.DeveloperController;
import com.iljaust.app.model.Developer;

import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    private final DeveloperController controller = new DeveloperController();
    private final Scanner scan = new Scanner(System.in);

    public void deleteById(){
        Long inputID = scan.nextLong();
        controller.deleteById(inputID);
    }

    public void save(){
        String developerName = scan.nextLine();
        Developer developer = new Developer.DeveloperBuilder(developerName,null).build();
        controller.save(developer);
    }

    public void getByIdDev(){
        Long inputID = scan.nextLong();
        Developer developer = controller.getById(inputID);
        System.out.println(developer);
    }

    public void update(){
        long id = scan.nextLong();
        String developerName = scan.nextLine();
        controller.update(new Developer.DeveloperBuilder(developerName,id).build());
    }

    public void getAll(){
        List<Developer> developers = controller.getAll();

        for(Developer developer : developers){
            System.out.println(developer);
        }
    }
}
