package ru.job4j.ood.lsp.food;


import java.util.List;

public interface Storage {

    boolean add(FoodDistribution foodDistribution);

     List<FoodDistribution> get();
}
