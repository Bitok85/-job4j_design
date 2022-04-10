package ru.job4j.gc;

public class Person {


    public Person() {
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("del");
    }

}
