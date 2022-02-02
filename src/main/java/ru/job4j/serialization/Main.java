package ru.job4j.serialization;

import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Auto auto = new Auto(
                true,
                2022,
                new Engine("Diesel", 3.6f),
                new String[]{"A+", "B", "A-"}
        );
        JAXBContext context = JAXBContext.newInstance(Auto.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(auto, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Auto result = (Auto) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
        System.out.println();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("new/old", auto.isNewOld());
        jsonObject.put("year of manufacture", auto.getYearOfManufacture());
        jsonObject.put("engine", auto.getEngine());
        jsonObject.put("crash tests", auto.getCrashTests());
        System.out.println(jsonObject);
        System.out.println(new JSONObject(auto));

    }

}
