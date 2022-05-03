package ru.job4j.ood.srp.reports;

import java.util.List;
import java.util.function.Predicate;

public class HrReportEngine implements Report {

    private MemStore store;

    public HrReportEngine(MemStore store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        store.descSortBySalary();
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        text.append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

}
