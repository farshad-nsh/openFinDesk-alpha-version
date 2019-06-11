package com.farshad.openFinDesk.domain.commands;

public interface Command {
    void execute(Command command);
}
