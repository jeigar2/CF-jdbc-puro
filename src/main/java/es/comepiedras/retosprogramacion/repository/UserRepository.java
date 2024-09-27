package es.comepiedras.retosprogramacion.repository;

import es.comepiedras.retosprogramacion.config.DatabaseConnection;
import es.comepiedras.retosprogramacion.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserRepository {

    Logger logger = Logger.getLogger(UserRepository.class.getName());
    private int bbdd;

    public UserRepository(int bbdd){
        this.bbdd = bbdd;
    }

    public void createUser(User user) {
        String sql = "INSERT INTO users (username, email) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(bbdd); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.executeUpdate();
            logger.info("Usuario creado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection(bbdd); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(bbdd); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(bbdd); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                logger.info("Usuario eliminado exitosamente.");
            } else {
                logger.warning("No se encontró el usuario con el id: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserByEmail(String email) {
        String sql = "DELETE FROM users WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection(bbdd); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                logger.info("Usuario eliminado exitosamente.");
            } else {
                logger.warning("No se encontró el usuario con el email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET username = ?, email = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(bbdd); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setInt(3, user.getId());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                logger.info("Usuario actualizado exitosamente.");
            } else {
                logger.warning("No se encontró el usuario con el id: "
                        + user.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserByEmail(User user) {
        String sql = "UPDATE users SET username = ?, email = ? WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection(bbdd); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getEmail());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                logger.info("Usuario actualizado exitosamente.");
            } else {
                logger.warning("No se encontró el usuario con el email: "
                        + user.getEmail());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
