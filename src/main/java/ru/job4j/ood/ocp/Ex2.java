package ru.job4j.ood.ocp;

/**
 * Собака и кошка разные по контексту объекты, хоть и могут иметь схожие методы.
 * В данном случае естественно необходим абстрактный класс или интерфейс для описания методов этих классов.
 */

public class Ex2 {

    private static class Dog {
        public void care() {
        }
    }

    private static class Cat extends Dog {
        @Override
        public void care() {
        }
    }

}
