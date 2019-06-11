package com.farshad.openFinDesk.infrastructure.commandCategory.commandDispatcher;

import com.farshad.openFinDesk.domain.commands.Command;
import com.farshad.openFinDesk.infrastructure.commandCategory.CommandListeners.CommandListenerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

@Component
public class CommandDispatcherImpl implements CommandDispatcher{

    private Logger LOGGER= LoggerFactory.getLogger(CommandDispatcherImpl.class);
    private final CommandListenerFactory commandListenerFactory;

    @Autowired
    public CommandDispatcherImpl(CommandListenerFactory listenerFactory) {
        this.commandListenerFactory = listenerFactory;
    }

    private boolean callMessageListener(String c, Command command) throws NoSuchFieldException {

        ArrayList<Method> listOfListeners = commandListenerFactory.getListeners(c);
        Class clazz=commandListenerFactory.getClassThatContainsListener(c);

        LOGGER.info("listOfListeners.size()="+listOfListeners.size());
        LOGGER.info("from class->"+clazz);

        Method firstMethod=listOfListeners.get(0);
        LOGGER.info("firstMethod.getName()= "+firstMethod.getName());

        try {
            Object object=clazz.getConstructor().newInstance(new Object[] {});
            firstMethod.invoke(object, command);
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
    public void dispatchCommand(String c, Command command) {
        try {
            callMessageListener(c,command);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


}
