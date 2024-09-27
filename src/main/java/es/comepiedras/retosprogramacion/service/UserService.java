package es.comepiedras.retosprogramacion.service;

import es.comepiedras.retosprogramacion.config.DatabaseConnection;
import es.comepiedras.retosprogramacion.model.User;
import es.comepiedras.retosprogramacion.repository.UserRepository;

public class UserService {
    private int bbdd;

    private UserRepository userRepository;

    public UserService(){
        this(DatabaseConnection.DATABASE_H2);
    }

    public UserService(int bbdd){
        this.bbdd = bbdd;
        userRepository = new UserRepository(bbdd);
    }

    public void createUser(User user) {
        userRepository.createUser(user);
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }
    public void deleteUserByEmail(String email) {
        userRepository.deleteUserByEmail(email);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void updateUserByEmail(User user) {
        userRepository.updateUserByEmail(user);
    }

}
