package ru.job4j.jdbc;

import ru.job4j.io.Config;

import  java.sql.*;
import java.util.StringJoiner;

public class StatementDemo {

    public static void main(String[] args) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "create table if not exists demo_table(%s, %s);",
                        "id serial primary key",
                        "name text"
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }

    private static Connection getConnection() throws Exception {
        Config config = new Config("./data/PSQL_login.properties");
        config.load();
        Class.forName(config.value("driver"));
        String url = config.value("url");
        String login = config.value("Admin");
        String password = config.value("Password");
        return DriverManager.getConnection(url, login, password);
    }

    private static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metadata = selection.getMetaData();
            for (int i = 1; i <= metadata.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metadata.getColumnName(i), metadata.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }
}
