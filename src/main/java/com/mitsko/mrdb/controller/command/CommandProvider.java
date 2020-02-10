package com.mitsko.mrdb.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.REGISTRATION, new Registration());

        repository.put(CommandName.TAKE_MOVIES, new TakeMovies());
        repository.put(CommandName.TAKE_MOVIES_FOR_REMOVE, new TakeMovies());

        repository.put(CommandName.TAKE_REVIEWS, new TakeReviews());
        repository.put(CommandName.ADD_REVIEW , new AddReview());
        repository.put(CommandName.RATING, new Rating());
        repository.put(CommandName.ADD_RATING, new AddRating());
        repository.put(CommandName.ADD_MOVIE, new AddMovie());
        repository.put(CommandName.TAKE_USERS, new TakeUsers());
        repository.put(CommandName.CHANGE_STATUS, new ChangeStatus());
        repository.put(CommandName.REMOVE_MOVIE, new RemoveMovie());
        repository.put(CommandName.SIGN_OUT, new SignOut());
        repository.put(CommandName.USER_HISTORY, new TakeHistory());
        repository.put(CommandName.REMOVE_REVIEW, new RemoveReview());
        repository.put(CommandName.LOCALE, new ChangeLocale());
        repository.put(CommandName.ADD_MOVIE_PAGE, new AddMoviePage());
    }

    public Command getCommand(String name) {
        CommandName commandName = CommandName.valueOf(name.toUpperCase());
        Command command = repository.get(commandName);
        return command;
    }
}
