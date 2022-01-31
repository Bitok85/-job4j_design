package ru.job4j.io;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        int age = 10;
        byte paws = 4;
        short friends = 3;
        long hair = 123243546436L;
        float milk = 3.4F;
        double money = 100.21324D;
        char firstLetter = 'K';
        boolean animal = true;
        LOG.debug("User info age : {}, paws {}, friends {}. hair {}, milk {}, money {}, firstLetter {}, animal {}",
                age, paws, friends, hair, milk, money, firstLetter, animal);
    }
}
