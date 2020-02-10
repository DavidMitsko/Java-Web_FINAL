package dao;

import com.mitsko.mrdb.dao.DAOException;
import com.mitsko.mrdb.dao.UserDAO;
import com.mitsko.mrdb.dao.impl.SQLUserDAOImpl;
import com.mitsko.mrdb.dao.pool.ConnectionPool;
import com.mitsko.mrdb.entity.User;
import com.mitsko.mrdb.entity.util.Role;
import com.mitsko.mrdb.entity.util.Status;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

class SQLUserDAOImplTest {
   // private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final UserDAO userDAO = new SQLUserDAOImpl();

    private User testUser = new User(14, "vlad", "$2a$10$1khs7RvAGoKuQ./ervFhEekkL076CK7vslzNCeLQe2hepvN3san82",
            Role.USER.toString(), Status.NO_LIMITS.toString(), 0);

    @BeforeClass
    public static void init() {
        //connectionPool.initPoolData();
    }

    @Test
    void takePassword() {
        try {
            String actual = userDAO.takePassword("vlad");
            String expected = testUser.getPassword();
            assertEquals(expected, actual);

        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeUserByLoginAndPassword() {
        try {
            User actual = userDAO.takeUserByLoginAndPassword("vlad", "$2a$10$1khs7RvAGoKuQ./ervFhEekkL076CK7vslzNCeLQe2hepvN3san82");
            User expected = testUser;
            assertEquals(expected, actual);

        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeRating() {
        try {
            int actual = userDAO.takeRating(14);
            int expected = testUser.getAverageRating();
            assertEquals(expected, actual);

        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeStatus() {
        try {
            Status actual = userDAO.takeStatus(14);
            Status expected = testUser.getStatus();
            assertEquals(expected, actual);

        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void findLogin() {
        try {
            boolean actual = userDAO.findLogin("vlad");
            boolean expected = true;
            assertEquals(expected, actual);

        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeID() {
        try {
            int actual = userDAO.takeID("vlad");
            int expected = testUser.getID();
            assertEquals(expected, actual);

        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void takeLogin() {
        try {
            String actual = userDAO.takeLogin(14);
            String expected = testUser.getLogin();
            assertEquals(expected, actual);
        } catch (DAOException ex) {
            ex.printStackTrace();
        }
    }

    @AfterClass
    public static void dispose() {
       // connectionPool.dispose();
    }
}