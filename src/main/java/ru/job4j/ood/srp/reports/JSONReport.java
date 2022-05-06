package ru.job4j.ood.srp.reports;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBException;
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
        Gson gson = new GsonBuilder().create();
        return gson.toJson(employees);
    }

    public static void main(String[] args) throws JAXBException {
        Store store1 = new MemStore();
        Calendar date = new GregorianCalendar(2022, 05, 06);
        Employee employee = new Employee("Ivan", date, date, 140);
        store1.add(employee);
        Report report = new JSONReport(store1);
        System.out.println(report.generate(em -> true));

    }
}
