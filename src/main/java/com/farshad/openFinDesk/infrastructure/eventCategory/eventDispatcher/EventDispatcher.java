package com.farshad.openFinDesk.infrastructure.eventCategory.eventDispatcher;

//import com.google.protobuf.Message;
import com.farshad.openFinDesk.domain.internalEvents.InternalEvent;


public interface EventDispatcher {
    void dispatchEvent(String event, InternalEvent internalEvent);
}

