package com.farshad.openFinDesk.infrastructure.commandCategory.CommandListeners;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;

@Component
public class CommandListenerFactory {
    private Logger LOGGER= LoggerFactory.getLogger(CommandListenerFactory.class);

    private final CommandListener commandListener;


    @Autowired
    public CommandListenerFactory(CommandListener commandListener) {
        this.commandListener = commandListener;
    }

    public ArrayList<Method> getListeners(String command){
        ArrayList<Method> listeners=new ArrayList<>();
        listeners = commandListener.getCommandListener(command);
        return listeners;
    }

    public Class getClassThatContainsListener(String command){
        Class clazz=null;
        clazz=commandListener.getClassThatContainsListener(command);
        LOGGER.info("clazz in factory="+clazz);
        return clazz;
    }
}
