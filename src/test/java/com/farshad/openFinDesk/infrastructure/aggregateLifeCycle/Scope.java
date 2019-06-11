package com.farshad.openFinDesk.infrastructure.aggregateLifeCycle;

import com.farshad.openFinDesk.domain.aggregates.AggregateRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public abstract class Scope {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scope.class);

    private  final ThreadLocal<Deque<AggregateRoot>> CURRENT_SCOPE = ThreadLocal.withInitial(LinkedList::new);

    public  <A extends AggregateRoot> A getCurrentScope() throws IllegalStateException {
        try {
            return (A) CURRENT_SCOPE.get().getFirst();
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("Cannot request current Scope if none is active");
        }
    }


/*
    protected void endScope() {
        Deque<AggregateRoot> scopes = CURRENT_SCOPE.get();
        if (scopes != scopes.peek()) {
            throw new IllegalStateException(
                    "Incorrectly trying to end another Scope then which the calling process is contained in."
            );
        }
        scopes.pop();

        if (scopes.isEmpty()) {
            LOGGER.debug("Clearing out ThreadLocal current Scope, as no Scopes are present");
            CURRENT_SCOPE.remove();
        }
    }
*/
    protected void startScope(AggregateRoot aggregateRoot) {
        CURRENT_SCOPE.get().add(aggregateRoot);
        LOGGER.info("size of deque in test="+CURRENT_SCOPE.get().size());
    }
/*
    protected <V> V executeWithResult(Callable<V> task) throws Exception {
       //startScope();
       try {
            return task.call();
        } finally {
            endScope();
        }
    }
*/
    protected Deque<AggregateRoot> getDeque(){
        return CURRENT_SCOPE.get();
    }


}
