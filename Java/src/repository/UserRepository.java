package repository;

import java.util.List;
import model.User;

public interface UserRepository {

    boolean save(User user);

    User findById(int userId);

    User findByEmail(String email);

    List<User> findAll();

    boolean update(User user);

    boolean delete(int userId);

}