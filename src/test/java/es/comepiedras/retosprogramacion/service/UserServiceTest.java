package es.comepiedras.retosprogramacion.service;

import es.comepiedras.retosprogramacion.config.DatabaseConnection;
import es.comepiedras.retosprogramacion.model.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class UserServiceTest {
    private static UserService userService;

    @BeforeClass
    public static void setUp() throws SQLException, FileNotFoundException {
        DatabaseConnection.inicializarBBDD();
        userService = new UserService();
    }

    @Test
    public void testCreateUser() {
        // Creamos un objeto User
        User user = new User(1,"John Doe", "john.doe@example.com");

        // Creamos el usuario
        userService.createUser(user);

        // Verificamos que el usuario se haya creado correctamente
        User createdUser = userService.getUserById(1);
        Assert.assertEquals(user, createdUser);

        // Borramos el usuario
        userService.deleteUser(1);
    }

    @Test
    public void testGetUserById() {
        // Creamos un objeto User
        User user = new User(1,"Susan Doe", "susan.doe@example.com");

        // Creamos el usuario
        userService.createUser(user);

        // Obtenemos el usuario por ID
        User retrievedUser = userService.getUserById(1);

        // Verificamos que el usuario se haya recuperado correctamente
        Assert.assertEquals(user, retrievedUser);

        // Borramos el usuario
        userService.deleteUser(1);
    }

    @Test
    public void testDeleteUser() {
        // Creamos un objeto User
        User user = new User(1, "Doe", "john.doe@example.com");

        // Creamos el usuario
        userService.createUser(user);

        // Borramos el usuario
        userService.deleteUser(1);

        // Verificamos que el usuario no esté más en la base de datos
        User deletedUser = userService.getUserById(1);
        Assert.assertNull(deletedUser);
    }

    @Test
    public void testUpdateUser() {
        // Creamos un objeto User
        User user = new User(1, "Juan Doe", "juan.doe@example.com");

        // Creamos el usuario
        userService.createUser(user);

        // Actualizamos el usuario
        user = userService.getUserById(1);
        user.setUsername("Jane");
        userService.updateUser(user);

        // Verificamos que el usuario se haya actualizado correctamente
        User updatedUser = userService.getUserById(1);
        Assert.assertEquals("Jane", updatedUser.getUsername());

        // Borramos el usuario
        userService.deleteUser(1);
    }
}