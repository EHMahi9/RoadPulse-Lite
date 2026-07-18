package manager;

import java.util.ArrayList;
import model.User;

public class UserManager {

    private ArrayList<User> users;

    public UserManager() {

        users = new ArrayList<>();

    }

    public void registerUser(User user) {

        users.add(user);

        System.out.println("User registered successfully.");

    }

    public void showAllUsers() {

        if (users.isEmpty()) {

            System.out.println("No users found.");

            return;

        }

        for (User user : users) {

            System.out.println("---------------------");
            System.out.println(user);

        }

    }

    public User findUserByEmail(String email) {

        for (User user : users) {

            if (user.getEmail().equalsIgnoreCase(email)) {

                return user;

            }

        }

        return null;

    }

    public User login(String email, String password) {

        for (User user : users) {

            if (user.getEmail().equalsIgnoreCase(email)
                    && user.getPassword().equals(password)) {

                return user;

            }

        }

        return null;

    }

    public boolean deleteUser(String email) {

        User user = findUserByEmail(email);

        if (user != null) {

            users.remove(user);

            return true;

        }

        return false;

    }

}