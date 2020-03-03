package dao;

import com.mitsko.mrdb.dao.DAOException;
import com.mitsko.mrdb.dao.ReviewDAO;
import com.mitsko.mrdb.dao.impl.SQLReviewDAOImpl;
import com.mitsko.mrdb.entity.Review;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

class SQLReviewDAOImplTest {

    private final ReviewDAO reviewDAO = new SQLReviewDAOImpl();

    private Review testReview1 = new Review(11, 13, 1, "My favorite character is Sven");
    private Review testReview2 = new Review(14, 14, 1, "123");

    @Test
    void takeAllMoviesReviews() {
        try {
            ArrayList<Review> expected = new ArrayList<>();
            expected.add(testReview1);
            expected.add(testReview2);

            ArrayList<Review> actual = reviewDAO.takeAllMoviesReviews(1);

            assertEquals(expected, actual);
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeAllUsersReviews() {
        try {
            ArrayList<Review> expected = new ArrayList<>();
            expected.add(testReview2);

            ArrayList<Review> actual = reviewDAO.takeAllUsersReviews(14);

            assertEquals(expected, actual);
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeByID() {
        try {
            int expected = testReview1.getUserID();
            int actual = reviewDAO.takeUsersID(11);

            assertEquals(expected, actual);
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }
}