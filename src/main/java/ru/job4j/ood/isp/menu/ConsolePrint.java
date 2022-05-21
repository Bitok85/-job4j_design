package ru.job4j.ood.isp.menu;

public class ConsolePrint implements MenuPrinter {

    private static final String BASE_INDENT = "---";

    @Override
    public void print(Menu menu) {
        menu.forEach(item -> System.out.println(indentAdd(item.getNumber()) + item.getName()));

    }

    private String indentAdd(String number) {
        int multiplier = number.length() / 2 - 1;
        return BASE_INDENT.repeat(multiplier) + number;
    }

}
