# Converters
converter is a concept in OpenFinDesk to convert
external events inside kafka to internal events. This is the place to 
implement all patterns of domain driven design such as shared kernel,...
so that context mapping between different bounded contexts takes place. 

## Avro
OpenFinDesk supports Avro at the moment for the following reasons:
* good consensus with confluent schema registry.
* It is used in debezium(CDC:change data capturing).
* provides backward and forward compatibility 
## Steps
There is a method inside the Bus class to do:
```java_holder_method_tree
        //step1: convert byte to avro using deserializers
        LOGGER.info("bytes="+bytes);
        deSerealizer.setAnAvro(model.AvroHttpRequest.class);
        model.AvroHttpRequest m= deSerealizer.deSerealizeBinary(bytes);
        LOGGER.info("deserialized data="+m.getEmployeeNames().get(2));
        LOGGER.info("m.getEventType().toString()="+m.getEventType().toString());

        //step2: convert avro to event
        AbstractEventConverter abstractEventConverter=converterFactory.getConverter(m.getEventType().toString());
        InternalEvent internalEvent=abstractEventConverter.convert(m);
        LOGGER.info("internalEvent.toString()="+internalEvent.toString());

        //step3: dispatch event
        messageDispatcher.dispatchEvent(m.getEventType().toString(), internalEvent);
```
## Implementation
You just need to extend  AbstractEventConverter. Forexample AvroHttpRequestToFutureIssuedEventConverter is a converter
that converts avro to internal events which are meaningful for the domain.

```java
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
```

