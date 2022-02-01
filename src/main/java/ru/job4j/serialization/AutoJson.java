package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class AutoJson {

    public static void main(String[] args) {
        final Auto auto = new Auto(
                true,
                2022,
                new Engine("Diesel", 3.6f),
                new String[]{"A+", "B", "A-"}
        );

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(auto));

        final String autoJson =
                "{"
                    + "\"newOld\":false,"
                    + "\"yearOfManufacture\":2015,"
                    + "\"engine\":"
                        + "{"
                        + "\"type\":\"Gasoline\","
                        + "\"capacity\":\"2.0\""
                        + "},"
                        + "\"crashTests\":"
                        + "[\"A\",\"B-\",\"A+\"]"
                        + "}";

        final Auto autoMod = gson.fromJson(autoJson, Auto.class);
        System.out.println(autoMod);

    }
}
