package ru.job4j.ood.srp.reports;

import java.util.function.Predicate;

public class ITReportEngine implements Report {

    private Store store;

    public ITReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        HtmlParser parser = new HtmlParser();
        return parser.parse(store.findBy(filter));
    }

}


