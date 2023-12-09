package by.bsuir.jewelry.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The {@code AuthResult} class represents the result of user authentication.
 * It includes the user ID, authentication status, and the user role.
 */
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
public class AuthResult {

    /**
     * The ID of the authenticated user.
     */
    private Long userId;

    /**
     * The authentication status. {@code true} if authentication is successful, {@code false} otherwise.
     */
    private boolean isAuthenticated;

    /**
     * The role of the authenticated user, either "user" or "admin".
     */
    private String role;
}
