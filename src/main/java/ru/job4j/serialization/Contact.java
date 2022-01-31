package ru.job4j.serialization;

import java.io.*;
import java.nio.file.Files;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class Contact implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(Contact.class.getName());
    private static long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BasicConfigurator.configure();
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        File tempFile = Files.createTempFile("tmpContact", ".data").toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
                 final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
             } catch (Exception e) {
            LOG.error("Exception", e);
        }
    }
}
