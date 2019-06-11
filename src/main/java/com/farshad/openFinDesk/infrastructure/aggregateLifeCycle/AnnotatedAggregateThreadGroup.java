package com.farshad.openFinDesk.infrastructure.aggregateLifeCycle;

import com.farshad.openFinDesk.domain.aggregates.Instrument;

import java.util.concurrent.*;

public class AnnotatedAggregateThreadGroup {

    static final ExecutorService service = Executors.newFixedThreadPool(4);

    static  Future <Instrument> instrumentFuture = null;

    Instrument instrument;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void submitCallable(Callable<Instrument> instrumentCallable){
        instrumentFuture=service.submit(instrumentCallable);
    }


    public Instrument getResult(){
        try {
             instrument= instrumentFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return instrument;
    }
}
