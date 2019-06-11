package com.farshad.openFinDesk.domain.aggregates;

import com.farshad.openFinDesk.domain.commands.IssueFutureCommand;
import com.farshad.openFinDesk.domain.internalEvents.FutureIssuedEvent;
import com.farshad.openFinDesk.infrastructure.annotations.CommandHandler;
import com.farshad.openFinDesk.infrastructure.annotations.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.*;


/**
 * aggregate Root
 * Instrument is a concept in financial systems. one category of instruments is Derivative.
 */
 /*
@Bean
public Repository<MyAggregate> repositoryForMyAggregate(EventStore eventStore) {
    return EventSourcingRepository.builder(GiftCard.class).eventStore(eventStore).build();
}
@AggregateRoot(commandRepository = "repositoryForMyAggregate",queryRepository="myElasticRepo")
*/
@Component
@Entity
@Table(name="ins")
@DiscriminatorValue("Ins")
public class Instrument extends AggregateRoot {

    @Transient
    Logger LOGGER= LoggerFactory.getLogger(Instrument.class);


    @Column
    private String description;


    @CommandHandler
    public void checkIfInstrumentIsAllowed(IssueFutureCommand issueFutureCommand){
        id= Long.valueOf(issueFutureCommand.getInstrumentId());
        LOGGER.info("id of instrument="+id);
        LOGGER.info("issueFutureCommand.getAmount()="+issueFutureCommand.getAmount());
        LOGGER.info("issueFutureCommand.getInstrumentId()="+issueFutureCommand.getInstrumentId());
        LOGGER.info("allowed");
        description=issueFutureCommand.getAmount();
        LOGGER.info("description="+description);
        //AggregateLifecycle.apply(new InstrumentIsAllowedEvent());
    }

    @EventHandler(version = "4")
    public void on(FutureIssuedEvent evt) {
        id=Long.valueOf(evt.getInstrumentId());
        description=evt.getAmount();
        LOGGER.info("id="+id);
        LOGGER.info("description="+description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Instrument() {
    }
}

