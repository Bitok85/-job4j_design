package ru.job4j.ood.lsp.food;

import java.time.Duration;
import java.time.LocalDateTime;

public interface FoodDistribution {

    int LOW_EXPIRATION = 25;
    int HIGH_EXPIRATION = 75;
    int EXPIRED = 100;

    default double expirationDatePercent(Food food) {
        long hBetweenCrExp
                = Duration.between(food.getCreateDate(), food.getExpireDate()).toHours();
        long hBetweenCrNow
                = Duration.between(food.getCreateDate(), LocalDateTime.now()).toHours();
        return (double) hBetweenCrNow / hBetweenCrExp * 100;
    }

    boolean sendFood(Food food);

    Food get(int index);

    boolean acceptFood(Food food);

}
