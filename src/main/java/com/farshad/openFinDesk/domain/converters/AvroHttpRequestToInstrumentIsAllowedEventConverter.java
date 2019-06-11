package com.farshad.openFinDesk.domain.converters;

import com.farshad.openFinDesk.domain.internalEvents.InstrumentIsAllowedEvent;
import model.AvroHttpRequest;

public class AvroHttpRequestToInstrumentIsAllowedEventConverter extends AbstractEventConverter<InstrumentIsAllowedEvent> {

    @Override
    public InstrumentIsAllowedEvent convertIt(AvroHttpRequest source) {
        return null;
    }
}
