package ru.job4j.gc;

public class GCDemo {

    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void main(String[] args) {
        System.out.println(ENVIRONMENT.freeMemory() / 1000);
        Person person1 = new Person();
        System.out.println(ENVIRONMENT.freeMemory() / 1000);
        Person person2 = new Person();
        System.out.println(ENVIRONMENT.freeMemory() / 1000);
        person1 = person2;
        System.gc();
        System.out.println(ENVIRONMENT.freeMemory() / 1000);

    }
}
