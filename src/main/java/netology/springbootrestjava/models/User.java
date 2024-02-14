package netology.springbootrestjava.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String username;
    private String password;
    private List<Authorities> roles;

    public User(String username, String password, List<Authorities> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
