/*
package com.farshad.openFinDesk.infrastructure.aggregateLifeCycle;


import com.farshad.openFinDesk.App;
import com.farshad.openFinDesk.domain.InstrumentAnnotatedAggregate;
import com.farshad.openFinDesk.domain.aggregates.Instrument;
import com.farshad.openFinDesk.domain.commands.Command;
import com.farshad.openFinDesk.domain.commands.IssueFutureCommand;
import com.farshad.openFinDesk.domain.repository.InstrumentRepository;
import com.farshad.openFinDesk.infrastructure.bus.EventBus;
import com.farshad.openFinDesk.infrastructure.repository.AbstractRepository;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebFlux;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static junit.framework.TestCase.assertEquals;

//@RunWith(SpringRunner.class)
//@RunWith(JUnitParamsRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
//@SpringBootTest(classes = App.class)
//@SpringBootTest(classes = ApplicationContext.class)
//@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
//@TestPropertySource(locations = "classpath:test.properties")
//@RunWith(JUnit4.class)
@RunWith( MockitoJUnitRunner.class)
public class TestAnnotatedAggregateUsingMockito {



    //InstrumentAnnotatedAggregate instrumentAnnotatedAggregate;


    //InstrumentRepository instrumentRepository;


//private MockMvc mvc;

*/
/*
    @Before
    public void setup() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup((WebApplicationContext) instrumentRepository).build();
    }
*//*

   // ApplicationContext applicationContext;
    private InstrumentAnnotatedAggregate instrumentAnnotatedAggregate;

    private InstrumentRepository instrumentRepository;

    private AnnotatedAggregate annotatedAggregate;

    private Instrument instrument;

    @Test
    public void testContext() throws Exception {
        //ApplicationContext applicationContext = Mockito.mock(ApplicationContext.class);
         instrumentRepository=Mockito.mock(InstrumentRepository.class);
         instrumentAnnotatedAggregate= Mockito.mock(InstrumentAnnotatedAggregate.class);
         instrumentAnnotatedAggregate.InstrumentAnnotatedAggregate(instrumentRepository);
          //  Mockito.when()
    // Mockito.when(applicationContext.getBean(Instrument.class).getDescription()).thenReturn("4");
       // MyOtherService myOtherService = new MyOtherService(myComponent);
        //instrumentRepository=applicationContext.getBean(InstrumentRepository.class);
       // instrumentAnnotatedAggregate=applicationContext.getBean(InstrumentAnnotatedAggregate.class,instrumentRepository);
        instrument=Mockito.mock(Instrument.class);
        //instrument.setId(3L);
        //instrument.setDescription("special desc!");
        //instrument.setMicroserviceName("micro1");
        System.out.println(instrumentRepository.getClass().getCanonicalName().toString());
        // when
         annotatedAggregate=Mockito.mock(AnnotatedAggregate.class);
         annotatedAggregate=instrumentAnnotatedAggregate.getAnnotatedAggregate();
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
    }
*/
/*
    @Test
    public void checkAnnotatedAggregate() throws Exception {
        // given
        instrumentAnnotatedAggregate=applicationContext.getBean(InstrumentAnnotatedAggregate.class,instrumentRepository);
        instrumentRepository=applicationContext.getBean(InstrumentRepository.class);

        Instrument instrument=new Instrument();
        //instrument.setId(3L);
        //instrument.setDescription("special desc!");
        //instrument.setMicroserviceName("micro1");

        // when
       // instrumentAnnotatedAggregate.getAnnotatedAggregate().setAggregateRoot(instrument);


        IssueFutureCommand issueFutureCommand=new IssueFutureCommand();
        issueFutureCommand.setAmount("118");
        issueFutureCommand.setInstrumentId("5");
        instrumentAnnotatedAggregate.getAnnotatedAggregate().setAggregateRoot(instrument);
        instrumentAnnotatedAggregate.setCommand(issueFutureCommand);
         Instrument resultInstrument=instrumentAnnotatedAggregate.call();
        // then
         assertEquals(resultInstrument.getDescription(),"118");
         assertEquals(resultInstrument.getId(),5L);


    }
 *//*

}
*/
