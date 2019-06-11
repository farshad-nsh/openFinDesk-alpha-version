# Aggregate Lifecycle
Each BoundedContext(a microservice) is composed of one or more aggregates. Each aggregate is composed of one or more entities and valueObjects but
each aggregate has only one aggregate root.
Since OpenFinDesk uses command handlers and event handlers
on a method and an aggregateroot may have many event handlers, the Lifecycle of 
aggregate should be managed by the framework. In order to control the lifecycle I
 used ThreadLocal that has a scope for the context that each aggregate defined by its identifier should have.
 ## Scope 
 Each aggregate is defined by its aggregateIdentifier which makes each one unique.
 So different instances of aggregate are scheduled to be run on different threads
 and the scope of each of these threads are different. The Context which we
 implement by:
 ```java_holder_method_tree
 ThreadLocal<ScopedContext> scopedContextThreadLocal;
 ``` 
So we track which events for each aggregate are received and which are one the way.
## Aggregate is live now!
When all events for a specific aggregate are collected by the dataStructure that is
defined before, we jump in to launch the aggregate since aggregate is live now
and ready to be persisted and published to kafka.

## comparison with axon framework
* in openFindDesk you can name any method you like for your handlers while 
axon is restricted to "on" and "handle" .
* Axon framework does not collect events to build the aggregate in a flexible way.
* Axon framework does not let you do CDC of many legacy databases in the world!
* Axon framework allows only upcasting for schema migration.
* In using eventsourcing by combining a cluster of mongodb and a kafka cluster,
you are forced to apply to only single topic. This does not take advantage of partitions in kafka.


