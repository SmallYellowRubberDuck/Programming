package XmlUtils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeAdapter extends XmlAdapter<String, ZonedDateTime> {
    DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    @Override
    public String marshal(ZonedDateTime v) {
        return v.format(ISO_FORMATTER);
    }

    public ZonedDateTime unmarshal(String v) throws ParseException {
        return ZonedDateTime.parse(v);
    }
}