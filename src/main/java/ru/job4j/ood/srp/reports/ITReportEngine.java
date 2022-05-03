package ru.job4j.ood.srp.reports;

import java.util.Calendar;
import java.util.function.Predicate;

public class ITReportEngine implements Report {

    private MemStore store;

    public ITReportEngine(MemStore store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        HtmlParser parser = new HtmlParser();
        return parser.parse(store.findBy(filter));
    }

    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        MemStore store = new MemStore();
        store.add(new Employee("Ivan", now, now, 100));
        store.add(new Employee("Petr", now, now, 10.1));
        store.add(new Employee("Kate", now, now, 355.2));
        ITReportEngine reportEngine = new ITReportEngine(store);
        System.out.println(reportEngine.generate(em -> true));
    }
}


