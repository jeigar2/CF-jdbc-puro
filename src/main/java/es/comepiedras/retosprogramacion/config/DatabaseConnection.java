package es.comepiedras.retosprogramacion.config;

import org.h2.tools.RunScript;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnection {

    public static final int DATABASE_H2 = 1;
    public static final int DATABASE_SUPEBASE = 2;
    public static final String SCRIPT_H2 = "src/main/resources/sql/createDatabase.sql";

    public static void inicializarBBDD(int db) throws FileNotFoundException, SQLException {
        Map<Integer, String> scripts = new HashMap<>();
        scripts.put(DATABASE_H2, SCRIPT_H2);
        if(db == DATABASE_H2) {
            try {
                 RunScript.execute(getConnection(db), new FileReader(scripts.get(db)));
            } catch (SQLException | FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static Connection getConnection(int db){
        Connection connection = null;
        try {
            if(db == DATABASE_H2) {
                connection = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
            }
            else if(db == DATABASE_SUPEBASE) {
                connection = DriverManager.getConnection(DatabaseConfig.URL_SUPABASE, DatabaseConfig.USER_SUPABASE, DatabaseConfig.PASSWORD_SUPABASE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectarse a la base de dato", e);
        }
        return connection;
    }
}
