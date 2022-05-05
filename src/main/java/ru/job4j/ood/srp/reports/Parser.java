package ru.job4j.ood.srp.reports;

import ru.job4j.ood.srp.reports.Employee;

import java.util.List;

public interface Parser {

    public String parse(List<Employee> employees);

}
