package com.farshad.openFinDesk.infrastructure.avroserializerdeserializer;

import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Component
public class AvroSerealizer {

    private static final Logger logger = LoggerFactory.getLogger(AvroSerealizer.class);


    private SpecificRecordBase message;


    public SpecificRecordBase getMessage() {
        return message;
    }

    public void setMessage(SpecificRecordBase message) {
        this.message = message;
    }

    public byte[] serealizeJSON(SpecificRecordBase request) {
        message=request;
        DatumWriter<SpecificRecordBase> writer = new SpecificDatumWriter(message.getSchema());
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder = null;
        try {
            jsonEncoder = EncoderFactory.get()
                .jsonEncoder(message.getSchema(), stream);
            writer.write(request, jsonEncoder);
            jsonEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            logger.error("Serialization error " + e.getMessage());
        }
        return data;
    }

    public byte[] serealizeBinary(SpecificRecordBase request) {

        message=request;

        DatumWriter<SpecificRecordBase> writer = new SpecificDatumWriter(message.getSchema());
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder = EncoderFactory.get()
            .binaryEncoder(stream, null);
        try {
            writer.write(request, jsonEncoder);
            jsonEncoder.flush();
            data = stream.toByteArray();
        } catch (IOException e) {
            logger.error("Serialization error " + e.getMessage());
        }
        return data;
    }

}
