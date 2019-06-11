

package com.farshad.openFinDesk.infrastructure.eventCategory.eventDispatcher;


import com.farshad.openFinDesk.domain.internalEvents.InternalEvent;
import com.farshad.openFinDesk.infrastructure.eventCategory.eventListeners.EventListenerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

@Component
public class EventDispatcherImpl implements EventDispatcher {


    private Logger LOGGER= LoggerFactory.getLogger(EventDispatcherImpl.class);
    private final EventListenerFactory eventListenerFactory;

    public EventListenerFactory getListenerFactory() {
        return eventListenerFactory;
    }

    @Autowired
    public EventDispatcherImpl(EventListenerFactory listenerFactory) {
        this.eventListenerFactory = listenerFactory;
    }

    private boolean callMessageListener(String event, InternalEvent ie) throws NoSuchFieldException {

        ArrayList<Method> listOfListeners = eventListenerFactory.getListeners(event);
        Class clazz=eventListenerFactory.getClassThatContainsListener(event);

        LOGGER.info("listOfListeners.size()="+listOfListeners.size());
        LOGGER.info("from class-->"+clazz);

        Method firstMethod=listOfListeners.get(0);
        LOGGER.info("firstMethod.getName()="+firstMethod.getName());
        LOGGER.info("internalEvent="+ie);
        try {
            Object object=clazz.getConstructor().newInstance(new Object[] { });
                firstMethod.invoke(object, ie);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return true;
    }


    @Override
    public void dispatchEvent(String event, InternalEvent ie) {
        try {
            callMessageListener(event,ie);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }



}


/*
public ArrayList<Method> getEventListeners(String event, InternalEvent ie) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        ArrayList<Method> listOfListeners = eventListenerFactory.getListeners(event);
        LOGGER.info("listOfListeners.size()="+listOfListeners.size());
        Method firstMethod=listOfListeners.get(0);
        LOGGER.info("firstMethod.getName()="+firstMethod.getName());
        LOGGER.info("internalEvent="+ie);
        return  listOfListeners;
    }

    public Class getClassThatContainsEventListener(String event,InternalEvent ie){
        Class clazz=eventListenerFactory.getClassThatContainsListener(event);
        LOGGER.info("from class-->"+clazz);
        return clazz;
    }

    public void invokeOneEventMethodFromClass(Class clazz,Method method,InternalEvent ie) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object object=clazz.getConstructor().newInstance(new Object[] { });
        method.invoke(object, ie);

    }
 */