package ru.job4j.ood.srp;

import java.util.HashMap;
import java.util.List;


public class ParseB implements ParseBill {
    /**
    В данном класе реализуется один интерфейс, который призван парсить
     почту на предмет писем содержащих определённые ссылки на счета и далее парсить содержимое уже этих url.
     Это должно быть описано в разных абстракциях, т.к. этот класс в реализации выполняет сразу две задачи.
     */
    @Override
    public HashMap parseMail(String url) {
        return null;
    }

    @Override
    public List parseUrl(HashMap urls) {
        return null;
    }
}
