package com.farshad.openFinDesk;

import com.farshad.openFinDesk.domain.InstrumentAnnotatedAggregate;
import com.farshad.openFinDesk.domain.aggregates.Instrument;
import com.farshad.openFinDesk.domain.commands.IssueFutureCommand;
import com.farshad.openFinDesk.domain.repository.InstrumentRepository;
import com.farshad.openFinDesk.infrastructure.aggregateLifeCycle.AnnotatedAggregate;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


import static junit.framework.TestCase.assertEquals;

//@RunWith(SpringRunner.class)
//@RunWith(JUnitParamsRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = {InstrumentRepository.class,TestAnnotatedAggregateUsingSpring.class})
@SpringBootTest
//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:test.properties")
//@RunWith(JUnit4.class)
public class TestAnnotatedAggregateUsingSpring  {

    Logger LOGGER= LoggerFactory.getLogger(TestAnnotatedAggregateUsingSpring.class);

    @Autowired
    ApplicationContext applicationContext;


    @Autowired
    InstrumentRepository instrumentRepository;


    private InstrumentAnnotatedAggregate instrumentAnnotatedAggregate;


    private AnnotatedAggregate annotatedAggregate;

    @Test
    public void aComponent()
    {
        Assert.assertTrue(applicationContext.getBean(InstrumentRepository.class) != null);
    }
    @Test
    public void testContext() throws Exception {
        instrumentAnnotatedAggregate=applicationContext.getBean(InstrumentAnnotatedAggregate.class);

        //instrument.setId(3L);
        //instrument.setDescription("special desc!");
        //instrument.setMicroserviceName("micro1");
        System.out.println(instrumentRepository.getClass().getCanonicalName().toString());
        //when
       // annotatedAggregate=instrumentAnnotatedAggregate.getAnnotatedAggregate();
        Instrument instrument=new Instrument();
        instrument.setDescription("s");
        IssueFutureCommand issueFutureCommand=new IssueFutureCommand();
        issueFutureCommand.setAmount("118");
        issueFutureCommand.setInstrumentId("5");
        annotatedAggregate=instrumentAnnotatedAggregate.getAnnotatedAggregate();
        annotatedAggregate.setAggregateRoot(instrument);
        instrumentAnnotatedAggregate.setCommand(issueFutureCommand);
        Instrument resultInstrument=instrumentAnnotatedAggregate.call();
        // then
        assertEquals(resultInstrument.getDescription(),"118");
        assertEquals(resultInstrument.getId(),5L);
        LOGGER.info("resultInstrument.getId()="+resultInstrument.getId());
        LOGGER.info("resultInstrument.getDescription()="+resultInstrument.getDescription());


    }

}

