package es.comepiedras.retosprogramacion.service;

import es.comepiedras.retosprogramacion.config.DatabaseConnection;
import es.comepiedras.retosprogramacion.model.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserServiceTest {
    private static final Logger logger = Logger.getLogger(UserServiceTest.class.getName());
    private static UserService userService;

    @BeforeClass
    public static void setUp() throws SQLException, FileNotFoundException {
        DatabaseConnection.inicializarBBDD(DatabaseConnection.DATABASE_SUPEBASE);
        userService = new UserService(DatabaseConnection.DATABASE_SUPEBASE);
    }

    @Test
    public void testCreateUser() {
        // Creamos un objeto User
        String email = "john.doe@example.com";
        User user = new User(2,"John Doe", email);

        // Creamos el usuario
        userService.createUser(user);

        // Verificamos que el usuario se haya creado correctamente
//        User createdUser = userService.getUserById(1);
        User createdUser = userService.getUserByEmail(email);
        Assert.assertEquals(user.getUsername(), createdUser.getUsername());
        Assert.assertEquals(user.getEmail(), createdUser.getEmail());

        logger.info(createdUser.toString());

        // Borramos el usuario
        userService.deleteUser(createdUser.getId());
    }

    @Test
    public void testGetUserById() {
        // Creamos un objeto User
        String email = "susan.doe@example.com";
        User user = new User(1,"Susan Doe", email);

        // Creamos el usuario
        userService.createUser(user);

        // Obtenemos el usuario por ID
//        User retrievedUser = userService.getUserById(1);
        // Obtenemos el usuario por email
        User retrievedUser = userService.getUserByEmail(email);

        // Verificamos que el usuario se haya recuperado correctamente
        Assert.assertEquals(user.getEmail(), retrievedUser.getEmail());
        Assert.assertEquals(user.getUsername(), retrievedUser.getUsername());

        // Borramos el usuario
        userService.deleteUser(retrievedUser.getId());
    }

    @Test
    public void testDeleteUser() {
        String email = "john.doe@example.com";
        // Creamos un objeto User
        User user = new User(1, "Doe", email);

        // Creamos el usuario
        userService.createUser(user);
        // Verificamos que el usuario esté en la base de datos
        User retrievedUser = userService.getUserByEmail(email);

        // Verificamos que el usuario se haya recuperado correctamente
        Assert.assertEquals(user.getEmail(), retrievedUser.getEmail());
        Assert.assertEquals(user.getUsername(), retrievedUser.getUsername());

        // Borramos el usuario
//        userService.deleteUser(retrievedUser.getId());

        // Borramos el usuario por email
        userService.deleteUserByEmail(retrievedUser.getEmail());

        // Verificamos que el usuario no esté más en la base de datos
//        User deletedUser = userService.getUserById(1);
        User deletedUser = userService.getUserByEmail(email);
        Assert.assertNull(deletedUser);
    }

    @Test
    public void testUpdateUser() {
        String email = "juan_update.doe@example.com";
        String nuevoNombre = "Jane";
        // Creamos un objeto User
//        User user = new User(1, "Juan Doe", "juan.doe@example.com");
        User user = new User("Juan_update Doe", email);

        // Creamos el usuario
        userService.createUser(user);

        // Actualizamos el usuario
//        user = userService.getUserById(1);
        user = userService.getUserByEmail(email);
        user.setUsername(nuevoNombre);
//        userService.updateUser(user);
        userService.updateUserByEmail(user);

        // Verificamos que el usuario se haya actualizado correctamente
//        User updatedUser = userService.getUserById(1);
        User updatedUser = userService.getUserByEmail(email);
        Assert.assertEquals(nuevoNombre, updatedUser.getUsername());

        // Borramos el usuario
        userService.deleteUser(updatedUser.getId());
//        userService.deleteUserByEmail(email);
    }
}