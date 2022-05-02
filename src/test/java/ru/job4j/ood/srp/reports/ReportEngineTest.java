package ru.job4j.ood.srp.reports;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Alex", now, now, 100);
        store.add(worker);
        ReportEngine reportEngine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(reportEngine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenCountingOldRoomGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Alex", now, now, "100");
        store.add(worker);
        ReportCountingRoom reportEngine = new ReportCountingRoom(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalaryNew()).append(";")
                .append(System.lineSeparator());
        assertThat(reportEngine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHrDeptGenerated() {
        HrStore store = new HrStore();
        Employee worker1 = new Employee("Ivan", 255.55);
        Employee worker2 = new Employee("Ivan", 255.55);
        Employee worker3 = new Employee("Alexey", 320);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        HrReportEngine reportEngine = new HrReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(reportEngine.generate(em -> true), is(expect.toString()));

    }


}