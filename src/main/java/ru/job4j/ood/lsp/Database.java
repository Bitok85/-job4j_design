package ru.job4j.ood.lsp;

/**
 * Здесь класс-родитель описывает методы которые применимы не у всех наследников в принципе.
 * Mongo - не реляционная БД, и зытычка в виде исключения в методе join() может привести к проблемам.
 * Соответственно для разных типов ДБ должны быть разные абстракции, если мы хотим применять именно наследование.
 */

public class Database {
    public void connect() {
    }

    public void read() {
    }

    public void write() {
    }

    public void join() {
    }

}

class PostgreSql extends Database {
}

class MongoDatabase extends Database {

    @Override
    public void join() {
        System.out.println("Exception, Mongo has no join");
    }
}
