package ru.job4j.ood.srp.reports;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemStore implements Store {

    private final List<Employee> employees = new ArrayList<>();

    public void add(Employee employee) {
        employees.add(employee);
    }

    public void descSortBySalary() {
        employees.sort((Employee em1, Employee em2) -> Double.compare(em2.getSalary(), em1.getSalary()));
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }
}
