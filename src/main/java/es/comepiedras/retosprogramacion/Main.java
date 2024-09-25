package es.comepiedras.retosprogramacion;

import es.comepiedras.retosprogramacion.config.DatabaseConnection;
import es.comepiedras.retosprogramacion.model.User;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import es.comepiedras.retosprogramacion.service.UserService;

public class Main {

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        DatabaseConnection.inicializarBBDD();

        UserService userService = new UserService();

        // Crear usuario
        User newUser = new User("JohnDoe", "johndoe@example.com");
        userService.createUser(newUser);

        // Leer usuario
        User user = userService.getUserById(1);
        System.out.println("Usuario recuperado: " + user);

        // Actualizar usuario
        user.setUsername("JaneDoe");
        user.setEmail("janedoe@example.com");
        userService.updateUser(user);
        System.out.println("Usuario recuperado: " + user);

        // Eliminar usuario
        userService.deleteUser(1);
    }
}