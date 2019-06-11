package com.farshad.openFinDesk.domain;

import com.farshad.openFinDesk.domain.aggregates.Instrument;
import com.farshad.openFinDesk.domain.commands.Command;
import com.farshad.openFinDesk.domain.repository.InstrumentRepository;
import com.farshad.openFinDesk.infrastructure.aggregateLifeCycle.AnnotatedAggregate;
import com.farshad.openFinDesk.infrastructure.bus.EventBus;
import com.farshad.openFinDesk.infrastructure.repository.AbstractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Component
public class InstrumentAnnotatedAggregate implements Callable<Instrument> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstrumentAnnotatedAggregate.class);


    @Autowired
    ApplicationContext applicationContext;

    private AnnotatedAggregate<Instrument> annotatedAggregate;

    @Autowired
    private AbstractRepository abstractRepository;

    @Autowired
    private EventBus eventbus;

    private Command command;




    @Autowired
    public void InitializeAnnotatedAggregate(InstrumentRepository instrumentRepository) {
        this.abstractRepository=instrumentRepository;
        this.annotatedAggregate=applicationContext.getBean(AnnotatedAggregate.class,eventbus,abstractRepository);
    }


    public AnnotatedAggregate<Instrument> getAnnotatedAggregate() {
        return annotatedAggregate;
    }

    public void setCommand(Command command) {
        LOGGER.info("command is set!!!");
        this.command = command;
    }

    @Override
    public Instrument call() throws Exception {
         this.annotatedAggregate.dispatch("com.farshad.openFinDesk.domain.commands.IssueFutureCommand",command);
        //this.annotatedAggregate.getAggregateRoot().setDescription("some new descriptions");
        return  annotatedAggregate.getAggregateRoot();
    }
}
