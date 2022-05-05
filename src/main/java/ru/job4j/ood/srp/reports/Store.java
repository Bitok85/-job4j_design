package ru.job4j.ood.srp.reports;

import ru.job4j.ood.srp.reports.Employee;

import java.util.List;
import java.util.function.Predicate;

public interface Store {

    void add(Employee employee);

    List<Employee> findBy(Predicate<Employee> filter);

    void descSortBySalary();
}
