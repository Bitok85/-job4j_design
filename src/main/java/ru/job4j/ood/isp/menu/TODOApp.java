package ru.job4j.ood.isp.menu;

public class TODOApp {

    private Menu menu = new SimpleMenu();
    private MenuPrinter printer = new ConsolePrint();

    public boolean addMenuItem(String parentName, String childName, ActionDelegate actionDelegate) {
        return menu.add(parentName, childName, actionDelegate);
    }

    public void print() {
        printer.print(menu);
    }
}
