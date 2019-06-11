package com.farshad.openFinDesk.infrastructure.avroserializerdeserializer;

import com.farshad.openFinDesk.model.AvroHttpRequest;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class AvroDeSerealizer {

    public Class<? extends SpecificRecordBase> getAnAvro() {
        return anAvro;
    }

    public void setAnAvro(Class<? extends SpecificRecordBase> anAvro) {
        this.anAvro = anAvro;
    }

    private static Logger logger = LoggerFactory.getLogger(AvroDeSerealizer.class);

    private Class<? extends SpecificRecordBase> anAvro;

    public <T extends SpecificRecordBase> T  deSerealizeJSON(byte[] data) {
        DatumReader<T> reader = new SpecificDatumReader(anAvro);
        Decoder decoder = null;
        try {
            decoder = DecoderFactory.get()
                .jsonDecoder(AvroHttpRequest.getClassSchema(), new String(data));
            return reader.read(null, decoder);
        } catch (IOException e) {
            logger.error("Deserialization error" + e.getMessage());
        }
        return null;
    }

    public  <T extends SpecificRecordBase> T deSerealizeBinary(byte[] data) {
        DatumReader<T> reader = new SpecificDatumReader(anAvro);
        Decoder decoder = DecoderFactory.get()
            .binaryDecoder(data, null);
        try {
            return reader.read(null, decoder);
        } catch (IOException e) {
            logger.error("Deserialization error" + e.getMessage());
        }
        return null;
    }
}
