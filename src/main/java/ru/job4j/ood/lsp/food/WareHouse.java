package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public class WareHouse implements FoodDistribution {

    private List<Food> wareHouse = new ArrayList<>();

    @Override
    public boolean sendFood(Food food) {
        boolean result = false;
        if (acceptFood(food)) {
            result = wareHouse.add(food);
        }
        return result;
    }

    @Override
    public Food get(int index) {
        return wareHouse.get(index);
    }

    @Override
    public boolean acceptFood(Food food) {
        return expirationDatePercent(food) < LOW_EXPIRATION;
    }
}