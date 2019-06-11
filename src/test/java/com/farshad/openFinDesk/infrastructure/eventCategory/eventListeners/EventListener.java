
package com.farshad.openFinDesk.infrastructure.eventCategory.eventListeners;


//TODO using hashmap to map events to methodNames after another map from avro to events

import com.farshad.openFinDesk.infrastructure.annotations.EventHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

@Scope(value = "singleton")
@Aspect
@Component
public class EventListener {



    Logger LOGGER= LoggerFactory.getLogger(EventListener.class);


    /**
     * listener is the method that should be executed.
     */
    private HashMap<String, ArrayList<Method>> eventToListenersMap;

    /**
     * this is the class that contains handler methods
     */
    private HashMap<String,Class> eventToClassMap;


    /**
     * we find parameters of a method which are our events
     */
    private List<Parameter> parameters;

    private ArrayList<Method> arrayList=new ArrayList<>();
    private Set<BeanDefinition> beans;
    private String targetPackage;

    public EventListener() {
        this.eventToListenersMap = new HashMap<>();
        this.eventToClassMap=new HashMap<>();
    }


    @PostConstruct
    public void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        LOGGER.info("inside postConstruct");
        this.targetPackage = "com.farshad.openFinDesk.domain.aggregates";
        beans=findBeans();
        registerEventListeners();
    }

    private Set<BeanDefinition> findBeans() throws ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
        Set<BeanDefinition> beanDefinitions=provider.findCandidateComponents(targetPackage);
        LOGGER.info("String.valueOf(beanDefinitions.size())"+beanDefinitions.size());
        beanDefinitions.stream().forEach(s-> System.out.println(s));
        return  beanDefinitions;
    }

    private HashMap<String, ArrayList<Method>> registerEventListeners() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (BeanDefinition b:beans) {
            LOGGER.info(b.getBeanClassName());
            Class clazz=Class.forName(b.getBeanClassName());
            LOGGER.info("clazz="+clazz);
            Method[] methods=clazz.getMethods();

            for (Method m:methods
            ) {
                if (m.isAnnotationPresent(EventHandler.class)) {
                    parameters= Arrays.asList(m.getParameters());
                    LOGGER.info("parameter.get(0).getType().getName()="+parameters.get(0).getType().getName());
                    LOGGER.info("m.getName()=" + m.getName());
                    EventHandler eventSourcingHandler = m.getAnnotation(EventHandler.class);
                    LOGGER.info("eventSourcingHandler.version()="+eventSourcingHandler.version());
                    arrayList.add(m);
                    eventToListenersMap.put(parameters.get(0).getType().getName(),arrayList);
                }
            }
            eventToClassMap.put(parameters.get(0).getType().getName(),clazz);
        }
        //LOGGER.info("listener="+eventToListenersMap.get("com.farshad.openFinDesk.domain.internalEvents.FutureIssuedEvent").get(0));
        //Method test=eventToListenersMap.get("com.farshad.openFinDesk.domain.internalEvents.FutureIssuedEvent").get(0);

       /*
        try {
            test.invoke(new Instrument(),futureIssuedEvent);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        */
       return eventToListenersMap;

    }





    public ArrayList<Method> getMessageListener(String eventName) {
        return eventToListenersMap.get(eventName);
    }

    public Class getClassThatContainsListener(String eventName) {
        return eventToClassMap.get(eventName);
    }



    @Around("@annotation(com.farshad.openFinDesk.infrastructure.annotations.EventHandler)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }

}


