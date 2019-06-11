package com.farshad.openFinDesk.infrastructure.commandCategory.commandDispatcher;

import com.farshad.openFinDesk.domain.commands.Command;

public interface CommandDispatcher {
    void dispatchCommand(String c, Command command);
}
