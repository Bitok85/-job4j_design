package ru.job4j.ood.lsp.food;

import java.time.LocalDateTime;

public class Meat extends Food {
    public Meat(String name, LocalDateTime createDate, LocalDateTime expireDate, double price, int discount) {
        super(name, createDate, expireDate, price, discount);
    }
}
