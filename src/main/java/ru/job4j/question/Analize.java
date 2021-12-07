package ru.job4j.question;

import java.util.*;
import java.util.Map;


public class Analize {

    /**
     * Метод строится на идее сравнения переведенных в HashMap множеств, где ключом является id, а
     * значением имя пользователя. Переопределение hashCode происходит только по id, что позволяет получить
     * одинаковые ключи в обоих map, в т.ч. при изменении имени пользователя. В результате появляется возможность
     * за линейное время сравнить линейно по ключам обе map и собрать данные для модели info.
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        HashMap<Integer, String> prevMap = Analize.toMap(previous);
        HashMap<Integer, String> currMap = Analize.toMap(current);
        for (Map.Entry<Integer, String> entry : prevMap.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            if (currMap.get(key) != null && !value.equals(currMap.get(key))) {
                info.setChanged(info.getChanged() + 1);
            } else if (currMap.get(key) == null) {
                info.setDeleted(info.getDeleted() + 1);
            }
        }
        for (Map.Entry<Integer, String> entry : currMap.entrySet()) {
            Integer key = entry.getKey();
            if (prevMap.get(key) == null) {
                info.setAdded(info.getAdded() + 1);
            }
        }

        return info;
    }

    private static HashMap<Integer, String> toMap(Set<User> set) {
        HashMap<Integer, String> map = new HashMap<>();
        for (User user : set) {
            map.put(user.getId(), user.getName());
        }
        return map;
    }
}
