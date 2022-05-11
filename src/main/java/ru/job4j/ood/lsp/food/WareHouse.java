package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public class WareHouse implements FoodDistribution {

    private List<Food> wareHouse = new ArrayList<>();

    @Override
    public void sendFood(Food food) {
        wareHouse.add(food);
    }

    @Override
    public Food get(int index) {
        return wareHouse.get(index);
    }
}
