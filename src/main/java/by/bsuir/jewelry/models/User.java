package by.bsuir.jewelry.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login", unique = true, nullable = false, length = 50)
    private String login;

    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

}
