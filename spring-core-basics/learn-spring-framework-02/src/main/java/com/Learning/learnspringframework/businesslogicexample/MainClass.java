package com.Learning.learnspringframework.businesslogicexample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class MainClass {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(MainClass.class);
        System.out.println(context.getBean(BusinessCalculationService.class).findMaX());
    }
}
