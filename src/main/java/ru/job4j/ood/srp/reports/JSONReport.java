package ru.job4j.ood.srp.reports;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Predicate;

public class JSONReport implements Report {

    private Store store;

    public JSONReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Calendar.class, new CalendarJsonAdapt());
        builder.registerTypeAdapter(GregorianCalendar.class, new CalendarJsonAdapt());
        Gson gson = builder.create();
        return gson.toJson(employees);
    }

    public static void main(String[] args) {
        Calendar date = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        date.set(2022, Calendar.MAY, 8);
        Store store = new MemStore();
        Employee employee = new Employee("Ivan", date, date, 140);
        store.add(employee);
        Report report = new JSONReport(store);
        System.out.println(report.generate(em -> true));
    }
}
