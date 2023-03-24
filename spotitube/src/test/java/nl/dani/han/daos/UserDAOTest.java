// package nl.dani.han.daos;

// import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.junit.jupiter.MockitoExtension;

// @ExtendWith(MockitoExtension.class)
// class UserDAOTest {
// private final String DUMMY_USER = "dummy_user";
// private final String DUMMY_PASS = "dummy_password";

// private UserDAO sut;

// @BeforeEach
// void setUp() {
// sut = new UserDAO();
// }

// @Test
// public void userExistsTrue() throws DataAccessException {
// if (sut.userExists(DUMMY_USER, DUMMY_PASS)) {
// assertTrue(false);
// }

// // boolean exists = sut.userExists(DUMMY_USER, DUMMY_PASS);

// // assertTrue(exists);
// }
// }