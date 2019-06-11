package com.farshad.openFinDesk.infrastructure.commandCategory;

import com.farshad.openFinDesk.domain.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CommandInvoker {

    Logger LOGGER= LoggerFactory.getLogger(CommandInvoker.class);


    private Command command;

    @Autowired
    public CommandInvoker(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void invoke() {
        command.execute(this.getCommand());
    }
}
