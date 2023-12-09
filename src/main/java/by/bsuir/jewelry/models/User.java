package by.bsuir.jewelry.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@code User} class represents a user in the system with attributes such as login, password, and role.
 * Each instance of this class corresponds to a record in the "users" table of the database.
 * It is annotated with JPA annotations for mapping to database columns.
 *
 * <p>The class provides constructors for creating instances with specified attributes, as well as
 * default and all-arguments constructors. The fields are annotated with appropriate JPA annotations
 * to define their mapping to database columns.</p>
 *
 * <p>Example usage:</p>
 * {@code
 * User user = new User("john_doe", "securePassword123", "user");
 * }
 *
 * @see Entity
 * @see Table
 * @see Column
 * @see GeneratedValue
 * @see Id
 * @see Data
 * @see NoArgsConstructor
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The login name of the user. It is unique and cannot be null.
     */
    @Column(name = "login", unique = true, nullable = false, length = 50)
    private String login;

    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    /**
     * Constructs a new {@code User} instance with the specified login, password, and role.
     *
     * @param login    the login name of the user
     * @param password the password of the user
     * @param role     the role of the user in the system
     */
    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

}
