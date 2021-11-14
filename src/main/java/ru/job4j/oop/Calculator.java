package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    private static int sum(int y) {
        return x + y;
    }

    private static int minus(int y) {
        return y - x;
    }

    public int multiply(int a) {
        return x * a;
    }

    public int divide(int a) {
        return a / x;
    }

    public int sumOfAllOperations(int a) {
        Calculator calc = new Calculator();
        return Calculator.sum(a) + calc.multiply(a) + Calculator.minus(a) + calc.divide(a);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(Calculator.sum(5));
        System.out.println(Calculator.minus(5));
        System.out.println(calc.divide(5));
        System.out.println(calc.multiply(5));
        System.out.println(calc.sumOfAllOperations(5));
    }
}
