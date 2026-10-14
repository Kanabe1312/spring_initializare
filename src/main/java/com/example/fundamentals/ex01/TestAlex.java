package com.example.fundamentals.ex01;

import org.springframework.stereotype.Component;

@Component
public class TestAlex {

    GreetingService greetingService;


    public  TestAlex(GreetingService greetingService) {

        this.greetingService = greetingService;
        afisare();

    }


    public void afisare(){
       String text= greetingService.greet("ce mai faci");
        System.out.println(text);
    }

}
