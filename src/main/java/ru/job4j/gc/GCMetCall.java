package ru.job4j.gc;

public class GCMetCall {
    public static void main(String[] args) {
        Person person = new Person();
        for (int i = 0; i < 1000; i++) {
            person = getPerson();
        }
    }

    private static Person getPerson() {
        return new Person();
    }
}
