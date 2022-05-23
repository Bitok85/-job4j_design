package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trash implements FoodDistribution {

    private List<Food> trash = new ArrayList<>();

    @Override
    public boolean sendFood(Food food) {
        boolean result = false;
        if (acceptFood(food)) {
            result = trash.add(food);
        }
        return result;
    }

    @Override
    public Food get(int index) {
        return trash.get(index);
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(trash);
    }

    @Override
    public boolean acceptFood(Food food) {
        return expirationDatePercent(food) == EXPIRED;
    }

    @Override
    public void clear() {
        trash.clear();
    }
}
