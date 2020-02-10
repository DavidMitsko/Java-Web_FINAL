package service;

import com.mitsko.mrdb.entity.Movie;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class MovieServiceImplTest {
    private final static MovieServiceImpl movieService = new MovieServiceImpl();

    private final Movie testMovie = new Movie(7, "Iron Man 2", 0, 0,
            "Iron Man 2.jpg", "Iron Man 2 is a 2010 American superhero film " +
            "based on the Marvel Comics character Iron Man, produced by " +
            "Marvel Studios and distributed by Paramount Pictures.");

    @Test
    void takeAverageRating() {
        try {
            float actual = movieService.takeAverageRating("Iron Man 2");
            float expected = testMovie.getAverageRating();
            assertEquals(expected, actual, 0);

        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeDescription() {
        try {
            String actual = movieService.takeDescription("Iron Man 2");
            String expected = testMovie.getDescription();
            assertEquals(expected, actual);

        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeNameByID() {
        try {
            String actual = movieService.takeNameByID(7);
            String expected = testMovie.getName();
            assertEquals(expected, actual);

        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
    }
}