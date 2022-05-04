package ru.job4j.ood.srp.reports;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        Store store = new MemStore();
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
        Store store = new MemStore();
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
        Store store = new MemStore();
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

    @Test
    public void whenItHtmlReport() {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat dateOnly = new SimpleDateFormat("MM/dd/yyyy");
        Store store = new MemStore();
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee("Ivan", now, now, 150);
        Employee employee2 = new Employee("Kate", now, now, 300);
        store.add(employee1);
        store.add(employee2);
        employees.add(employee1);
        employees.add(employee2);
        ITReportEngine reportEngine = new ITReportEngine(store);

        StringJoiner html = new StringJoiner(System.lineSeparator());

        html.add("<!DOCTYPE html>");
        html.add("<html>");
        html.add("<head>");
        html.add("<meta charset=\"UTF-8\">");
        html.add("<title>Employers</title>");
        html.add("</head>");
        html.add("<body>");

        html.add("<table>");
        html.add("<tr>");
        html.add("<th>Name</th>");
        html.add("<th>Hired</th>");
        html.add("<th>Fired</th>");
        html.add("<th>Salary</th>");
        html.add("</tr>");

        for (Employee emp : employees) {
            html.add("<tr>");
            html.add(String.format("<td>%s</td>", emp.getName()));
            html.add(String.format("<td>%s</td>", dateOnly.format(emp.getHired().getTime())));
            html.add(String.format("<td>%s</td>", dateOnly.format(emp.getFired().getTime())));
            html.add(String.format("<td>%s</td>", emp.getSalary()));
            html.add("</tr>");
        }

        html.add("</table>");
        html.add("</body>");
        html.add("</html>");
        assertThat(reportEngine.generate(em -> true), is(html.toString()));

    }


}