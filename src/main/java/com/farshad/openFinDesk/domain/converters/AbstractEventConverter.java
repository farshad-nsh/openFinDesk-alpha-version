package com.farshad.openFinDesk.domain.converters;

import com.farshad.openFinDesk.domain.internalEvents.InternalEvent;
import model.AvroHttpRequest;
import org.springframework.core.convert.converter.Converter;

//ET= eventType
public abstract class AbstractEventConverter<ET extends InternalEvent> implements Converter<model.AvroHttpRequest, ET> {


    public abstract  ET convertIt(AvroHttpRequest source);

    @Override
    public ET convert(AvroHttpRequest source) {
        return convertIt(source);
    }

}
