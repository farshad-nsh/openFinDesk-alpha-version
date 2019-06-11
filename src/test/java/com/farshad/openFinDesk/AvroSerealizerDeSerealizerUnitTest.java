package com.farshad.openFinDesk;

import com.farshad.openFinDesk.infrastructure.avroserializerdeserializer.AvroDeSerealizer;
import com.farshad.openFinDesk.infrastructure.avroserializerdeserializer.AvroSerealizer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AvroSerealizerDeSerealizerUnitTest {

    AvroSerealizer serealizer;
    AvroDeSerealizer deSerealizer;
    model.AvroHttpRequest request;

    @Before
    public void setUp() throws Exception {
        serealizer = new AvroSerealizer();
        deSerealizer = new AvroDeSerealizer();

        model.ClientIdentifier clientIdentifier = model.ClientIdentifier.newBuilder()
            .setHostName("localhost")
            .setIpAddress("255.255.255.0")
            .build();

        List<CharSequence> employees = new ArrayList();
        employees.add("James");
        employees.add("Alice");
        employees.add("David");
        employees.add("Han");

        request = model.AvroHttpRequest.newBuilder()
            .setRequestTime(01l)
            .setActive(model.Active.YES)
            .setClientIdentifier(clientIdentifier)
            .setEmployeeNames(employees).setEventType("com.farshad.openFinDesk.domain.internalEvents.FutureIssuedEvent")
            .build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void WhenSerializedUsingJSONEncoder_thenObjectGetsSerialized() {
        byte[] data = serealizer.serealizeJSON(request);
        assertTrue(Objects.nonNull(data));
        assertTrue(data.length > 0);
    }

    @Test
    public void WhenSerializedUsingBinaryEncoder_thenObjectGetsSerialized() {
        byte[] data = serealizer.serealizeBinary(request);
        assertTrue(Objects.nonNull(data));
        assertTrue(data.length > 0);
    }
/*
    @Test
    public void WhenDeserializeUsingJSONDecoder_thenActualAndExpectedObjectsAreEqual() {
        byte[] data = serealizer.serealizeAvroHttpRequestJSON(request);
        model.AvroHttpRequest actualRequest = deSerealizer.deSerealizeAvroHttpRequestJSON(data);
        assertEquals(actualRequest, request);
        assertTrue(actualRequest.getRequestTime()
            .equals(request.getRequestTime()));
    }

    @Test
    public void WhenDeserializeUsingBinaryecoder_thenActualAndExpectedObjectsAreEqual() {
        byte[] data = serealizer.serealizeAvroHttpRequestBinary(request);
        model.AvroHttpRequest actualRequest = deSerealizer.deSerealizeAvroHttpRequestBinary(data);
        assertEquals(actualRequest, request);
        assertTrue(actualRequest.getRequestTime()
            .equals(request.getRequestTime()));
    }
  */
}

