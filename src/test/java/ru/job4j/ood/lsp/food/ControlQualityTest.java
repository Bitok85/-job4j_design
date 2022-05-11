package ru.job4j.ood.lsp.food;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void whenExpirationLower25ThenWareHouseSend() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.plusHours(2000);
        LocalDateTime created = now.minusHours(300);
        List<Food> foods = List.of(new Potato("potato", created, expired, 98.8, 10));
        ControlQuality controlQuality = new ControlQuality(foods);
        controlQuality.execute();
        assertThat(controlQuality.get(1).get(0).getName(), is("potato"));
    }

   @Test
    public void whenExpirationBetween25And75ThenShopWithoutDiscountSend() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.plusHours(100);
        LocalDateTime created = now.minusHours(50);
        Food meat = new Meat("Meat", created, expired, 299.22, 10);
        List<Food> foods = List.of(meat);
        ControlQuality controlQuality = new ControlQuality(foods);
        controlQuality.execute();
        assertThat(controlQuality.get(0).get(0).getName(), is("Meat"));
        assertThat(controlQuality.get(0).get(0).getPrice(), is(299.22));
    }

    @Test
    public void whenExpirationBetween75And100ThenSendShopWithDiscount() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.plusHours(50);
        LocalDateTime created = now.minusHours(200);
        Food meat = new Meat("Meat", created, expired, 300, 10);
        List<Food> foods = List.of(meat);
        ControlQuality controlQuality = new ControlQuality(foods);
        controlQuality.execute();
        assertThat(controlQuality.get(0).get(0).getName(), is("Meat"));
        assertThat(controlQuality.get(0).get(0).getPrice(), is(270D));
    }

    @Test
    public void whenFoodExpiredThenSendTrash() {
        LocalDateTime expired = LocalDateTime.now();
        LocalDateTime created = expired.minusHours(200);
        Food cheese = new Cheese("Cheese", created, expired, 345.55, 15);
        List<Food> foods = List.of(cheese);
        ControlQuality controlQuality = new ControlQuality(foods);
        controlQuality.execute();
        assertThat(controlQuality.get(2).get(0).getName(), is("Cheese"));
    }



}