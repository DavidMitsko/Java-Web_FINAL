package dao;

import com.mitsko.mrdb.dao.DAOException;
import com.mitsko.mrdb.dao.RatingDAO;
import com.mitsko.mrdb.dao.impl.SQLRatingDAOImpl;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class SQLRatingDAOImplTest {

    private final RatingDAO ratingDAO = new SQLRatingDAOImpl();

    @Test
    void takeAverageRatingOfMovie() {
        try {
            float actual = ratingDAO.takeAverageRatingOfMovie(1);
            float expected = (float) 8.16667;
            assertEquals(expected, actual, 0.001);
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeUsersRatingOfMovie() {
        try {
            float actual = ratingDAO.takeUsersRatingOfMovie(13, 1);
            float expected = (float) 8;
            assertEquals(expected, actual, 0.001);
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }
}