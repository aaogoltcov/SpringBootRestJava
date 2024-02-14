package netology.springbootrestjava.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import netology.springbootrestjava.exception.InvalidCredentials;
import netology.springbootrestjava.exception.UnauthorizedUser;
import netology.springbootrestjava.models.Authorities;
import netology.springbootrestjava.models.User;
import netology.springbootrestjava.service.AuthorizationService;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService service;

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestBody @Valid User user) {
        return service.getAuthorities(user);
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> invalidCredentialsHandler(InvalidCredentials e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> unauthorizedUserHandler(UnauthorizedUser e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
