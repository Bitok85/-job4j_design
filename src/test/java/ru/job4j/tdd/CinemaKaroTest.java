package ru.job4j.tdd;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaKaroTest {

    @Ignore
    @Test
    public void whenBuy() {
        Account account = new AccountKaro();
        Cinema cinema = new CinemaKaro();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new TicketKaro()));
    }

    @Ignore
    @Test
    public void whenFind() {
        Cinema cinema = new CinemaKaro();
        cinema.add(new SessionKaro());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new SessionKaro())));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
        public void whenBuyingRowPlaceDoesntExist() {
        Account account = new AccountKaro();
        Cinema cinema = new CinemaKaro();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 50, 1, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenBuyingColumnPlaceDoesntExist() {
        Account account = new AccountKaro();
        Cinema cinema = new CinemaKaro();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 50, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenSessionDateIsEarlierThenPresentDate() {
        Account account = new AccountKaro();
        Cinema cinema = new CinemaKaro();
        Calendar date = Calendar.getInstance();
        date.set(2000, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 5, 1, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenTicketIsAlreadySold() {
        Account account = new AccountKaro();
        Cinema cinema = new CinemaKaro();
        Calendar date = Calendar.getInstance();
        date.set(2000, 10, 10, 23, 00);
        Ticket ticket1 = cinema.buy(account, 5, 1, date);
        Ticket ticket2 = cinema.buy(account, 5, 1, date);
    }
}