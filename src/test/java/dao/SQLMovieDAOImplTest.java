package dao;

import com.mitsko.mrdb.dao.DAOException;
import com.mitsko.mrdb.dao.impl.SQLMovieDAOImpl;
import com.mitsko.mrdb.entity.Movie;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.service.ServiceException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class SQLMovieDAOImplTest {
    private final static SQLMovieDAOImpl movieDAO = new SQLMovieDAOImpl();

    private final Movie testMovie = new Movie(7, "Iron Man 2", 0, 0,
            "Iron Man 2.jpg", "Iron Man 2 is a 2010 American superhero film " +
            "based on the Marvel Comics character Iron Man, produced by " +
            "Marvel Studios and distributed by Paramount Pictures.");

    @Test
    void takeCountOfRating() {
        try {
            int actual = movieDAO.takeCountOfRating(7);
            int expected = testMovie.getCountOfRatings();
            assertEquals(expected, actual);

        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeRatingOfMovie() {
        try {
            float actual = movieDAO.takeRatingOfMovie(7);
            float expected = testMovie.getAverageRating();
            assertEquals(expected, actual, 0);
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeID() {
        try {
            int actual = movieDAO.takeID("Iron Man 2");
            int expected = testMovie.getID();
            assertEquals(expected, actual);

        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeDescription() {
        try {
            String actual = movieDAO.takeDescription(7);
            String expected = testMovie.getDescription();
            assertEquals(expected, actual);

        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeName() {
        try {
            String actual = movieDAO.takeName(7);
            String expected = testMovie.getName();
            assertEquals(expected, actual);

        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }
}