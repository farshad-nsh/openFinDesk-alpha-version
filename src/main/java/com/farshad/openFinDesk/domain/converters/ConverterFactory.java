package com.farshad.openFinDesk.domain.converters;

import org.springframework.stereotype.Component;

@Component
public class ConverterFactory {
    public AbstractEventConverter getConverter(String eventType) {
        if ("com.farshad.openFinDesk.domain.internalEvents.FutureIssuedEvent".equals(eventType)) {
            return new AvroHttpRequestToFutureIssuedEventConverter();
        } else if ("com.farshad.openFinDesk.domain.internalEvents.InstrumentIsAllowedEvent".equals(eventType)){
            return  new AvroHttpRequestToInstrumentIsAllowedEventConverter();
        }else{
            return null;
        }
    }
}
