package ru.job4j.ood.dip;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Приложение, которое должно найти в почте письмо с чеком и спарсить из него информацию.
 * Здесь логика в основном классе зависит от других классов, а не от абстракций. Любые изменения в будущем
 * по добавлению например новых почтовых сервисов или магазинов могут сломать работу парсера.
 * В данном случае необходимо создать абстракции для классов по работе с почтой и чеком и использовать их в
 * логике класса с основной логикой.
 *
 * 2. База куда заносятся данные, является полем основного класса. Для способов хранения данных необходимо
 * выделить отдельную абстракцию.
 *
 */

public class BillsParser {

    Map<Integer, String> base = new HashMap<>();

    private void parse(int a, String b) {
        GmailCheck gmailCheck = new GmailCheck();
        PerekrestokParse perekrestokParse = new PerekrestokParse();
        if (gmailCheck.checkMail() && perekrestokParse.parseBill()) {
            base.put(a, b);
        }
    }
}

class GmailCheck {
    boolean checkMail() {
        return false;
    }
}

class PerekrestokParse {
    boolean parseBill() {
        return false;
    }
}