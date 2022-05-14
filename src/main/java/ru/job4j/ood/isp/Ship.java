package ru.job4j.ood.isp;

/**
 * В данном случае логичнее будет создать отдельные интерфейсы для кораблей и подводных лодок.
 */

public interface Ship {

    void sail();

    void dive();
}

class Battleship implements Ship {

    @Override
    public void sail() {

    }

    @Override
    public void dive() {
    }
}

class Submarine implements Ship {

    @Override
    public void sail() {

    }

    @Override
    public void dive() {

    }
}
