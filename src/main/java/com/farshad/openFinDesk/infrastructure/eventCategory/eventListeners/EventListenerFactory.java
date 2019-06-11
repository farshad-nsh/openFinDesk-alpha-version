
package com.farshad.openFinDesk.infrastructure.eventCategory.eventListeners;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;


@Component
public class EventListenerFactory {

    private Logger LOGGER= LoggerFactory.getLogger(EventListenerFactory.class);

    private final EventListener eventListener;


    @Autowired
    public EventListenerFactory(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public ArrayList<Method> getListeners(String event){
        ArrayList<Method> listeners=new ArrayList<>();
        listeners = eventListener.getMessageListener(event);
        return listeners;
    }

    public Class getClassThatContainsListener(String event){
        Class clazz=null;
        clazz=eventListener.getClassThatContainsListener(event);
        LOGGER.info("clazz in factory="+clazz);
        return clazz;
    }


}


