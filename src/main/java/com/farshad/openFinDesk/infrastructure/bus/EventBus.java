package com.farshad.openFinDesk.infrastructure.bus;

import com.farshad.openFinDesk.domain.converters.AbstractEventConverter;
import com.farshad.openFinDesk.domain.converters.ConverterFactory;
import com.farshad.openFinDesk.domain.internalEvents.InternalEvent;
import com.farshad.openFinDesk.infrastructure.avroserializerdeserializer.AvroDeSerealizer;
import com.farshad.openFinDesk.infrastructure.avroserializerdeserializer.AvroSerealizer;
import com.farshad.openFinDesk.infrastructure.eventCategory.eventDispatcher.EventDispatcher;
import model.AvroHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventBus {


    Logger LOGGER= LoggerFactory.getLogger(EventBus.class);

    @Autowired
    private EventDispatcher eventDispatcher;

    @Autowired
    AvroSerealizer serealizer;

    @Autowired
    AvroDeSerealizer deSerealizer;


    @Autowired
    ConverterFactory converterFactory;


    //TODO do kafka consumer receives avro bytes...
    public void dispatchInternalEventFromReceivedBytes(byte[] bytes){
        //TODO for each event we should find the type of converter
        //get avro from kafka and deserialize it:

        //step1
        AvroHttpRequest avroHttpRequest=convertByteToAvro(bytes);

        //step2
        InternalEvent internalEvent=convertAvroToInternalEvent(avroHttpRequest);

        //step3: dispatch event
        LOGGER.info("eventType="+avroHttpRequest.getEventType().toString());
        LOGGER.info("internalEvent="+internalEvent);
        eventDispatcher.dispatchEvent(avroHttpRequest.getEventType().toString(), internalEvent);

    }

    public AvroHttpRequest convertByteToAvro(byte[] bytes){
        //step1: convert byte to avro using deserializers
        LOGGER.info("bytes="+bytes);
        deSerealizer.setAnAvro(model.AvroHttpRequest.class);
        model.AvroHttpRequest avroHttpRequest= deSerealizer.deSerealizeBinary(bytes);
        //LOGGER.info("avroHttpRequest.getEmployeeNames().get(2)="+avroHttpRequest.getEmployeeNames().get(2));
        //LOGGER.info("avroHttpRequest.getClientIdentifier().getHostName().toString()="+avroHttpRequest.getClientIdentifier().getHostName().toString());
        //LOGGER.info("avroHttpRequest.getEventType().toString()"+avroHttpRequest.getEventType().toString());
        return avroHttpRequest;
    }

    public InternalEvent convertAvroToInternalEvent(AvroHttpRequest avroHttpRequest){
        //step2: convert avro to event
        AbstractEventConverter abstractEventConverter=converterFactory.getConverter(avroHttpRequest.getEventType().toString());
        //LOGGER.info("internalEvent.toString()="+internalEvent.toString());
        return abstractEventConverter.convert(avroHttpRequest);
    }


}
