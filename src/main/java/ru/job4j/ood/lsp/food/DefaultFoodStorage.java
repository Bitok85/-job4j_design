package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;


public class DefaultFoodStorage implements Storage {

    private List<FoodDistribution> distributionList = new ArrayList<>();

    @Override
    public boolean add(FoodDistribution foodDistribution) {
        if (distributionList.contains(foodDistribution)) {
            return false;
        }
        distributionList.add(foodDistribution);
        return true;
    }

    @Override
    public List<FoodDistribution> get() {
        return distributionList;
    }

}
