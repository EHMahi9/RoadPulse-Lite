package service;

import java.util.List;
import model.User;
import repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(User user) {

        if (user == null) {
            return false;
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            return false;
        }

        return userRepository.save(user);
    }

    public User login(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            return null;
        }

        if (!user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(int userId) {
        return userRepository.findById(userId);
    }

    public boolean updateUser(User user) {
        return userRepository.update(user);
    }

    public boolean deleteUser(int userId) {
        return userRepository.delete(userId);
    }

}