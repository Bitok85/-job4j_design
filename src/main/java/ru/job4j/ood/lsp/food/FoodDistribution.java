package ru.job4j.ood.lsp.food;

public interface FoodDistribution {

    public void sendFood(Food food);

    public Food get(int index);

}
