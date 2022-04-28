package ru.job4j.tdd;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class CinemaKaro implements Cinema {
    @Override
    public List<Session> find(Predicate<Session> filter) {
        return null;
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return null;
    }

    @Override
    public void add(Session session) {

    }
}
