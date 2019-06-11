package com.farshad.openFinDesk.domain.converters;

import com.farshad.openFinDesk.domain.internalEvents.FutureIssuedEvent;

import model.AvroHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class AvroHttpRequestToFutureIssuedEventConverter extends AbstractEventConverter<FutureIssuedEvent> {


    Logger LOGGER= LoggerFactory.getLogger(AvroHttpRequestToFutureIssuedEventConverter.class);

    @Override
    public FutureIssuedEvent convertIt(AvroHttpRequest source) {
        LOGGER.info("source.getSchema().getName()="+source.getSchema().getName());
        FutureIssuedEvent futureIssuedEvent=new FutureIssuedEvent();
        futureIssuedEvent.setInstrumentId(source.getClientIdentifier().getHostName().toString());
        futureIssuedEvent.setAmount( source.getClientIdentifier().getIpAddress().toString());
        return futureIssuedEvent;
    }
}
