package service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateSerializer extends StdSerializer<LocalDate> {
    protected LocalDateSerializer() {
        super(LocalDate.class);
    }

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(localDate.getYear() + "-" +
                (localDate.getMonthValue()<10 ?String.format("0%d", localDate.getMonthValue()):localDate.getMonthValue())+
                "-" + (localDate.getDayOfMonth() < 10 ? String.format("0%d", localDate.getDayOfMonth())
                : localDate.getDayOfMonth()));
    }
}
