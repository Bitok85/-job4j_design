package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    Storage distributions = new DefaultFoodStorage();

    public void execute(List<Food> foodList) {
        for (Food food : foodList) {
            for (FoodDistribution send : distributions.get()) {
                if (send.acceptFood(food)) {
                    send.sendFood(food);
                    break;
                }
            }
        }
    }

    public void resort() {
        List<Food> tmpFoodList = new ArrayList<>();
        for (FoodDistribution foodDistribution : distributions.get()) {
            tmpFoodList.addAll(foodDistribution.getAll());
            foodDistribution.clear();
        }
        execute(tmpFoodList);
    }

    public List<FoodDistribution> getStorage() {
        return distributions.get();
    }


}
