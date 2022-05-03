package ru.job4j.ood.srp.reports;

import javax.swing.text.DateFormatter;
import java.util.List;
import java.util.StringJoiner;

public class HtmlParser implements Parser {

    @Override
    public String parse(List<Employee> employees) {
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
            html.add(String.format("<td>%s</td>", emp.getHired()));
            html.add(String.format("<td>%s</td>", emp.getFired()));
            html.add(String.format("<td>%s</td>", emp.getSalary()));
            html.add("</tr>");
        }

        html.add("</table>");
        html.add("</body>");
        html.add("</html>");

        return html.toString();
    }
}
