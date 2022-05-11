package ru.job4j.ood.lsp.food;

import java.time.Duration;
import java.time.LocalDateTime;

public class ControlQuality {

    private static final int EXPIRED = 100;

    private Food food;
    private int lowExpLimit;
    private int highExpLimit;
    private int discount;

    private double currentPercentage;

    public ControlQuality(Food food, int lowExpLimit, int highExpLimit, int discount) {
        this.food = food;
        this.lowExpLimit = lowExpLimit;
        this.highExpLimit = highExpLimit;
        this.discount = discount;
    }

    public FoodDistribution execute() {
        expirationDatePercent();
        if (currentPercentage == EXPIRED) {
            FoodDistribution trash = new Trash();
            trash.sendFood(food);
            return trash;
        } else if (currentPercentage < lowExpLimit) {
            FoodDistribution wareHouse = new WareHouse();
            wareHouse.sendFood(food);
            return wareHouse;
        } else {
            FoodDistribution shop = new Shop();
            if (currentPercentage > 75) {
                food.setDiscount(discount);
            }
            shop.sendFood(food);
            return shop;
        }

    }

    private void expirationDatePercent() {
        long hBetweenCrExp
                = Duration.between(food.getCreateDate(), food.getExpireDate()).toHours();
        long hBetweenCrNow
                = Duration.between(food.getCreateDate(), LocalDateTime.now()).toHours();
        currentPercentage = (double) hBetweenCrNow / hBetweenCrExp * 100;
    }
}
