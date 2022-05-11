package ru.job4j.ood.lsp.food;

import java.util.List;

public class ControlQuality {

    private static final int EXPIRED = 100;

    private final List<FoodDistribution> sendList = List.of(
            new Shop(),
            new WareHouse(),
            new Trash()
    );
    private List<Food> foodList;

    public ControlQuality(List<Food> foodList) {
        this.foodList = foodList;
    }

    public void execute() {
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
