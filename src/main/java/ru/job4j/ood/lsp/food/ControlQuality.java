package ru.job4j.ood.lsp.food;

import java.util.List;

public class ControlQuality {

    private List<FoodDistribution> sendList;


    public ControlQuality(List<FoodDistribution> sendList) {
        this.sendList = sendList;
    }

    public void execute(List<Food> foodList) {
        for (Food food : foodList) {
            for (FoodDistribution send : sendList) {
                if (send.acceptFood(food)) {
                    send.sendFood(food);
                    break;
                }
            }
        }
    }

    public FoodDistribution get(int index) {
        return sendList.get(index);
    }
}
