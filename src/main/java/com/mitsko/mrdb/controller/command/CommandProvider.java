package com.mitsko.mrdb.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.SIGN_IN, new SignIn());
    }

    public Command getCommand(String name) {
        CommandName commandName = CommandName.valueOf(name);
        Command command = repository.get(commandName);
        return command;
    }
}
