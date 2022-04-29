package ru.job4j.ood.srp;

import java.util.HashMap;
import java.util.List;

public interface ParseBill<T, V> {

    HashMap<T, V> parseMail(String url);

    List<V> parseUrl(HashMap<T, V> urls);
}


