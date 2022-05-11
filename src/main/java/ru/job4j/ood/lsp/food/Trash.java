package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements FoodDistribution {

    private List<Food> trash = new ArrayList<>();

    @Override
    public void sendFood(Food food) {
        trash.add(food);
    }

    @Override
    public Food get(int index) {
        return trash.get(index);
    }
}
