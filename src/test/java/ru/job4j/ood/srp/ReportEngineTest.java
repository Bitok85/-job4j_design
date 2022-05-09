package ru.job4j.ood.srp;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.ood.srp.reports.*;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Alex", now, now, 100);
        store.add(worker);
        Report reportEngine = new ReportEngine(store);
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
        Report reportEngine = new ReportCountingRoom(store);
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
        Report reportEngine = new HrReportEngine(store);
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
        Report reportEngine = new ITReportEngine(store);

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

        html.add("<tr>");
        html.add(String.format("<td>%s</td>", employee1.getName()));
        html.add(String.format("<td>%s</td>", dateOnly.format(employee1.getHired().getTime())));
        html.add(String.format("<td>%s</td>", dateOnly.format(employee1.getFired().getTime())));
        html.add(String.format("<td>%s</td>", employee1.getSalary()));
        html.add("</tr>");

        html.add("<tr>");
        html.add(String.format("<td>%s</td>", employee2.getName()));
        html.add(String.format("<td>%s</td>", dateOnly.format(employee2.getHired().getTime())));
        html.add(String.format("<td>%s</td>", dateOnly.format(employee2.getFired().getTime())));
        html.add(String.format("<td>%s</td>", employee2.getSalary()));
        html.add("</tr>");

        html.add("</table>");
        html.add("</body>");
        html.add("</html>");
        assertThat(reportEngine.generate(em -> true), is(html.toString()));
    }

    @Test
    public void whenXmlReport() throws JAXBException {
        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.MAY, 8);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateStr = formatter.format(date.getTime());
        Store store = new MemStore();
        Employee emp1 = new Employee("Ivan", date, date, 150);
        Employee emp2 = new Employee("Kate", date, date, 140);
        store.add(emp1);
        store.add(emp2);
        Report report = new XmlReport(store);
        String empXmlTemplate = "<employee name=\"%s\" hired=\"%s\" fired=\"%s\" salary=\"%.1f\"/>";
        StringBuilder expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("<Employees>")
                .append(String.format(Locale.US, empXmlTemplate,
                        emp1.getName(), dateStr, dateStr, emp1.getSalary()))
                .append(String.format(Locale.US, empXmlTemplate,
                        emp2.getName(), dateStr, dateStr, emp2.getSalary()))
                .append("</Employees>");
        String rsl = report.generate(em -> true).replaceAll("\\n\\s*", "");
        assertThat(rsl, is(expected.toString()));
    }

    @Test
    public void whenGsonReport() {
        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.MAY, 8);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateStr = formatter.format(date.getTime());
        Store store = new MemStore();
        Employee emp1 = new Employee("Ivan", date, date, 140);
        Employee emp2 = new Employee("Kate", date, date, 140);
        store.add(emp1);
        store.add(emp2);
        Report report = new JSONReport(store);
        String empJsonTemplate =
                "{\"name\":\"%s\",\"hired\":\"%s\",\"fired\":\"%s\",\"salary\":%.1f}";
        StringBuilder expected = new StringBuilder()
                .append("[")
                .append(String.format(Locale.US, empJsonTemplate + ",",
                        emp1.getName(), dateStr, dateStr, emp1.getSalary()))
                .append(String.format(Locale.US, empJsonTemplate,
                        emp2.getName(), dateStr, dateStr, emp2.getSalary()))
                .append("]");
        assertThat(report.generate(em -> true), is(expected.toString()));

    }


}