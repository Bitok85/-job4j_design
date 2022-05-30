package ru.job4j.ood.isp.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.StringJoiner;

public class TODOApp {

    private static final Logger LOG = LoggerFactory.getLogger(TODOApp.class.getName());
    private static final ActionDelegate STUB_ACTION = System.out::println;

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new ConsolePrint();
        try (Scanner scanner = new Scanner(System.in)) {
            schemePrint();
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (inputValidation(input)) {
                    int schemeItem = Integer.parseInt(input);
                    if (schemeItem == 4) {
                        System.out.println("BB");
                        break;
                    } else if (schemeItem == 1) {
                        addMenuOptionsPrint();
                        String menuAdd = scanner.nextLine();
                        if (addAction(menuAdd, menu)) {
                            System.out.println("Выберете следующее действие");
                        } else {
                            schemePrint();
                        }

                    } else if (schemeItem == 2) {
                        actionAction();
                        System.out.println("Выберете следующее действие");
                    } else if (schemeItem == 3) {
                        printAction(menu, printer);
                        System.out.println("Выберете следующее действие");
                    }
                } else {
                    schemePrint();
                }
            }
        }
    }

    private static void schemePrint() {
        StringJoiner scheme = new StringJoiner(System.lineSeparator());
        scheme.add("Выберите действие с меню:");
        scheme.add("1. Добавить пункт меню.");
        scheme.add("2. Выполнить действие ");
        scheme.add("3. Вывести меню на экран");
        scheme.add("4. Завершить работу");
        System.out.println(scheme);
    }

    private static void addMenuOptionsPrint() {
        System.out.println("Ведите пункт меню, который хотите расширить и название подпункта через запятую.");
        System.out.println("Если вы хотите добавить новый корневой пункт, просто введите его название.");
    }

    private static boolean addAction(String input, Menu menu) {
        boolean result = true;
        String[] inputArr = input.split(", ");
        if (inputArr.length > 2 || inputArr.length < 1) {
            result = false;
            LOG.info("Некорректный ввод, попробуйте заново");
        }
        if (inputArr.length == 2) {
            menu.add(inputArr[0], inputArr[1], STUB_ACTION);
            LOG.info("Пункт {} добавлен к {}", inputArr[1], inputArr[0]);
        } else if (inputArr.length == 1) {
            menu.add(Menu.ROOT, inputArr[0], STUB_ACTION);
            LOG.info("Пункт {} добавлен в корневой каталог", inputArr[0]);
        }
        return result;
    }


    private static void actionAction() {
        System.out.println("Сервис добавления действия к пункту меню в разработке");
    }

    private static void printAction(Menu menu, MenuPrinter print) {
        print.print(menu);
    }

    private static boolean inputValidation(String input) {
        boolean result = true;
        try {
            int intInput = Integer.parseInt(input);
            if (intInput < 1 || intInput > 4) {
                result = false;
                throw new IllegalArgumentException("Такого пункта меню не существует");

            }
        } catch (Exception e) {
            result = false;
            LOG.error("Вы ввели не число");
        }
        return result;
    }

}
