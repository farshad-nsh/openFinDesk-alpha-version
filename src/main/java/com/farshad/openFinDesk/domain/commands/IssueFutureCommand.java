package com.farshad.openFinDesk.domain.commands;

import com.farshad.openFinDesk.domain.InstrumentAnnotatedAggregate;
import com.farshad.openFinDesk.domain.aggregates.Instrument;
import com.farshad.openFinDesk.domain.repository.InstrumentRepository;
import com.farshad.openFinDesk.infrastructure.aggregateLifeCycle.AnnotatedAggregateThreadGroup;
import com.farshad.openFinDesk.infrastructure.bus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class IssueFutureCommand implements Command {

    Logger LOGGER= LoggerFactory.getLogger(IssueFutureCommand.class);

    @Autowired
    ApplicationContext applicationContext;

    InstrumentRepository instrumentRepository;

    @Autowired
    EventBus eventBus;

    private String instrumentId;

    private String amount;


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    @Override
    public void execute(Command command)  {
        Instrument instrument=new Instrument();
        instrument.setDescription("farshad");
        instrument.setId(55L);
        instrumentRepository=applicationContext.getBean(InstrumentRepository.class);
        InstrumentAnnotatedAggregate instrumentAnnotatedAggregate=applicationContext.getBean(InstrumentAnnotatedAggregate.class,instrumentRepository);
        instrumentAnnotatedAggregate.getAnnotatedAggregate().setAggregateRoot(instrument);
        LOGGER.info("ccccc="+command.toString());
        instrumentAnnotatedAggregate.setCommand("com.farshad.openFinDesk.domain.commands.IssueFutureCommand",command);
        Instrument resultInstrument= null;
        AnnotatedAggregateThreadGroup annotatedAggregateThreadGroup=new AnnotatedAggregateThreadGroup();
        annotatedAggregateThreadGroup.submitCallable(instrumentAnnotatedAggregate);
        resultInstrument=annotatedAggregateThreadGroup.getResult();
        LOGGER.info("resultInstrument.getId()="+resultInstrument.getId());
    }


}
