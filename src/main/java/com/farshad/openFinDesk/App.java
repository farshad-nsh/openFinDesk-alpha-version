package com.farshad.openFinDesk;

import com.farshad.openFinDesk.domain.InstrumentAnnotatedAggregate;
import com.farshad.openFinDesk.domain.aggregates.AggregateRoot;
import com.farshad.openFinDesk.domain.aggregates.Instrument;
import com.farshad.openFinDesk.domain.commands.Command;
import com.farshad.openFinDesk.domain.commands.IssueFutureCommand;
import com.farshad.openFinDesk.domain.internalEvents.FutureIssuedEvent;
import com.farshad.openFinDesk.domain.repository.InstrumentRepository;
import com.farshad.openFinDesk.infrastructure.avroserializerdeserializer.AvroDeSerealizer;
import com.farshad.openFinDesk.infrastructure.avroserializerdeserializer.AvroSerealizer;
import com.farshad.openFinDesk.infrastructure.bus.EventBus;
import com.farshad.openFinDesk.infrastructure.commandCategory.CommandInvoker;
import com.farshad.openFinDesk.infrastructure.commandCategory.commandDispatcher.CommandDispatcher;
import com.farshad.openFinDesk.infrastructure.commandCategory.commandDispatcher.CommandDispatcherImpl;
import com.farshad.openFinDesk.infrastructure.eventCategory.eventDispatcher.EventDispatcher;
import com.farshad.openFinDesk.infrastructure.eventCategory.eventDispatcher.EventDispatcherImpl;
import com.farshad.openFinDesk.infrastructure.repository.AbstractRepository;
import com.farshad.openFinDesk.testpack.TestEntity;
import com.farshad.openFinDesk.testpack.TestIt;
import model.AvroHttpRequest;
import org.apache.avro.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


/**
 * @author farshad noravesh
 *
 *
 */



@SpringBootApplication
public class App {

    @Autowired
    ApplicationContext applicationContext;


    Logger LOGGER= LoggerFactory.getLogger(App.class);

    @Autowired
    AvroSerealizer serealizer;

    @Autowired
    AvroDeSerealizer deSerealizer;

    @Autowired
    EventBus eventBus;


    @Autowired
    InstrumentRepository instrumentRepository;

    @Autowired
    TestIt testIt;


    /*
    @Autowired
    MessageListenerFactory messageListenerFactory;
*/

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            LOGGER.info("starting openFinDesk...");
            model.AvroHttpRequest request;


            model.ClientIdentifier clientIdentifier = model.ClientIdentifier.newBuilder()
                    .setHostName("23")
                    .setIpAddress("255.255.255.0")
                    .build();

            List<CharSequence> employees = new ArrayList();
            employees.add("farshad");
            employees.add("hossein");
            employees.add("ali");
            employees.add("hamid");

            request = model.AvroHttpRequest.newBuilder()
                    .setEventType("com.farshad.openFinDesk.domain.internalEvents.FutureIssuedEvent")
                    .setRequestTime(01l)
                    .setActive(model.Active.YES)
                    .setClientIdentifier(clientIdentifier)
                    .setEmployeeNames(employees)
                    .build();

            byte[] data = serealizer.serealizeBinary(request);
            //LOGGER.info("serialized data="+data);

            deSerealizer.setAnAvro(AvroHttpRequest.class);
            Schema schema=deSerealizer.deSerealizeBinary(data).getSchema();
            //LOGGER.info("schema="+schema.getFullName());

            model.AvroHttpRequest m=  deSerealizer.deSerealizeBinary(data);
           // LOGGER.info("deserialized data="+m.getEmployeeNames().get(2));



            FutureIssuedEvent futureIssuedEvent=new FutureIssuedEvent();
            EventDispatcher eventDispatcher=applicationContext.getBean(EventDispatcherImpl.class);
            futureIssuedEvent.setInstrumentId(String.valueOf("2"));
            futureIssuedEvent.setAmount(String.valueOf("4"));
            //eventDispatcher.dispatchEvent("com.farshad.openFinDesk.domain.internalEvents.FutureIssuedEvent", futureIssuedEvent);
/*
            IssueFutureCommand issueFutureCommand=new IssueFutureCommand();
            issueFutureCommand.setAmount("8");
            issueFutureCommand.setInstrumentId("2");
            CommandDispatcher commandDispatcher=applicationContext.getBean(CommandDispatcherImpl.class);
            //commandDispatcher.dispatchCommand("com.farshad.openFinDesk.domain.commands.IssueFutureCommand",issueFutureCommand);

            //eventBus.dispatchInternalEventFromReceivedBytes(data);


            Instrument instrument=new Instrument();
            instrument.setVersion(4L);
            instrument.setMicroserviceName("investmentMicroservice");
            instrument.setAggregateType("investment");
            instrument.setDescription("some disc");
            instrumentRepository.doSave(instrument);

            Instrument instrument1=new Instrument();
            instrument1=instrumentRepository.doLoad(new Instrument(),1L,4L);
            LOGGER.info("instrument1.getId()="+instrument1.getId());
            LOGGER.info("instrument1.getMicroserviceName()="+instrument1.getMicroserviceName());
            LOGGER.info("instrument1.getDescription()="+instrument1.getDescription());

            // given

            Instrument instrument=new Instrument();
            //instrument.setId(3L);
            //instrument.setDescription("special desc!");
            //instrument.setMicroserviceName("micro1");




            // when
            InstrumentAnnotatedAggregate instrumentAnnotatedAggregate=applicationContext.getBean(InstrumentAnnotatedAggregate.class,instrumentRepository);
            // instrumentAnnotatedAggregate.getAnnotatedAggregate().setAggregateRoot(instrument);
             issueFutureCommand=new IssueFutureCommand();
            issueFutureCommand.setAmount("118");
            issueFutureCommand.setInstrumentId("5");
            instrumentAnnotatedAggregate.getAnnotatedAggregate().setAggregateRoot(instrument);
            instrumentAnnotatedAggregate.setCommand("com.farshad.openFinDesk.domain.commands.IssueFutureCommand",issueFutureCommand);
            Instrument resultInstrument=instrumentAnnotatedAggregate.call();
            LOGGER.info(resultInstrument.getDescription());
            LOGGER.info(String.valueOf(resultInstrument.getId()));
*/
            IssueFutureCommand issueFutureCommand=applicationContext.getBean(IssueFutureCommand.class);
            issueFutureCommand.setInstrumentId("888");
            issueFutureCommand.setAmount("222");
            CommandInvoker commandInvoker=applicationContext.getBean(CommandInvoker.class,issueFutureCommand);
            //commandInvoker.setCommand(issueFutureCommand);
            commandInvoker.invoke();



        };
    }

}