package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop implements FoodDistribution {

    private List<Food> shop = new ArrayList<>();

    @Override
    public boolean sendFood(Food food) {
        boolean result = false;
        if (acceptFood(food)) {
            if (expirationDatePercent(food) > HIGH_EXPIRATION) {
                food.setPrice(food.getPrice() * (1 - food.getDiscount() / 100));
            }
            result = shop.add(food);
        }
        return result;
    }

    @Override
    public Food get(int index) {
        return shop.get(index);
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(shop);
    }

    @Override
    public boolean acceptFood(Food food) {
        return expirationDatePercent(food) > LOW_EXPIRATION && expirationDatePercent(food) < EXPIRED;
    }

    @Override
    public void clear() {
        shop.clear();
    }
}
