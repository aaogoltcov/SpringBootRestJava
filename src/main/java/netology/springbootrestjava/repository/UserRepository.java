package netology.springbootrestjava.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import netology.springbootrestjava.exception.InvalidCredentials;
import netology.springbootrestjava.exception.UnauthorizedUser;
import netology.springbootrestjava.models.Authorities;
import netology.springbootrestjava.models.User;

@Repository
public class UserRepository {
    public static ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    public UserRepository() {
        var username = "test_user";
        var password = "test_password";
        var roles = new ArrayList<Authorities>();

        roles.add(Authorities.WRITE);

        users.put(username, new User(username, password, roles));
    }

    public List<Authorities> getUserAuthorities(User user) {
        final User findUser = users.get(user.getUsername());

        if (findUser == null) {
            throw new UnauthorizedUser(String.format("Can't found user with username: %s", user.getUsername()));
        }

        var isPasswordCorrect = findUser.getPassword().equals(user.getPassword());

        if (!isPasswordCorrect) {
            throw new InvalidCredentials("Password is incorrect");
        }

        return findUser.getRoles();
    }
}
