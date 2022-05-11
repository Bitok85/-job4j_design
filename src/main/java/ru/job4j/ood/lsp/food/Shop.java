package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements FoodDistribution {

    private List<Food> shop = new ArrayList<>();

    @Override
    public void sendFood(Food food) {
        shop.add(food);
    }

    @Override
    public Food get(int index) {
        return shop.get(index);
    }
}
