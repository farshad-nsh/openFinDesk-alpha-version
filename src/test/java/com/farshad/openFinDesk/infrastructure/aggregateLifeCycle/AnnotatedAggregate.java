package com.farshad.openFinDesk.infrastructure.aggregateLifeCycle;

import com.farshad.openFinDesk.domain.aggregates.AggregateRoot;
import com.farshad.openFinDesk.domain.commands.Command;
import com.farshad.openFinDesk.domain.internalEvents.FutureIssuedEvent;
import com.farshad.openFinDesk.domain.internalEvents.InternalEvent;
import com.farshad.openFinDesk.infrastructure.bus.EventBus;
import com.farshad.openFinDesk.infrastructure.commandCategory.CommandListeners.CommandListenerFactory;
import com.farshad.openFinDesk.infrastructure.eventCategory.eventListeners.EventListenerFactory;
import com.farshad.openFinDesk.infrastructure.repository.AbstractRepository;
import model.AvroHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class AnnotatedAggregate<A extends AggregateRoot> extends Scope {

    private A aggregateRoot;
    //private Runnable runnable;
    private AbstractRepository abstractRepository;
    private final EventBus eventBus;
    private boolean applying = false;
    private boolean isDeleted = false;
    private Long lastKnownSequence;
    private final Queue<Runnable> delayedTasks = new LinkedList<>();


    @Autowired
    private EventListenerFactory eventListenerFactory;

    @Autowired
    private CommandListenerFactory commandListenerFactory;

    Logger LOGGER= LoggerFactory.getLogger(AnnotatedAggregate.class);

    @Autowired
    public AnnotatedAggregate(EventBus eventBus, AbstractRepository abstractRepository) {
        this.eventBus = eventBus;
        this.abstractRepository=abstractRepository;
    }
/*
    protected void registerRoot(Callable<A> aggregateFactory) throws Exception {
        this.aggregateRoot = executeWithResult(aggregateFactory);
    }

    public  <A extends AggregateRoot> AnnotatedAggregate<A> initialize(Callable<A> aggregateFactory, EventBus eventBus,AbstractRepository abstractRepository) {
        AnnotatedAggregate<A> aggregate =
                new AnnotatedAggregate<>(eventBus,abstractRepository);
        try {
            aggregate.registerRoot(aggregateFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aggregate;
    }
*/
    public AbstractRepository getAbstractRepository() {
        return abstractRepository;
    }

    public A getAggregateRoot() {
        return aggregateRoot;
    }

    public void setAggregateRoot(A aggregateRoot) {
        this.aggregateRoot = aggregateRoot;
        LOGGER.info("MicroserviceName="+aggregateRoot.getMicroserviceName());
    }


    private InternalEvent consumeFromBus() {
        byte[] bytes=null;
       // AvroHttpRequest avroHttpRequest=eventBus.convertByteToAvro(bytes);
       // LOGGER.info("received event="+avroHttpRequest.getEventType().toString());
       // InternalEvent internalEvent=eventBus.convertAvroToInternalEvent(avroHttpRequest);
       // return internalEvent;
        //eventBus.dispatchInternalEventFromReceivedBytes(bytes);
       // runnable.run();
        return null;
    }

    /**
     *
     * @param bytes bytes on bus ready to be listened.
     */
    protected void listenFromEventBus(byte[] bytes) {

        if (eventBus != null) {
            eventBus.dispatchInternalEventFromReceivedBytes(bytes);
        }
    }


    public void dispatch(String command, Command c){
        doApply(command,c);
    }
    //***********Overrides**************
    //**********************************

  //  @Override
    protected  AggregateRoot doCreateNew(AggregateRoot aggregateRoot) throws Exception {
       this.aggregateRoot= (A) abstractRepository.doLoad(aggregateRoot,1L,5L);
        return aggregateRoot;
    }

  //  @Override
    public void doApply(String command,Command c) {

        //step1:create the aggregate or read from Deque<Scope>
        /*
        try {
            doCreateNew(aggregateRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        //step2:consumeFrombus
        //consumeFromBus();

        //step2: apply events to aggregate
        applyEventsToAggregate();
        startScope(aggregateRoot);
        //aggregateRoot=getCurrentScope();
        //LOGGER.info("aggregateRoot.getId() from deque="+aggregateRoot.getId());

        //step4: apply command to aggregate
        applyCommandsToAggregate(command,c);

        //step5: update the aggregate in repository for CDC to kafka
        updateTheAggregateRoot();

        //step6:push to the Deque<Scope> for further enrichment
        //getCurrentScope().push(aggregateRoot);
        //getInstance().getDeque().push(this);
        startScope(aggregateRoot);
        aggregateRoot=getCurrentScope();
        LOGGER.info("aggregateRoot.getId() from deque in test="+aggregateRoot.getId());
        //aggregateRoot=(AnnotatedAggregate)scope;
    }

    public void applyEventsToAggregate(){

        FutureIssuedEvent futureIssuedEvent=new FutureIssuedEvent();
        futureIssuedEvent.setInstrumentId("2");
        futureIssuedEvent.setAmount("4");

        ArrayList<Method> listOfListeners = eventListenerFactory.getListeners("com.farshad.openFinDesk.domain.internalEvents.FutureIssuedEvent");
        //Class clazz=eventListenerFactory.getClassThatContainsListener("com.farshad.openFinDesk.domain.internalEvents.FutureIssuedEvent");
        Method firstMethod=listOfListeners.get(0);
        try {
            firstMethod.invoke(aggregateRoot, futureIssuedEvent);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void applyCommandsToAggregate(String command, Command c) {
        ArrayList<Method> listOfListeners = commandListenerFactory.getListeners(command);
        Method firstMethod=listOfListeners.get(0);
        try {
            firstMethod.invoke(aggregateRoot, c);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void updateTheAggregateRoot(){
        //aggregateRoot.setId(1L);
        abstractRepository.doMerge(aggregateRoot);
    }


    //@Override
    protected boolean getIsLive() {
        return false;
    }

    //@Override
    protected Long version() {
        return null;
    }

    //@Override
    protected void doMarkDeleted() {
        isDeleted=true;
    }



   // @Override
    protected String type() {
        return null;
    }

   // @Override
    protected Object identifier() {
        return null;
    }
}
