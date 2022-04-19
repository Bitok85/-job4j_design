package ru.job4j.gc;

public class GCMetCall {

    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void main(String[] args) {
        Person person = new Person();
        for (int i = 0; i < 10000; i++) {
            if (i % 100 == 0) {
                System.out.println(ENVIRONMENT.freeMemory() / 1000);
            }
            person = getPerson();
        }
    }

    private static Person getPerson() {
        return new Person();
    }
}
