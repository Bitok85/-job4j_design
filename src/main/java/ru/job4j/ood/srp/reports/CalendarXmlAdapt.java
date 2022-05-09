package ru.job4j.ood.srp.reports;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarXmlAdapt extends XmlAdapter<String, Calendar> {

    private static final ThreadLocal<DateFormat> DATE_FORMAT
            = ThreadLocal.withInitial(() -> new SimpleDateFormat("dd-MM-yyyy"));

    @Override
    public String marshal(Calendar calendar) throws Exception {
        return DATE_FORMAT.get().format(calendar.getTime());
    }

    @Override
    public Calendar unmarshal(String s) throws Exception {
        return null;
    }


}
