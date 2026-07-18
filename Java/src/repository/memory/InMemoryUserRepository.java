package repository.memory;

import model.User;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {

    private final List<User> users;

    public InMemoryUserRepository() {
        users = new ArrayList<>();
    }

    @Override
    public boolean save(User user) {
        return users.add(user);
    }

    @Override
    public User findById(int userId) {

        for (User user : users) {

            if (user.getUserId() == userId) {
                return user;
            }

        }

        return null;
    }

    @Override
    public User findByEmail(String email) {

        for (User user : users) {

            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }

        }

        return null;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public boolean update(User updatedUser) {

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getUserId() == updatedUser.getUserId()) {

                users.set(i, updatedUser);

                return true;

            }

        }

        return false;

    }

    @Override
    public boolean delete(int userId) {

        User user = findById(userId);

        if (user != null) {

            users.remove(user);

            return true;

        }

        return false;

    }

}