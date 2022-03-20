package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private static Properties properties = new Properties();
    private static final Logger LOG = LoggerFactory.getLogger(TableEditor.class.getName());

    public TableEditor(Properties properties) throws  IOException, SQLException, ClassNotFoundException {
        TableEditor.properties = properties;
        initConnection();
    }

    public static void main(String[] args) throws Exception {
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable("Alex");
        tableEditor.addColumn("Alex", "name", "text");
        tableEditor.renameColumn("Alex", "name", "age");
        tableEditor.dropColumn("Alex", "age");
        tableEditor.dropTable("Alex");
        tableEditor.close();
    }

    private void initConnection() throws IOException, SQLException, ClassNotFoundException {
        properties.load(new FileReader("./data/PSQL_login.properties"));
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("Admin"),
                properties.getProperty("Password")
        );
    }

    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.join(
                    " ",
                    "create table", tableName, "(id serial primary key);");
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        } catch (Exception e) {
            LOG.error("Exception", e);
        }

    }

    public void dropTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = "drop table Alex;";
            statement.execute(sql);
            LOG.info("Table " + tableName + " has been dropped");
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.join(
                    " ",
                    "alter table", tableName, "add column", columnName, type);
            statement.executeUpdate(sql);
            System.out.println(getTableScheme(connection, tableName));
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.join(
                    " ",
                    "alter table", tableName, "drop column", columnName);
            statement.executeUpdate(sql);
            System.out.println(getTableScheme(connection, tableName));
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.join(
                    " ",
                    "alter table", tableName, "rename column", columnName, "to", newColumnName);
            statement.executeUpdate(sql);
            System.out.println(getTableScheme(connection, tableName));
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
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


    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
