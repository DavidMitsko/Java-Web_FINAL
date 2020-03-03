package service;

import com.mitsko.mrdb.entity.Review;
import com.mitsko.mrdb.service.ReviewService;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

class ReviewServiceImplTest {

    private final ReviewService reviewService = new ReviewServiceImpl();

    private Review testReview1 = new Review(11, 13, 1, "My favorite character is Sven");
    private Review testReview2 = new Review(14, 14, 1, "123");

    @Test
    void takeAllMoviesReview() {
        try {
            ArrayList<Review> expected = new ArrayList<>();
            expected.add(testReview1);

            ArrayList<Review> actual = reviewService.takeAllMoviesReview("Frozen");

            assertEquals(expected, actual);
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeAllUsersReview() {
        try {
            ArrayList<Review> expected = new ArrayList<>();
            expected.add(testReview2);

            ArrayList<Review> actual = reviewService.takeAllUsersReview(14);

            assertEquals(expected, actual);
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
    }
}