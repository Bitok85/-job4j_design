package ru.job4j.gc;

import java.util.ArrayList;
import java.util.List;

public class GCDemo {

    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void main(String[] args) {
       Person person1 = new Person();
       System.out.println(ENVIRONMENT.freeMemory() / 1000);
       Person person2 = new Person();
       System.out.println(ENVIRONMENT.freeMemory() / 1000);
       Person person3 = new Person();
       System.out.println(ENVIRONMENT.freeMemory() / 1000);
       Person person4 = new Person();
       System.out.println(ENVIRONMENT.freeMemory() / 1000);
       Person person5 = new Person();
       System.out.println(ENVIRONMENT.freeMemory() / 1000);



    }
}
