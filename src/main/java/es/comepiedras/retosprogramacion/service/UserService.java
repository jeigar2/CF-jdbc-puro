package es.comepiedras.retosprogramacion.service;

import es.comepiedras.retosprogramacion.model.User;
import es.comepiedras.retosprogramacion.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(){
        userRepository = new UserRepository();
    }

    public void createUser(User user) {
        userRepository.createUser(user);
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public void deleteUser(int id) {
        userRepository.deleteUser(id);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

}
