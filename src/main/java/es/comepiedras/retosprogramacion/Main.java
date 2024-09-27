package es.comepiedras.retosprogramacion;

import es.comepiedras.retosprogramacion.config.DatabaseConnection;
import es.comepiedras.retosprogramacion.model.User;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Logger;

import es.comepiedras.retosprogramacion.service.UserService;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        int bbdd = DatabaseConnection.DATABASE_H2;
        if (args.length > 0) {
            try {
                bbdd = Integer.parseInt(args[0]);
            } catch (NumberFormatException e){
                logger.warning("el argumento no es numérico");
            }
        }

        DatabaseConnection.inicializarBBDD(bbdd);

        UserService userService = new UserService(bbdd);

        String email = "run_johndoe@example.com";
        String emailModificado = "run_janedoe@example.com";

        // Crear usuario
        User newUser = new User("JohnDoe", email);
        userService.createUser(newUser);

        // Leer usuario
//        User user = userService.getUserById(1);
        User user = userService.getUserByEmail(email);
        logger.info("Usuario recuperado: " + user);

        // Actualizar usuario
        user.setUsername("JaneDoe");
        user.setEmail(emailModificado);
        userService.updateUser(user);
        logger.info("Usuario recuperado: " + user);

        // Eliminar usuario
//        userService.deleteUser(1);
        userService.deleteUserByEmail(emailModificado);
    }
}