/*
package com.farshad.openFinDesk.infrastructure.aggregateLifeCycle;

import com.farshad.openFinDesk.domain.aggregates.AggregateRoot;
import com.farshad.openFinDesk.domain.commands.Command;

import java.util.concurrent.Callable;

public abstract class AggregateLifecycle extends Scope{

    protected  AggregateRoot getInstance() {
        return Scope.getCurrentScope();
    }

    public static void apply(String command, Command c) {
        // getInstance().doApply(command,c);
    }

    protected abstract void doApply(String command, Command c);


*/
/*
    public static AggregateRoot createNew(AggregateRoot aggregateRoot)
            throws Exception {
        return getInstance().doCreateNew(aggregateRoot);
    }
*//*

    protected abstract  AggregateRoot doCreateNew(AggregateRoot aggregateRoot) throws Exception;

*/
/*
    public static boolean isLive() {
        return AggregateRoot.getInstance().getIsLive();
    }
*//*

*/
/*
    public static Long getVersion() {
        return getInstance().version();
    }


    public static void markDeleted() {
        getInstance().doMarkDeleted();
    }

*//*

    protected abstract boolean getIsLive();


    protected abstract Long version();


    protected abstract void doMarkDeleted();



*/
/*
    protected void execute(Runnable task) {

        try {
            executeWithResult(() -> {
                task.run();
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
*//*

     protected abstract String type();

    protected abstract Object identifier();



}
*/
