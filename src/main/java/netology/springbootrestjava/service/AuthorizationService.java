package netology.springbootrestjava.service;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import netology.springbootrestjava.exception.InvalidCredentials;
import netology.springbootrestjava.exception.UnauthorizedUser;
import netology.springbootrestjava.models.Authorities;
import netology.springbootrestjava.models.User;
import netology.springbootrestjava.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;

    public List<Authorities> getAuthorities(User user) {
        if (isEmpty(user.getUsername()) || isEmpty(user.getPassword())) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
