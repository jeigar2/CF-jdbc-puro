package es.comepiedras.retosprogramacion.config;

public class DatabaseConfig {
    // Database H2
    public static final String URL = "jdbc:h2:mem:cf_test";
    public static final String USER = "sa";
    public static final String PASSWORD = "";

    // Database Postgres en la nube supabase
    public static final String URL_SUPABASE = "jdbc:postgresql://aws-0-eu-west-1.pooler.supabase.com:5432/postgres";
    public static final String USER_SUPABASE = "postgres.drytbengfubbxijtnibu";
    public static final String PASSWORD_SUPABASE = System.getenv("PASSWORD_SUPABASE");
}
