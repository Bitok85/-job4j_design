package ru.job4j.ood.lsp.food;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    @Test
    public void whenExpirationLower25ThenWareHouseSend() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.plusHours(2000);
        LocalDateTime created = now.minusHours(300);
        Food potato = new Potato("Potato", created, expired, 98.75, 0);
        ControlQuality controlQuality = new ControlQuality(potato, 25, 75, 10);
        FoodDistribution wareHouse = controlQuality.execute();
        assertThat(wareHouse.get(0), is(potato));
    }

    @Test
    public void whenExpirationBetween25And75ThenShopWithoutDiscountSend() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.plusHours(100);
        LocalDateTime created = now.minusHours(50);
        Food meat = new Meat("Meat", created, expired, 299.22, 0);
        ControlQuality controlQuality = new ControlQuality(meat, 25, 75, 10);
        FoodDistribution shop = controlQuality.execute();
        assertThat(shop.get(0), is(meat));
        assertThat(shop.get(0).getDiscount(), is(0));
    }

    @Test
    public void whenExpirationBetween75And100ThenSendShopWithDiscount() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.plusHours(50);
        LocalDateTime created = now.minusHours(200);
        Food meat = new Meat("Meat", created, expired, 299.22, 0);
        ControlQuality controlQuality = new ControlQuality(meat, 25, 75, 10);
        FoodDistribution shop = controlQuality.execute();
        assertThat(shop.get(0), is(meat));
        assertThat(shop.get(0).getDiscount(), is(10));
    }

    @Test
    public void whenFoodExpiredThenSendTrash() {
        LocalDateTime expired = LocalDateTime.now();
        LocalDateTime created = expired.minusHours(200);
        Food cheese = new Meat("Cheese", created, expired, 299.22, 0);
        ControlQuality controlQuality = new ControlQuality(cheese, 25, 75, 10);
        FoodDistribution trash = controlQuality.execute();
        assertThat(trash.get(0), is(cheese));
    }

}