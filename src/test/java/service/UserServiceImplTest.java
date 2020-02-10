package service;

import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.entity.util.Status;
import com.mitsko.mrdb.service.ServiceException;
import com.mitsko.mrdb.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class UserServiceImplTest {
    private static final UserServiceImpl userService = new UserServiceImpl();

    private User testUser = new User(14, "vlad", "$2a$10$1khs7RvAGoKuQ./ervFhEekkL076CK7vslzNCeLQe2hepvN3san82",
            Role.USER.toString(), Status.NO_LIMITS.toString(), 0);

    @Test
    void signIn() {
        try {
            User actual = userService.signIn("vlad", "vlad");
            User expected = testUser;
            assertEquals(expected, actual);

        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeStatus() {
        try {
            Status actual = userService.takeStatus("vlad");
            Status expected = testUser.getStatus();
            assertEquals(expected, actual);

        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeLogin() {
        try {
            String actual = userService.takeLogin(14);
            String expected = testUser.getLogin();
            assertEquals(expected, actual);

        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeAverageRating() {
        try {
            int actual = userService.takeAverageRating("vlad");
            int expected = testUser.getAverageRating();
            assertEquals(expected, actual);

        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
    }
}