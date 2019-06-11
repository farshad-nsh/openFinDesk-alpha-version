package com.farshad.openFinDesk.infrastructure.commandCategory.CommandListeners;
import com.farshad.openFinDesk.infrastructure.annotations.CommandHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;


@Scope(value = "singleton")
@Aspect
@Component
public class CommandListener {
    Logger LOGGER= LoggerFactory.getLogger(CommandListener.class);


    /**
     * listener is the method that should be executed.
     */
    private HashMap<String, ArrayList<Method>> commandToListenersMap;

    /**
     * this is the class that contains handler methods
     */
    private HashMap<String,Class> commandToClassMap;


    /**
     * we find parameters of a method which are our events
     */
    private List<Parameter> parameters;

    private ArrayList<Method> arrayList=new ArrayList<>();
    private Set<BeanDefinition> beans;
    private String targetPackage;

    public CommandListener() {
        this.commandToListenersMap = new HashMap<>();
        this.commandToClassMap=new HashMap<>();
    }


    @PostConstruct
    public void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        LOGGER.info("inside postConstruct");
        this.targetPackage = "com.farshad.openFinDesk.domain.aggregates";
        beans=findBeans();
        registerCommandListeners();
    }

    private Set<BeanDefinition> findBeans() throws ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
        Set<BeanDefinition> beanDefinitions=provider.findCandidateComponents(targetPackage);
        LOGGER.info("String.valueOf(beanDefinitions.size())"+beanDefinitions.size());
        beanDefinitions.stream().forEach(s-> System.out.println(s));
        return  beanDefinitions;
    }

    private HashMap<String, ArrayList<Method>> registerCommandListeners() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (BeanDefinition b:beans) {
            LOGGER.info(b.getBeanClassName());
            Class clazz=Class.forName(b.getBeanClassName());
            LOGGER.info("clazz="+clazz);
            Method[] methods=clazz.getMethods();

            for (Method m:methods
            ) {
                if (m.isAnnotationPresent(CommandHandler.class)) {
                    parameters= Arrays.asList(m.getParameters());
                    LOGGER.info("parameter.get(0).getType().getName()="+parameters.get(0).getType().getName());
                    LOGGER.info("m.getName()=" + m.getName());
                    CommandHandler commandListener = m.getAnnotation(CommandHandler.class);
                    LOGGER.info("commandHandler.version()="+commandListener.version());
                    arrayList.add(m);
                    commandToListenersMap.put(parameters.get(0).getType().getName(),arrayList);
                }
            }
            commandToClassMap.put(parameters.get(0).getType().getName(),clazz);
        }
        LOGGER.info("listener="+commandToListenersMap.get("com.farshad.openFinDesk.domain.commands.IssueFutureCommand").get(0));
        Method test=commandToListenersMap.get("com.farshad.openFinDesk.domain.commands.IssueFutureCommand").get(0);

        return commandToListenersMap;

    }

    public ArrayList<Method> getCommandListener(String commandName) {
        return commandToListenersMap.get(commandName);
    }

    public Class getClassThatContainsListener(String commandName) {
        return commandToClassMap.get(commandName);
    }


    @Around("@annotation(com.farshad.openFinDesk.infrastructure.annotations.CommandHandler)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }

}
