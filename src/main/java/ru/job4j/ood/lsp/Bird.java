package ru.job4j.ood.lsp;

/**
 * Здесь нарушается принцип LSP, т.к. при реализации класса-наследника, метод fly() не будет корректно применим
 * - курица птица, но летать не умеет.
 * В подобном случае, было бы правильно определить общий метод move(),
 * и переопределяя его в наследниках уже прописывать логику для перемещения каждой отдельной птицы.
 */

public class Bird {

    public void fly() {
    }
}

class Hen extends Bird {
}