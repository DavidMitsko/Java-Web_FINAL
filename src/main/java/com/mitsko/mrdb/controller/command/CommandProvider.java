package com.mitsko.mrdb.controller.command;

import com.mitsko.mrdb.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.REGISTRATION, new Registration());

        repository.put(CommandName.TAKE_MOVIES, new TakeMovies());
        repository.put(CommandName.EDITING_MOVIES, new TakeMovies());

        repository.put(CommandName.TAKE_REVIEWS, new TakeReviews());
        repository.put(CommandName.ADD_REVIEW , new AddReview());
        repository.put(CommandName.RATING, new Rating());
        repository.put(CommandName.ADD_RATING, new AddRating());

        repository.put(CommandName.ADD_MOVIE, new AddMovie(true));
        repository.put(CommandName.CHANGE_MOVIE, new AddMovie(false));

        repository.put(CommandName.TAKE_USERS, new TakeUsers());
        repository.put(CommandName.CHANGE_STATUS, new ChangeStatus());
        repository.put(CommandName.REMOVE_MOVIE, new RemoveMovie());
        repository.put(CommandName.SIGN_OUT, new SignOut());
        repository.put(CommandName.USER_HISTORY, new TakeHistory());
        repository.put(CommandName.REMOVE_REVIEW, new RemoveReview());
        repository.put(CommandName.LOCALE, new ChangeLocale());
        repository.put(CommandName.ADD_MOVIE_PAGE, new AddMoviePage());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
        repository.put(CommandName.GET_IMAGE, new GetImage());
        repository.put(CommandName.CHANGE_MOVIE_PAGE, new ChangeMoviePage());
    }

    public Command getCommand(String name) {
        Command command;
        try {
            CommandName commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException ex) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
