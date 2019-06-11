package com.farshad.openFinDesk.application.CommandController;/*
package com.farshad.openFinDesk.application.CommandController;

import com.farshad.openFinDesk.domain.InstrumentAnnotatedAggregate;
import com.farshad.openFinDesk.domain.aggregates.Instrument;
import com.farshad.openFinDesk.domain.commands.IssueFutureCommand;
import com.farshad.openFinDesk.domain.repository.InstrumentRepository;
import com.farshad.openFinDesk.infrastructure.aggregateLifeCycle.AnnotatedAggregate;
import com.farshad.openFinDesk.infrastructure.bus.EventBus;
import com.farshad.openFinDesk.infrastructure.repository.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvestmentCommandController {


    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    EventBus eventBus;

    AbstractRepository abstractRepository;

    private InstrumentAnnotatedAggregate instrumentAnnotatedAggregate;

    @Autowired
    public InvestmentCommandController(InstrumentRepository instrumentRepository) {
        this.abstractRepository=instrumentRepository;
    }

    @RequestMapping(value = "/persistInstrument", method = RequestMethod.POST)
    public ResponseEntity<String> persistPerson(@RequestBody IssueFutureCommand issueFutureCommand) throws Exception {
        if (issueFutureCommand.getInstrumentId()!=null) {
            instrumentAnnotatedAggregate=applicationContext.getBean(InstrumentAnnotatedAggregate.class,abstractRepository);
           // instrumentAnnotatedAggregate.getAnnotatedAggregate().setAggregateRoot(new Instrument());
            //instrumentAnnotatedAggregate.getAnnotatedAggregate().doApply("com.farshad.openFinDesk.domain.commands.IssueFutureCommand", issueFutureCommand);
            //instrumentAnnotatedAggregate.call();
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}
*/
